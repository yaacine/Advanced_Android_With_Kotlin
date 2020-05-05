package com.example.exo6_room.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.exo6_room.R
import com.example.exo6_room.adapter.DataManager
import com.example.exo6_room.adapter.SeanceAdapter
import com.example.exo6_room.database.AppDatabase
import com.example.exo6_room.database.Seance
import com.example.exo6_room.database.SeanceDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_module.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var dao: SeanceDao? = null
    private var seances: MutableList<Seance>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setEvent(btn_module,ModuleActivity::class.java)
        setEvent(btn_enseignat,EnseignantActivity::class.java)
        setEvent(btn_group,GroupActivity::class.java)
        setEvent(btn_jour,JourActivity::class.java)
        setEvent(btn_salle,SalleActivity::class.java)
        setEvent(btn_semaine,SemaineActivity::class.java)


        btn_import.setOnClickListener {
               // save("hello world")

            // save a list in json
/*
          val t1 = Seance("sil1","2020-05-14",8,9,"HPC",5,"hadji")
            val t2 = Seance("sit2","2020-05-15",11,15,"SGBD",4,"benkrid")
            val t3 = Seance("sit1","2020-05-19",8,9,"TPGO",5,"zegour")
            val t4 = Seance("sit2","2020-05-22",8,9,"anad",4,"hamdad")
            val t5 = Seance("sit4","2020-05-16",8,9,"HPC",6,"hadji")
            val t6 = Seance("sil2","2020-05-17",11,15,"SGBD",4,"benkrid")
            val t7 = Seance("siq1","2020-05-14",8,9,"TPGO",5,"zegour")
            val t8 = Seance("sit3","2020-05-23",8,9,"anad",4,"hamdad")

            DataManager.interventionsList.add(t1)
            DataManager.interventionsList.add(t2)
            DataManager.interventionsList.add(t3)
            DataManager.interventionsList.add(t4)
            DataManager.interventionsList.add(t5)
            DataManager.interventionsList.add(t7)
            DataManager.interventionsList.add(t6)
            DataManager.interventionsList.add(t8)


            DataManager.filePath = filesDir.absolutePath+"/seances2.json"
            showToast(this,filesDir.absolutePath+"/seances2.json")
            DataManager.writeListToJSONFile()*/


            // import list from json

            @SuppressLint("StaticFieldLeak")
            var lun = object : AsyncTask<Void, Void, Void>() {

                override fun doInBackground(vararg params: Void?): Void? {
                    DataManager.filePath = filesDir.absolutePath+"/seances2.json"
                    DataManager.readJSONfromFile()
                    val ls = DataManager.interventionsList
                    dao?.reset()
                    ls.forEach { seance: Seance ->
                        dao?.inserer(seance)
                    }
                    return null
                }

                override fun onPostExecute(result: Void?) {
                    showToast(this@MainActivity," database imported ")
                }

            }.execute()
            /*
            * */
        }

        btn_reset.setOnClickListener {
            val t2 = Seance("sil1","2020-05-09",8,9,"BDM",5,"mostefai")
            val t22 = Seance("sil2","2020-05-10",8,9,"anad",4,"mostefai")
            val t222 = Seance("siq2","2020-05-09",8,9,"ALOG",4,"chbieb")
            val t2222 = Seance("sit3","2020-05-09",8,9,"anad",4,"hamdad")

           // dao?.reset()
           // dao.in
            @SuppressLint("StaticFieldLeak")
            var lun = object : AsyncTask<Void, Void, Void>() {

                override fun doInBackground(vararg params: Void?): Void? {
                    dao?.reset()
                    dao?.inserer(t222)
                    dao?.inserer(t22)
                    dao?.inserer(t2222)
                    dao?.inserer(t2)
                    return null
                }

                override fun onPostExecute(result: Void?) {
                    showToast(this@MainActivity," database resetted ")
                }

            }.execute()

        }
    }


    private fun setEvent(btn : Button, cls: Class<*>) {

        btn.setOnClickListener {
            val intent = Intent(this, cls)
            startActivity(intent)
        }

    }
    fun showToast(ctx: Context, message: CharSequence) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }

    private fun init() {
        var activ = this
        val luncher = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                activ. db = AppDatabase.getInstance(activ)
                activ. dao =activ. db?.SeanceDao()

                return null
            }
        }
        luncher.execute()
    }
    fun save( jsonFile: String) {
        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput("seances.json", Context.MODE_PRIVATE)
            fos.write(jsonFile.toByteArray())

            Toast.makeText(
                this, "Saved to $filesDir/seances.json",
                Toast.LENGTH_LONG
            ).show()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }


}


