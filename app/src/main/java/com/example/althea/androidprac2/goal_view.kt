package com.example.althea.androidprac2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_goal_view.*
import kotlinx.android.synthetic.main.activity_income_log.*

class goal_view : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_view)


        goal_many.layoutManager = LinearLayoutManager(this)

        val context = this
        var db = goal_handler(context)

        /*var income_all = db.readData()  */

        var users = ArrayList<goal_logg>()
        users = db.readData() as ArrayList<goal_logg>

        goal_many.adapter = Goal_adapter(users)

         fun setRecyclerViewItemTouchListener() {

            //1
            val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                    //2
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    //3
                    val position = viewHolder.adapterPosition
                    users.removeAt(position)
                    goal_many.adapter.notifyItemRemoved(position)
//                    var users = ArrayList<goal_logg>()
//                    users = db.readData() as ArrayList<goal_logg>
//
//                    goal_many.adapter = Goal_adapter(users)
//

                }
            }

            //4
            val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
            itemTouchHelper.attachToRecyclerView(goal_many)
        }
        setRecyclerViewItemTouchListener()


    }
}
