package com.example.althea.androidprac2

import android.content.Intent
import com.example.althea.androidprac2.R.id.Tot_sum
import kotlinx.android.synthetic.main.activity_account.*
import java.text.SimpleDateFormat
import java.util.*


class goal_logg{
    var name : String = ""
    var description : String = ""
    var amount : Float = 0.0F
    //var in_date : String = LocalDate.now().toString()

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())


    var the_date:String = currentDate

    var bal:Float=0F




    constructor(namena:String, decna: String, amna:Float, datana : String){
        this.name = namena
        this.description = decna
        this.amount = amna
        this.the_date = datana
    }

    constructor(){


    }
    constructor(bala:Float){
        this.bal= bala
    }








}