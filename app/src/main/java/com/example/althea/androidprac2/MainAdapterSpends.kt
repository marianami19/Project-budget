package com.example.althea.androidprac2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainAdapterSpends(val userList1: ArrayList<User_two>): RecyclerView.Adapter<MainAdapterSpends.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById(R.id.out_name) as TextView
        val TextMethod = itemView.findViewById(R.id.out_way) as TextView
        val textAmt = itemView.findViewById(R.id.out_amt) as TextView
        val textDate = itemView.findViewById(R.id.out_date) as TextView
    }


    val name_list= listOf<String>("Althea", "Namitha", "Leora", "Amber")



    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        /*val layoutInflater = LayoutInflater.from(parent?.context)
        val cellforRow = layoutInflater.inflate(R.layout.log_inwallet, parent, false)
        return CustomViewHolder(cellforRow)  */

        val v = LayoutInflater.from(parent?.context).inflate(R.layout.log_wallet, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val user:User_two = userList1.get(p1)
        holder?.textName?.text= user.name_out
        holder?.textAmt?.text = user.num_out.toString()
        holder?.TextMethod?.text = user.method_o
        holder?.textDate?.text = user.o_date

    }

    override fun getItemCount(): Int{
        return userList1.size
    }

}
