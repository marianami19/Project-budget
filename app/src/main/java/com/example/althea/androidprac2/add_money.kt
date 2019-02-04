package com.example.althea.androidprac2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_money.*
import java.text.SimpleDateFormat
import java.util.*

class add_money : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_money)


        val context = this
        var db = handler_in(context)

        button_add.setOnClickListener ({
            if (name_text.text.toString().length > 0 &&
                    num_text.text.toString().length > 0 &&
                    method_text.text.toString().length > 0 ){

                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
                val currentDate = sdf.format(Date())

                var in_date:String = currentDate
                var user = User(name_text.text.toString(), num_text.text.toString().toInt(), method_text.text.toString(), in_date )

                /*var db = handler_in(context)*/
                    db.insertData(user)

                name_text.text.clear()
                num_text.text.clear()
                method_text.text.clear()



            }

            else
            {
             Toast.makeText(context, "Enter all the values", Toast.LENGTH_SHORT).show()
            }

        })


    }
}
