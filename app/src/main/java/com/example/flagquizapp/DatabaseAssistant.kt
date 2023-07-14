package com.example.flagquizapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseAssistant(context:Context) : SQLiteOpenHelper(context,"flagsquiz.sqlite",null,1) {//null=kendimiz br cursor kullanmayacağımız iin null dedik

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"flags\" (\n" +
                "\t\"flag_id\"\tINTEGER,\n" +
                "\t\"flag_ad\"\tTEXT,\n" +
                "\t\"flag_resim\"\tTEXT,\n" +
                "\t\"flag_id\"  PRIMARY KEY AUTOINCREMENT\n" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS flags")
        onCreate(db)
    }
}