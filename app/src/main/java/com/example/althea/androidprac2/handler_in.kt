package com.example.althea.androidprac2

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast

val db_name_in = "bank004"
val table_in = "income"
val col_name = "name"
val col_num = "num"
val col_method = "method"
val col_date = "date"

class handler_in( var context: Context) : SQLiteOpenHelper(context, db_name_in, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "create table " + table_in + "(" +
                col_name + " varchar(100), " +
                col_num + " integer , " +
                col_method + " varchar(20) ," +
                col_date + " date" + ");";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    fun insertData(user: User) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(col_name, user.name_in)
        cv.put(col_num, user.num_in)
        cv.put(col_method, user.method_in)
        cv.put(col_date, user.in_date)
        val result = db.insert(table_in, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Entered into db", Toast.LENGTH_SHORT).show()

        db.close()


    }



    fun readData(): MutableList<User> {

        var con = this
        var list: MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "select * from " + table_in
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var user = User()
                user.name_in = result.getString(result.getColumnIndex(col_name))
                user.num_in = result.getInt(result.getColumnIndex(col_num))
                user.method_in = result.getString(result.getColumnIndex(col_method))
                user.in_date = result.getString(result.getColumnIndex(col_date))
                list.add(user)
            } while (result.moveToNext())
        }
        result.close()
        db.close()

        return list


    }

    fun readNum(): Int {

        var con = this
        var list: MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "select * from " + table_in
        val result = db.rawQuery(query, null)
        var sum = 0

        if (result.moveToFirst()) {
            do {
                var user = User()

                user.num_in = result.getInt(result.getColumnIndex(col_num))
                sum = sum + user.num_in
                list.add(user)
            } while (result.moveToNext())
        }
        result.close()
        db.close()

        return sum
    }

}