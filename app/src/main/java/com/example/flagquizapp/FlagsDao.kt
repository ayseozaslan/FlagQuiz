package com.example.flagquizapp

class FlagsDao {

    fun ramdomlyBring5Flags(vt:DatabaseAssistant):ArrayList<Flags>{

        val flagsList=ArrayList<Flags>()
        val db=vt.writableDatabase
        val c=db.rawQuery("SELECT * FROM flags ORDER BY RANDOM() LIMIT 5 ",null)

        while (c.moveToNext()){
            val flag=Flags(c.getInt(c.getColumnIndex("flag_id"))
            ,c.getString(c.getColumnIndex("flag_ad"))
            ,c.getString(c.getColumnIndex("flag_resim")))
            flagsList.add(flag)
        }
        return flagsList
    }

    fun bring3wrongchoices(vt:DatabaseAssistant,flag_id: Int):ArrayList<Flags>{

        val flagsList=ArrayList<Flags>()
        val db=vt.writableDatabase
        val c=db.rawQuery("SELECT * FROM flags WHERE flag_id !=$flag_id ORDER BY RANDOM() LIMIT 3 ",null)
//dogru seçenegin indeksi 4 ise 4 den farklı 3 seçenek getirecek
        while (c.moveToNext()){
            val flag=Flags(c.getInt(c.getColumnIndex("flag_id"))
                ,c.getString(c.getColumnIndex("flag_ad"))
                ,c.getString(c.getColumnIndex("flag_resim")))
            flagsList.add(flag)
        }
        return flagsList
    }
}