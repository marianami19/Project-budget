package com.example.althea.androidprac2
import android.content.ContentValues
import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val db = "goal_things5"
val tablef = "things"
val namef = "title"
val desc = "About"
val amount = "amount"
val datee = "DateTime"


val tabel1 = "tableau"
val balance="bal"

class goal_handler(var context: Context) : SQLiteOpenHelper(context, db, null, 1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "create table " + tablef + "(" +
                namef + " varchar(100), " +
                desc + " varchar(200) , " +
                amount + " real ," +
                datee + " varchar(20) );";

        val createTable1 = "create table " + tabel1 + "(" +
                balance + " real);";

        db?.execSQL(createTable1)
        db?.execSQL(createTable)
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(user: goal_logg) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(namef, user.name)
        cv.put(desc, user.description)
        cv.put(amount, user.amount)
        cv.put(datee, user.the_date)
        val result = db.insert(tablef, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Entered your goal", Toast.LENGTH_SHORT).show()

        db.close()


    }
    fun insert_bal(user: goal_logg){

        val db = this.writableDatabase
        var cv = ContentValues()

        //var ball = getBal()
        cv.put(balance, user.bal)

        val result = db.insert(tabel1, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Entered your balan", Toast.LENGTH_SHORT).show()

        db.close()

    }

    fun update_bal(user: goal_logg){

    }


    fun readData(): MutableList<goal_logg> {

        var con = this
        var list: MutableList<goal_logg> = ArrayList()

        val db = this.readableDatabase
        val query = "select * from " + tablef
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var user = goal_logg()
                user.name = result.getString(result.getColumnIndex(namef))
                user.description = result.getString(result.getColumnIndex(desc))
                user.amount = result.getFloat(result.getColumnIndex(amount))
                user.the_date = result.getString(result.getColumnIndex(datee))
                list.add(user)
            } while (result.moveToNext())
        }
        result.close()
        db.close()

        return list


    }

    fun getBal(): MutableList<goal_logg> {

        var con = this
        var list : MutableList<goal_logg> = ArrayList()

        var db = this.readableDatabase
        var query = "select * from " + tabel1
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do{
                var user = goal_logg()
                user.bal = result.getFloat(result.getColumnIndex(balance))
                list.add(user)
            }  while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
}
}
