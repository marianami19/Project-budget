package com.example.althea.androidprac2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_money.*
import kotlinx.android.synthetic.main.activity_outgoing.*
import java.text.SimpleDateFormat
import java.util.*

class outgoing : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outgoing)

        val context = this

        button_add1.setOnClickListener ({
            if (name_text1.text.toString().length > 0 &&
                    num_text1.text.toString().length > 0 &&
                    method_text1.text.toString().length > 0) {

                val sdf = SimpleDateFormat("dd/M/yyyy")
                val currentDate = sdf.format(Date())

                var in_date: String = currentDate
                var user = User_two(name_text1.text.toString(), num_text1.text.toString().toFloat(), method_text1.text.toString(), in_date)

                /*var db = handler_in(context)*/
                var dbb = out_handler_sp(context)
                dbb.insertData_sp(user)

                name_text1.text.clear()
                num_text1.text.clear()
                method_text1.text.clear()

            } else {
                Toast.makeText(context, "Enter all the values", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
