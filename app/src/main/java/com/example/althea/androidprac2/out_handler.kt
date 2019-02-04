package com.example.althea.androidprac2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val db_name_notIn = "bank003"
val table_out = "outgoing"
val col_name1 = "name"
val col_num1 = "num"
val col_method1 = "method"
val col_date1 = "date"

val tableb="budget"
val money="money"
val sdate="sdate"
val edate="edate"

class out_handler_sp( var context: Context) : SQLiteOpenHelper(context, db_name_notIn, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "create table if not exists " + table_out + "(" +
                col_name1 + " varchar(100), " +
                col_num1 + " real , " +
                col_method1 + " varchar(20) ," +
                col_date1 + " varchar(30)" + ");";

        db?.execSQL(createTable)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun insertData_sp(user: User_two) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(col_name, user.name_out)
        cv.put(col_num, user.num_out)
        cv.put(col_method, user.method_o)
        cv.put(col_date, user.o_date)
        val result = db.insert(table_out, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Entered into db", Toast.LENGTH_SHORT).show()

        db.close()
    }





    fun readData_sp(): MutableList<User_two> {

        var con = this
        var list: MutableList<User_two> = ArrayList()

        val db = this.readableDatabase
        val query = "select * from " + table_out
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var user2 = User_two()
                user2.name_out = result.getString(result.getColumnIndex(col_name))
                user2.num_out = result.getFloat(result.getColumnIndex(col_num))
                user2.method_o = result.getString(result.getColumnIndex(col_method))
                user2.o_date = result.getString(result.getColumnIndex(col_date))
                list.add(user2)
            } while (result.moveToNext())
        }
        result.close()
        db.close()

        return list


    }
//
//    fun readData_am(date1: String, date2: String): Array<User_two> {
//
//        var con = this
//
//        val db = this.readableDatabase
//        val query = "select * from " + table_out +" where "+ col_date+" between '"+ date1 +"' and '"+date2+"'"
//        val result = db.rawQuery(query, null)
//
//
//        var user2 = User_two()
//
//        return arrayOf(user2)
//        result.close()
//        db.close()
//
//
//
//    }
//
//
//    fun readData_tm(date1: String, date2: String): ArrayList<User_two> {
//
//        var con = this
//        var list: ArrayList<User_two> = ArrayList()
//
//        val db = this.readableDatabase
//        val query = "select * from " + table_out +" where "+ col_date+" between '"+ date1 +"' and '"+date2+"'"
//        val result = db.rawQuery(query, null)
//
//        if (result.moveToFirst()) {
//            do {
//                var user2 = User_two()
//
//                user2.o_date = result.getString(result.getColumnIndex(col_date))
//                list.add(user2)
//            } while (result.moveToNext())
//        }
//        result.close()
//        db.close()
//
//        return list
//
//
//    }

    fun readNum_sum(date1: String, date2: String): Float {

        var con = this
        var list: MutableList<User_two> = ArrayList()
        var sum = 0f
        val db = this.readableDatabase
        val query = "select * from " + table_out + " where "+ col_date+" between '"+ date1 +"' and '"+date2+"'"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var user2 = User_two()

                user2.num_out = result.getFloat(result.getColumnIndex(col_num))
                sum = sum + user2.num_out
                list.add(user2)
            } while (result.moveToNext())
        }
        result.close()
        db.close()

        return sum
    }

    fun readNum_sp(): Float {

        var con = this
        var list: MutableList<User_two> = ArrayList()
        var sum = 0f
        val db = this.readableDatabase
        val query = "select * from " + table_out
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var user2 = User_two()

                user2.num_out = result.getFloat(result.getColumnIndex(col_num))
                sum = sum + user2.num_out
                list.add(user2)
            } while (result.moveToNext())
        }
        result.close()
        db.close()

        return sum
    }
    }