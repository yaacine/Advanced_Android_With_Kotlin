package com.example.exo6_room.adapter


import android.content.Context
import android.content.res.Configuration
import android.content.Intent as Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exo6_room.R
import com.example.exo6_room.database.Seance
import kotlinx.android.synthetic.main.seance_item.view.*

class SeanceAdapter(ls:List<Seance>,cntx:Context) : RecyclerView.Adapter<SeanceAdapter.MYViewHolder>() {
    var liste:List<Seance> = ls
    var con : Context = cntx


    inner class MYViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun set(seance:Seance){


            itemView.item_debut.text = seance.heure_debut.toString()+" h"
            itemView.item_fin.text = seance.heure_fin.toString()+" h"
            itemView.item_enseignant.text = seance.enseignant
            itemView.item_jour.text = seance.date
            itemView.item_salle.text = seance.salle.toString()
            itemView.item_group.text = seance.groupe
            itemView.item_module.text = seance.module



        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val item  =  LayoutInflater.from(con).inflate(R.layout.seance_item,parent,false)

        return MYViewHolder(item)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        val book = liste[position]
        holder.set(book)



    }
    public fun update_list(lz:List<Seance>){
        this.liste = lz ;
    }


}