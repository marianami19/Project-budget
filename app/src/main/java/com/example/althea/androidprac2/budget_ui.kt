package com.example.althea.androidprac2

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_budget_ui.*
import java.text.SimpleDateFormat
import java.util.*

val db1="db1"


class out_handler( var context: Context) : SQLiteOpenHelper(context, db1, null, 1) {
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val tableb="budget"
    val money="money"
    val sdate="sdate"
    val edate="edate"

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable1 = "create table if not exists " + tableb + "(" +
                money + " real, " +
                sdate + " varchar(30) , " +
                edate + " varchar(30) " + ");";

        db?.execSQL(createTable1)
    }

    fun insertbudget(user: User_two) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(com.example.althea.androidprac2.money, user.budget)
        cv.put(com.example.althea.androidprac2.sdate, user.sdate)
        cv.put(com.example.althea.androidprac2.edate, user.edate)
        val result = db.insert(com.example.althea.androidprac2.tableb, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Budget set", Toast.LENGTH_SHORT).show()

        db.close()
    }

    fun  rb(): Float{
        var con=this

        val db = this.readableDatabase
        val query = "select * from " + tableb
        val result = db.rawQuery(query, null)
        var b:Float=0f
        if (result.moveToLast()) {

                var user2 = User_two()
                user2.budget = result.getFloat(result.getColumnIndex(money))
                b=user2.budget

        }
        result.close()
        db.close()

        return b

    }
    fun  rsd(): String{
        var con=this

        val db = this.readableDatabase
        val query = "select * from " + tableb
        val result = db.rawQuery(query, null)
        var b:String=""
        if (result.moveToLast()) {

            var user2 = User_two()
            user2.sdate = result.getString(result.getColumnIndex(sdate))
            b=user2.sdate

        }
        result.close()
        db.close()

        return b

    }
    fun  red(): String{
        var con=this

        val db = this.readableDatabase
        val query = "select * from " + tableb
        val result = db.rawQuery(query, null)
        var b:String=""
        if (result.moveToLast()) {

            var user2 = User_two()
            user2.edate = result.getString(result.getColumnIndex(edate))
            b=user2.edate

        }
        result.close()
        db.close()

        return b

    }
    fun readbudget(): MutableList<User_two> {

        var con = this
        var list: MutableList<User_two> = ArrayList()

        val db = this.readableDatabase
        val query = "select * from " + tableb
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var user2 = User_two()
                user2.budget = result.getFloat(result.getColumnIndex(money))
                user2.sdate = result.getString(result.getColumnIndex(sdate))
                user2.edate = result.getString(result.getColumnIndex(edate))
                list.add(user2)
            } while (result.moveToNext())
        }
        result.close()
        db.close()

        return list


    }


}

class budget_ui : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_ui)
        var context=this

        var db3=out_handler(context)
        //var buddy:Double=0.0

        val graph = findViewById<View>(R.id.graph) as GraphView

        graph.gridLabelRenderer.isHorizontalLabelsVisible=false
        graph.gridLabelRenderer.isVerticalLabelsVisible = false



        button2.setOnClickListener{


            //var ans=db2.readData_am(d_in, d_end)
            //var ans2=db2.readData_tm(d_in, d_end)
            if (editTextbb.text.isNotEmpty() &&
                    v_dattee.text.toString().length > 0 &&
                    end_d.text.toString().length > 0 ){

                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
                val currentDate = sdf.format(Date())

                var in_date:String = currentDate
                var user = User_two(editTextbb.text.toString().toFloat(), v_dattee.text.toString(), end_d.text.toString())

                /*var db = handler_in(context)*/
                db3.insertbudget(user)
                Toast.makeText(context, "Budget set", Toast.LENGTH_SHORT).show()





            }

            else
            {
                Toast.makeText(context, "Please enter budget", Toast.LENGTH_SHORT).show()
            }

            //val i =0
//        for (i in ans) {
//            println(i)
//
//        }





//        val calender : Calendar.getInstance()
//        val d1 : Date = calender.time


                   // DataPoint(2.0, 1000.0)))
//                DataPoint(2.0, 3.0),
//                DataPoint(3.0, 2.0),
//                DataPoint(4.0, 6.0)))
            //val series = LineGraphSeries(gra)


        }
        viewgraph.setOnClickListener{

            var buddy:Double=db3.rb().toDouble()

            graph.gridLabelRenderer.isVerticalLabelsVisible = true
            // var d_s=intent.getStringExtra("start")
            //var d_e=intent.getStringExtra("end")
            graph.getViewport().setXAxisBoundsManual(true)
            graph.getViewport().setMinX(0.0)
            graph.getViewport().setMaxX(2.0)
            //graph.animation.duration


// set manual Y bounds
            graph.getViewport().setYAxisBoundsManual(true)
            graph.getViewport().setMinY(0.0)
            graph.getViewport().setMaxY(buddy)



            var d_s = db3.rsd().toString()
            var d_e = db3.red().toString()

            var db = out_handler_sp(context)
            var tot = db.readNum_sum(d_s, d_e)
            var tot_double = tot.toDouble()
            var tot_x = 4.0




            val series = BarGraphSeries(arrayOf<DataPoint>(
                    //var buddy=editTextbb.text.toString().toDouble()
                    DataPoint(0.5, 0.0),
                    DataPoint(1.0,tot_double)))
            Thread(Runnable {

                graph.addSeries(series)
            }).start()

        }



        //val textView: TextView = findViewById(R.id.v_dattee)
        v_dattee.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            v_dattee.text = sdf.format(cal.time)

        }

        v_dattee.setOnClickListener {
            DatePickerDialog(this@budget_ui, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }


       // val textView1: TextView = findViewById(R.id.end_d)
        end_d.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())

        var calc = Calendar.getInstance()

        val dateSetListenerc = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calc.set(Calendar.YEAR, year)
            calc.set(Calendar.MONTH, monthOfYear)
            calc.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            end_d.text = sdf.format(calc.time)

        }



        end_d.setOnClickListener {
            DatePickerDialog(this@budget_ui, dateSetListenerc,
                    calc.get(Calendar.YEAR),
                    calc.get(Calendar.MONTH),
                    calc.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}
