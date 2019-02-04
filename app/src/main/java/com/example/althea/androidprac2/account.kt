package com.example.althea.androidprac2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_account.*


class account : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

            var context = this

            var db= handler_in(context)
            var sum_in = db.readNum()

            var db2 = out_handler_sp(context)
            var sum_out = db2.readNum_sp()

            var balance = sum_in - sum_out
            Tot_sum.text="" + balance

        go_ingoing.setOnClickListener{

                val intent = Intent(context, incomeLog::class.java)
                startActivity(intent)
            }




            go_outgoing.setOnClickListener{

                val intent = Intent(context, out_log::class.java)
                startActivity(intent)
            }




    }
}
