package com.example.exo6_room.activities

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exo6_room.R
import com.example.exo6_room.adapter.SeanceAdapter
import com.example.exo6_room.database.AppDatabase
import com.example.exo6_room.database.Seance
import com.example.exo6_room.database.SeanceDao
import kotlinx.android.synthetic.main.activity_module.*
import android.os.AsyncTask
import android.text.Editable
import android.text.TextWatcher

open class ModuleActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var dao: SeanceDao? = null
    private var seances: MutableList<Seance>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module)

        initHint()

        val layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL

        val adapter = SeanceAdapter(listOf(),this)

        list_seance.adapter = adapter
        list_seance.layoutManager = layoutmanager


        txt_filter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                synch(p0.toString())
                toast_enable(this@ModuleActivity,p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })


        init()
    }

 //   public fun costumize(hintValue:String , )

    private fun synch(value:String){

        var activ = this

        val luncher = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                activ. db = AppDatabase.getInstance(activ)
                activ. dao =activ. db?.SeanceDao()

               // activ. seances =activ. dao?.getByModule(value) as MutableList<Seance>?

                activ. seances = activ.getDataCusumized(activ.dao!!,value) as MutableList<Seance>?

                return null
            }

            override fun onPostExecute(result: Void?) {
                activ. seances?.let { (list_seance.adapter as SeanceAdapter).update_list(it) }
                list_seance.adapter?.notifyDataSetChanged()
            }


        }
        luncher.execute()
    }

    open fun initHint(){
        txt_filter.hint = " filterer par module "
    }

    open fun getDataCusumized(dao: SeanceDao, value: String):List<Seance>{
        return dao.getByModule(value)
    }
    open fun toast_enable(con :Context,str:String){

    }


    private fun init() {
        var activ = this

        val luncher = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                activ. db = AppDatabase.getInstance(activ)
                activ. dao =activ. db?.SeanceDao()

                activ. seances =activ. dao?.getAll() as MutableList<Seance>?

                return null
            }

            override fun onPostExecute(result: Void?) {
                activ. seances?.let { (list_seance.adapter as SeanceAdapter).update_list(it) }
                list_seance.adapter?.notifyDataSetChanged()
            }


        }
        luncher.execute()
    }
}
