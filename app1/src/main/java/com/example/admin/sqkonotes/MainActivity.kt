package com.example.admin.sqkonotes


import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.text.ClipboardManager
import android.view.Menu
import android.view.MenuItem

import android.widget.Toast
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_add_note.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*
import kotlinx.android.synthetic.main.row.view.*

class MainActivity : AppCompatActivity() {



    var listNotes = ArrayList<Note>()

    var mSharedPref: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSharedPref = this.getSharedPreferences("My_Data", Context.MODE_PRIVATE)

        var mSorting = mSharedPref!!.getString("Sort","newest")
        when(mSorting){
            "newest" -> loadQueryNewest("%")
            "oldest" -> loadQueryOldest("%")
            "ascending" -> loadQueryAscending("%")
            "descending" -> loadQueryDescending("%")
        }

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
    }

    override fun onResume() {
        super.onResume()

        var mSorting = mSharedPref!!.getString("Sort","newest")
        when(mSorting){
            "newest" -> loadQueryNewest("%")
            "oldest" -> loadQueryOldest("%")
            "ascending" -> loadQueryAscending("%")
            "descending" -> loadQueryDescending("%")
        }
    }

    private fun loadQueryAscending(title:String){
        var dbManager = DbManager(this)
        val projections = arrayOf("ID","Title","Description")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.Query(projections,"Title like ?",selectionArgs,"Title")
        listNotes.clear()

        //ascending
        if(cursor.moveToFirst()){

            do{
                val Id = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))

                listNotes.add(Note( Id, Title, Description))
            }while(cursor.moveToNext())
        }

        //adapter
        var myNotesAdapter = MyNotesAdapter(this, listNotes)
        //set adapter
        notesLv.adapter =myNotesAdapter

        //get total no of task from ListView

        val total = notesLv.count
        //actionbar
        val mActionBar = supportActionBar
        if(mActionBar != null){
            //set to actionbar as subtitle of actionbar
            mActionBar.subtitle ="You have $total notes(s) in list ...."
        }
    }

    private fun loadQueryDescending(title:String){
        var dbManager = DbManager(this)
        val projections = arrayOf("ID","Title","Description")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.Query(projections,"Title like ?",selectionArgs,"Title")
        listNotes.clear()

        //descending
        if(cursor.moveToLast()){

            do{
                val Id = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))

                listNotes.add(Note( Id, Title, Description))
            }while(cursor.moveToPrevious())
        }

        //adapter
        var myNotesAdapter = MyNotesAdapter(this, listNotes)
        //set adapter
        notesLv.adapter =myNotesAdapter

        //get total no of task from ListView

        val total = notesLv.count
        //actionbar
        val mActionBar = supportActionBar
        if(mActionBar != null){
            //set to actionbar as subtitle of actionbar
            mActionBar.subtitle ="You have $total notes(s) in list ...."
        }
    }

    private fun loadQueryNewest(title:String){
        var dbManager = DbManager(this)
        val projections = arrayOf("ID","Title","Description")
        val selectionArgs = arrayOf(title)
        //sort by ID
        val cursor = dbManager.Query(projections,"ID like ?",selectionArgs,"ID")
        listNotes.clear()


        if(cursor.moveToLast()){

            do{
                val Id = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))

                listNotes.add(Note( Id, Title, Description))
            }while(cursor.moveToPrevious())
        }

        //adapter
        var myNotesAdapter = MyNotesAdapter(this, listNotes)
        //set adapter
        notesLv.adapter =myNotesAdapter

        //get total no of task from ListView

        val total = notesLv.count
        //actionbar
        val mActionBar = supportActionBar
        if(mActionBar != null){
            //set to actionbar as subtitle of actionbar
            mActionBar.subtitle ="You have $total notes(s) in list ...."
        }
    }

    private fun loadQueryOldest(title:String){
        var dbManager = DbManager(this)
        val projections = arrayOf("ID","Title","Description")
        val selectionArgs = arrayOf(title)
        //sort by ID
        val cursor = dbManager.Query(projections,"ID like ?",selectionArgs,"ID")
        listNotes.clear()


        if(cursor.moveToFirst()){

            do{
                val Id = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))

                listNotes.add(Note( Id, Title, Description))
            }while(cursor.moveToNext())
        }

        //adapter
        var myNotesAdapter = MyNotesAdapter(this, listNotes)
        //set adapter
        notesLv.adapter =myNotesAdapter

        //get total no of task from ListView

        val total = notesLv.count
        //actionbar
        val mActionBar = supportActionBar
        if(mActionBar != null){
            //set to actionbar as subtitle of actionbar
            mActionBar.subtitle ="You have $total notes(s) in list ...."
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        //searchView

        val sv: SearchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView

        val sm =  getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadQueryAscending("%" + query + "%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                loadQueryAscending("%" + newText + "%")
                return false
            }

        });

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null){
            when(item.itemId){
                R.id.addNote->{
                    startActivity(Intent(this,AddNoteActivity::class.java))
                }
                R.id.action_sort->{
                   // Toast.makeText(this,"Settings", Toast.LENGTH_SHORT).show()
                    showSortDialog()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showSortDialog() {
        val sortOptions = arrayOf("Newest","Oldest","Title(Ascending)","Title(Descending)")
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Sort by")
        mBuilder.setIcon(R.drawable.ic_action_sort)
        mBuilder.setSingleChoiceItems(sortOptions,-1){
            dialogInterface, i ->
            if(i==0){
                //newest first
                Toast.makeText(this,"Newest",Toast.LENGTH_SHORT).show();
                val editor =mSharedPref!!.edit()
                editor.putString("Sort","newest")
                editor.apply()
                loadQueryNewest("%")
            }
            if(i==1){
                // oldest first
                Toast.makeText(this,"Oldest",Toast.LENGTH_SHORT).show();
                val editor =mSharedPref!!.edit()
                editor.putString("Sort","oldest")
                editor.apply()
                loadQueryOldest("%")
            }
            if(i==2){
                //title ascending
                Toast.makeText(this,"Title(Ascending)",Toast.LENGTH_SHORT).show();
                val editor =mSharedPref!!.edit()
                editor.putString("Sort","ascendning")
                editor.apply()
                loadQueryAscending("%")
            }
            if(i==3){
                //title descending
                Toast.makeText(this,"Title(Descending)",Toast.LENGTH_SHORT).show();
                val editor =mSharedPref!!.edit()
                editor.putString("Sort","descendning")
                editor.apply()
                loadQueryDescending("%")
            }
            dialogInterface.dismiss()

        }

        val mDialog = mBuilder.create()
        mDialog.show()
    }

    inner class MyNotesAdapter: BaseAdapter {
        var listNotesAdapter = ArrayList<Note>()
        var context: Context?=null

        constructor(context: Context, listNotesAdapter: ArrayList<Note>):super(){
            this.listNotesAdapter = listNotesAdapter
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //inflate layout row.xml
            var myView = layoutInflater.inflate(R.layout.row,null)
            var myNote = listNotesAdapter[position]
            myView.titleTv.text = myNote.nodeName
            myView.descTv.text = myNote.nodeDes
            //delete button click
            myView.deleteBtn.setOnClickListener {
                var dbManager = DbManager(this.context!!)
                var selectionArgs = arrayOf(myNote.nodeID.toString())
                dbManager.delete("ID=?",selectionArgs)
                loadQueryAscending("%")
            }
            //edit/update button click
            myView.editBtn.setOnClickListener{
                GoToUpdateFun(myNote)
            }

            //copy btn click
            myView.copyBtn.setOnClickListener {
                //get title
                val title = myView.titleTv.text.toString()
                //get description
                val desc = myView.descTv.text.toString()
                //concatinate
                val s = title +"\n" + desc
                val cb =getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cb.text= s //add to clipboard
                Toast.makeText(this@MainActivity,"Copied...",Toast.LENGTH_SHORT).show()

            }
            // share btn click
            myView.shareBtn.setOnClickListener {
                //get title
                val title = myView.titleTv.text.toString()
                //get description
                val desc = myView.descTv.text.toString()
                //concatinate
                val s = title +"\n" + desc
                //share intent
                var shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT,s)
                startActivity(Intent.createChooser(shareIntent,s))

            }
            return myView
        }

        override fun getItem(position: Int): Any {
            return listNotesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNotesAdapter.size
        }

    }

    private fun GoToUpdateFun(myNote: Note) {
        var intent = Intent(this,AddNoteActivity::class.java)
        intent.putExtra("ID", myNote.nodeID)  //put id
        intent.putExtra("name", myNote.nodeName)  //put name
        intent.putExtra("des",myNote.nodeDes)   //put description
        startActivity(intent) //start acivity
    }
}


