package com.example.althea.androidprac2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_money.*
import kotlinx.android.synthetic.main.activity_goals.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class goals : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        val context = this
        var db = goal_handler(context)

        addBtn.setOnClickListener{

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
            val currentDate = sdf.format(Date())

            var in_date:String = currentDate
            if (name.text.toString().length > 0 &&
                    desc.text.toString().length > 0 &&
                    am_goal.text.toString().length > 0 &&
                    in_date.length > 0 ){



                var user = goal_logg(name.text.toString(), desc.text.toString(), am_goal.text.toString().toFloat(), in_date )

                /*var db = handler_in(context)*/
                db.insertData(user)

                    name.text.clear()
                    desc.text.clear()
                    am_goal.text.clear()

            }

            else
            {
                Toast.makeText(context, "Enter all the values", Toast.LENGTH_SHORT).show()
            }


//            val intent = Intent(context, EachGoalActivity::class.java)
//            intent.putExtra("Amount_goal", am_goal.text.toString())
//            startActivity(intent)
        }
    }
}
