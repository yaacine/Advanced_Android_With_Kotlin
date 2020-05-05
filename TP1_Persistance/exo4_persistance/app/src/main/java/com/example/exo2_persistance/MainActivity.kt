package com.example.exo4_persistance

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.exo4_persistance.AddNoteActivity
import com.example.exo2_persistance.Data.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.noterow.view.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    var listNotes = ArrayList<Note>()
    lateinit var myNotesAdapter: MyNotesAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "notes-list.db"
        ).build()
        DataManager.dbReference = db
        myNotesAdapter = MyNotesAdapter(this, listNotes)
        notesLv.adapter = myNotesAdapter
        //Load from DB
        LoadQuery("%")
        myNotesAdapter.notifyDataSetChanged()

        }

    override fun onResume() {
        super.onResume()
        LoadQuery("%")
    }

    private fun LoadQuery(title: String) {
        // TODO : get all notes

        GlobalScope.launch {

            var myDataList = DataManager.dbReference.interventionDao().getAll()
            listNotes.clear()
            listNotes.addAll(myDataList)
        }.invokeOnCompletion {

            println("nbNotes ======>"+listNotes.size)
            var activity = (this as MainActivity)
            activity.runOnUiThread(java.lang.Runnable {
                activity.myNotesAdapter.notifyDataSetChanged()


            })

        }
        //adapter
        //set adapter

        myNotesAdapter.notifyDataSetChanged()

        //get total number of tasks from ListView
        val total = notesLv.count
        //actionbar
        val mActionBar = supportActionBar
        if (mActionBar != null) {
            //set to actionbar as subtitle of actionbar
            mActionBar.subtitle = "You have $total note(s) in list..."
        }
    }

    @SuppressLint("ServiceCast")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        //searchView
      /*  val sv: SearchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView

        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                LoadQuery("%" + query + "%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                LoadQuery("%" + newText + "%")
                return false
            }
        });
*/
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.addNote -> {
                    startActivity(Intent(this, AddNoteActivity::class.java))
                }
                R.id.action_settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    inner class MyNotesAdapter : BaseAdapter {
        var listNotesAdapter = ArrayList<Note>()
        var context: Context? = null

        constructor(context: Context, listNotesAdapter: ArrayList<Note>) : super() {
            this.listNotesAdapter = listNotesAdapter
            this.context = context
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //inflate layout row.xml
            var myView = layoutInflater.inflate(R.layout.noterow, null)
            val myNote = listNotesAdapter[position]
            myView.titleTv.text = myNote.titile
            myView.descTv.text = myNote.text
            //delete button click
            myView.deleteBtn.setOnClickListener {
             // TODO: delete
                GlobalScope.launch {

                    DataManager.dbReference.interventionDao().delete(myNote)
                    var myDataList = DataManager.dbReference.interventionDao().getAll()
                    listNotes.clear()
                    listNotes.addAll(myDataList)

                }.invokeOnCompletion {

                    var activity = (context as MainActivity)
                    activity.runOnUiThread(java.lang.Runnable {
                        activity.myNotesAdapter.notifyDataSetChanged()

                    })
                }

            }
            //edit//update button click
            myView.editBtn.setOnClickListener {
                GoToUpdateFun(myNote)
            }
            //copy btn click
            myView.copyBtn.setOnClickListener {
                //get title
                val title = myView.titleTv.text.toString()
                //get description
                val desc = myView.descTv.text.toString()
                //concatinate
                val s = title + "\n" + desc
                val cb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cb.text = s // add to clipboard
                Toast.makeText(this@MainActivity, "Copied...", Toast.LENGTH_SHORT).show()
            }
            //share btn click
            myView.shareBtn.setOnClickListener {
                //get title
                val title = myView.titleTv.text.toString()
                //get description
                val desc = myView.descTv.text.toString()
                //concatenate
                val s = title + "\n" + desc
                //share intent
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, s)
                startActivity(Intent.createChooser(shareIntent, s))
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
        var intent = Intent(this, AddNoteActivity::class.java)
        intent.putExtra("ID", myNote.id) //put id
        intent.putExtra("name", myNote.titile) //ut name
        intent.putExtra("des", myNote.text) //put description
        startActivity(intent) //start activity
    }

    }



