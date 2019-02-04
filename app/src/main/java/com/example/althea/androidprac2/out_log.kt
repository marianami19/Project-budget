package com.example.althea.androidprac2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_income_log.*
import kotlinx.android.synthetic.main.activity_out_log.*

class out_log : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_log)



        card_outgoing.layoutManager = LinearLayoutManager(this)

        val context = this
        var db = out_handler_sp(context)

        /*var income_all = db.readData()  */

        var users = ArrayList<User_two>()
        users = db.readData_sp() as ArrayList<User_two>

        card_outgoing.adapter = MainAdapterSpends(users)
    }
}
