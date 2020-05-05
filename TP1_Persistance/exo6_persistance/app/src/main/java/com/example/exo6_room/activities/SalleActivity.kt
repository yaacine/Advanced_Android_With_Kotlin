package com.example.exo6_room.activities

import com.example.exo6_room.database.Seance
import com.example.exo6_room.database.SeanceDao
import kotlinx.android.synthetic.main.activity_module.*

class SalleActivity : ModuleActivity(){
    override fun initHint(){
        txt_filter.hint = " filterer par salle "
    }

    override fun getDataCusumized(dao: SeanceDao, value: String):List<Seance>{

        var tmp: Int? = value.toIntOrNull() ?: return dao.getAll()

        return dao.getBySalle(tmp!!)
    }
}