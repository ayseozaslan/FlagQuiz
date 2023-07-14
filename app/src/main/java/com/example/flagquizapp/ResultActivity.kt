package com.example.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.flagquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= DataBindingUtil. setContentView(this,R.layout.activity_result)

        val trueCounter=intent.getIntExtra("trueCounter",0)
        binding.textViewResult.text="$trueCounter DOĞRU ${5-trueCounter} YANLIŞ"
        binding.textViewPercentResult.text="% ${(trueCounter*100)/5} BAŞARI"

        binding.buttonTryAgain.setOnClickListener {
            val intent= Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}