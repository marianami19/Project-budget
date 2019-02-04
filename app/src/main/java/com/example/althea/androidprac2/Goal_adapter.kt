package com.example.althea.androidprac2

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.each_goal.*
import java.nio.file.Files.size






 class Goal_adapter(val userL: ArrayList<goal_logg>): RecyclerView.Adapter<Goal_adapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textN = itemView.findViewById(R.id.goal_name) as TextView
        val TextDes = itemView.findViewById(R.id.goal_des) as TextView
        val textA = itemView.findViewById(R.id.goal_budget) as TextView

        val textD = itemView.findViewById(R.id.date_gg) as TextView
        //val mProgressBar:ProgressBar=itemView.findViewById(R.id.progressBarGoal) as ProgressBarval
       // val text_money_left  = itemView.findViewById(R.id.show) as TextView



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Goal_adapter.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.each_goal, parent, false)
        return Goal_adapter.ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return userL.size
    }


    override fun onBindViewHolder(holder: Goal_adapter.ViewHolder, position: Int) {


        val u : goal_logg = userL.get(position)
        holder?.textN?.text = u.name
        holder?.TextDes?.text = u.description
        holder?.textA?.text = u.amount.toString()
        holder?.textD?.text = u.the_date



        //holder?.text_money_left?.text= ""


        //holder?.mProgressBar?.setProgress(u.getProgress())

    }




}