package com.example.exo6_room.activities

import com.example.exo6_room.database.Seance
import com.example.exo6_room.database.SeanceDao
import kotlinx.android.synthetic.main.activity_module.*


class JourActivity : ModuleActivity(){
    override fun initHint(){
        txt_filter.hint = " filterer par jour  yyyy-MM-JJ"
    }

    override fun getDataCusumized(dao: SeanceDao, value: String):List<Seance>{

        return dao.getByDate(value)
    }
}