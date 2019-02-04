package com.example.althea.androidprac2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainAdapter(val userList: ArrayList<User>): RecyclerView.Adapter<MainAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById(R.id.in_name) as TextView
        val TextMethod = itemView.findViewById(R.id.in_way) as TextView
        val textAmt = itemView.findViewById(R.id.in_amt) as TextView
        val textDate = itemView.findViewById(R.id.in_date) as TextView
    }


    val name_list= listOf<String>("Althea", "Namitha", "Leora", "Amber")



    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        /*val layoutInflater = LayoutInflater.from(parent?.context)
        val cellforRow = layoutInflater.inflate(R.layout.log_inwallet, parent, false)
        return CustomViewHolder(cellforRow)  */

        val v = LayoutInflater.from(parent?.context).inflate(R.layout.log_inwallet, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val user:User = userList.get(p1)
        holder?.textName?.text= user.name_in
        holder?.textAmt?.text = user.num_in.toString()
        holder?.TextMethod?.text = user.method_in
        holder?.textDate?.text = user.in_date

    }

    override fun getItemCount(): Int{
        return userList.size
    }


    /*
    override fun onCreateViewHolder(parent: ViewGroup?, viewtype: Int) : CustomViewHolder{

    }

    override fun onBindViewholder(holder: CustomViewHolder?, position : Int){

    }  */

}

