package com.example.mycontacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_child.view.*

class ContactAdapter(items : List<ContactData>, ctx: Context) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    private var list = items
    private var context = ctx


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val name: TextView = v.tv_name
        val number: TextView = v.tv_number
        //val profile = v.iv_profile!!
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_child,parent,false))
    }


}