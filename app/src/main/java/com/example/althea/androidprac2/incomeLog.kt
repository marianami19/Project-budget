package com.example.althea.androidprac2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.althea.androidprac2.R.id.card_ingoing
import kotlinx.android.synthetic.main.activity_income_log.*


class incomeLog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_log)

        card_ingoing.layoutManager = LinearLayoutManager(this)

        val context = this
        var db = handler_in(context)

        /*var income_all = db.readData()  */

        var users = ArrayList<User>()
        users = db.readData() as ArrayList<User>

        card_ingoing.adapter = MainAdapter(users)

    }
}
