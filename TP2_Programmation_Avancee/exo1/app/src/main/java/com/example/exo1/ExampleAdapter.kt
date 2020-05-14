package com.example.exo1


import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*


class ExampleAdapter(ls:List<Word>,cntx:Context) : RecyclerView.Adapter<ExampleAdapter.MYViewHolder>() {
    var liste:List<Word> = ls
    var con : Context = cntx


    inner class MYViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun set(book:Word){

            itemView.word.text = book.word

            itemView.detail_btn.setOnClickListener {


                    val intent = Intent()
                    intent.setClass(con, DetailActivity::class.java)

                    intent.putExtra("type", book.type)
                    intent.putExtra("audio", book.audio)
                    intent.putExtra("video", book.video)
                    intent.putExtra("photo", book.photo)
                    intent.putExtra("word", book.word)

                    con.startActivity(intent)

            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val item  =  LayoutInflater.from(con).inflate(R.layout.example_item,parent,false)

        return MYViewHolder(item)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        val book = liste[position]
        holder.set(book)



    }


}