package com.example.exo6_room.activities

import com.example.exo6_room.database.Seance
import com.example.exo6_room.database.SeanceDao
import kotlinx.android.synthetic.main.activity_module.*

class GroupActivity : ModuleActivity(){
    override fun initHint(){
        txt_filter.hint = " filterer par Group "
    }

    override fun getDataCusumized(dao: SeanceDao, value: String):List<Seance>{
        return dao.getByGroup(value)
    }
}