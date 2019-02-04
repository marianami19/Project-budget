package com.example.althea.androidprac2

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class User{
    var name_in : String = ""
    var num_in : Int = 0
    var method_in : String = ""
    //var in_date : String = LocalDate.now().toString()

    val sdf = SimpleDateFormat("dd/M/yyyy")
    val currentDate = sdf.format(Date())

    var in_date:String = currentDate





    constructor(in_name:String, in_num: Int, in_method:String, in_date : String){
        this.name_in = in_name
        this.num_in = in_num
        this.method_in = in_method
        this.in_date = in_date
    }

    constructor(){

    }

}

class User_two{
    var name_out : String = ""
    var num_out : Float = 0f
    var method_o : String = ""
    //var in_date : String = LocalDate.now().toString()

    val sdf = SimpleDateFormat("dd/M/yyyy")
    val currentDate = sdf.format(Date())

    var o_date:String = currentDate

    var budget:Float=0f
    var sdate:String=""
    var edate:String=""
    constructor(b:Float,sdate:String,edate:String){
        this.budget=b
        this.sdate=sdate
        this.edate=edate
    }

    constructor(in_name:String, in_num: Float, in_method:String, in_date : String){
        this.name_out = in_name
        this.num_out = in_num
        this.method_o = in_method
        this.o_date = in_date
    }

    constructor(){

    }
}