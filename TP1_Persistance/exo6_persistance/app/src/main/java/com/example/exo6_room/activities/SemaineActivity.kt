package com.example.exo6_room.activities

import android.content.Context
import android.widget.Toast
import com.example.exo6_room.database.Seance
import com.example.exo6_room.database.SeanceDao
import kotlinx.android.synthetic.main.activity_module.*
import java.text.SimpleDateFormat
//import java.time.LocalDate
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


class SemaineActivity : ModuleActivity() {

    override fun initHint(){
        txt_filter.hint = " la semaine à partir du yyyy-MM-JJ"
    }

    override fun getDataCusumized(dao: SeanceDao, value: String):List<Seance>{

        try {
            val date = LocalDate.parse(value, DateTimeFormatter.ISO_DATE)
            val date2 = date.plusDays(7)

            return dao.getBetweenDates(date.toString(),date2.toString())

        }
        catch (e:Exception){
            return dao.getAll()
        }


    }

    override fun toast_enable(con: Context, str: String) {

        try {
            val date = LocalDate.parse(str, DateTimeFormatter.ISO_DATE)
            val date2 = date.plusDays(7)
            Toast.makeText(con, "la semaine selectionné \n du $date \n au $date2", Toast.LENGTH_SHORT).show()
        }
        catch (e:Exception){

        }




    }

}