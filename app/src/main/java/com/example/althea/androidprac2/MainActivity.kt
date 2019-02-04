package com.example.althea.androidprac2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.althea.androidprac2.R.layout.*
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.each_goal.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val context = this
        income_add.setOnClickListener {
            //val context = this
            val intent = Intent(context, add_money::class.java)

            startActivity(intent)
        }

        goal.setOnClickListener{
            val intent = Intent(context, goals::class.java)

            startActivity(intent)

        }


        view_g.setOnClickListener{


           // var context = this
//            var db= handler_in(context)
//            var sum_in = db.readNum()
//
//            var db2 = out_handler_sp(context)
//            var sum_out = db2.readNum_sp()
//
//            var balance = (sum_in - sum_out).toFloat()
//            var bala = goal_logg(balance)
            //Tot_sum.text="" + balance



//            val dbb = goal_handler(context)
//
//            if(dbb.getBal().isEmpty()) {
//                dbb.insert_bal(bala)
//            }else{
//
//
//            }

            val intent = Intent(context, goal_view::class.java)
            //intent.putExtra("the_amount", balance.toString())
            startActivity(intent)

        }


        button_bank.setOnClickListener {

            val intent = Intent(context, account::class.java)

            startActivity(intent)

    }

        spend_add.setOnClickListener {
            /*val context = this*/
            val intent = Intent(context, outgoing::class.java)

            startActivity(intent)
        }

        the_budd.setOnClickListener{

            val intent = Intent(context, budget_ui::class.java)
            startActivity(intent)
        }
    }
}
