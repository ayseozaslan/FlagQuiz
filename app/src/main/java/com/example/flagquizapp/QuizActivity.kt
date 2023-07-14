package com.example.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.flagquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding:ActivityQuizBinding

    private lateinit var questions:ArrayList<Flags>
    private lateinit var falseChoices:ArrayList<Flags>
    private lateinit var trueQuestion:Flags
    private lateinit var allOptions:HashSet<Flags>
    private lateinit var vt:DatabaseAssistant

    private var trueCounter=0
    private var falseCounter=0
    private var questionCounter=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= DataBindingUtil. setContentView(this,R.layout.activity_quiz)

        vt=DatabaseAssistant(this)
        questions=FlagsDao().ramdomlyBring5Flags(vt)
        questionsUpload()



        binding.buttonA.setOnClickListener {
            trueCheck(binding.buttonA)
            QuestionCounterCheck()
        }
        binding.buttonB.setOnClickListener {
            trueCheck(binding.buttonB)
            QuestionCounterCheck()
        }
        binding.buttonC.setOnClickListener {
            trueCheck(binding.buttonC)
            QuestionCounterCheck()
        }
        binding.buttonD.setOnClickListener {
            trueCheck(binding.buttonD)
            QuestionCounterCheck()
        }
    }
    fun questionsUpload(){
        binding.textViewNumberOfQuestions.text="${questionCounter+1}.Soru"
        trueQuestion=questions.get(questionCounter)//ilk soru alıcaz
        binding.imageViewFlag.setImageResource(resources.getIdentifier(trueQuestion.flag_resim,"drawable",packageName))//ilk soru ne ise o sorunun bayrağını alıcak

        falseChoices=FlagsDao().bring3wrongchoices(vt, trueQuestion.flag_id)

        allOptions=HashSet()
        allOptions.add(trueQuestion)
        allOptions.add(falseChoices.get(0))
        allOptions.add(falseChoices.get(1))
        allOptions.add(falseChoices.get(2))

        binding.buttonA.text=allOptions.elementAt(0).flag_ad
        binding.buttonB.text=allOptions.elementAt(1).flag_ad
        binding.buttonC.text=allOptions.elementAt(2).flag_ad
        binding.buttonD.text=allOptions.elementAt(3).flag_ad

    }
    fun QuestionCounterCheck(){
        questionCounter++
        if (questionCounter !=5){
            questionsUpload()
        }
        else{
            val intent= Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("trueCounter",trueCounter)
            startActivity(intent)
            finish()
        }
    }

    fun trueCheck(button: Button){
        val buttonText=button.text.toString()
        val trueCheck=trueQuestion.flag_ad
        if (buttonText==trueCheck){
            trueCounter++
        }else{
            falseCounter++
        }
        binding.textViewTrue.text="Doğru:$trueCounter"
        binding.textViewFalse.text="Doğru:$falseCounter"

    }

}