package com.example.mycontacts

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontacts.database.AppDatabase
import com.example.mycontacts.database.ContactData
import com.example.mycontacts.database.ContactDataDAO
import kotlinx.android.synthetic.main.contact_child.view.*

class ContactAdapter(items : List<ContactData>, ctx: Context) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    private var list = items
    private var context = ctx
    private var dao: ContactDataDAO? =  AppDatabase.getInstance(ctx)?.contactDao()





    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val name: TextView = v.tv_name
        val number: TextView = v.tv_number
        val email:TextView = v.tv_email!!
        val state:TextView = v.tv_state
        val btn_change:Button = v.btn_change

    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
        holder.email.text = list[position].email
        holder.state.text = if(list[position].send) " sychronisé " else " déconnecté "
        holder.btn_change.setOnClickListener {

            showToast(this.context,"chnged btn")
            val active = this ;
            val luncher = @SuppressLint("StaticFieldLeak")
            object : AsyncTask<Void, Void, Void>() {

                override fun doInBackground(vararg params: Void?): Void? {
                    if(list[position].send){
                        active.dao?.desActivateNumber(list[position].number)
                    }
                    else{
                        active.dao?.activateNumber(list[position].number)
                    }


                    return null
                }

                override fun onPostExecute(result: Void?) {
                    showToast(context,"contact added")
                    ( active.context as MainActivity ).init()
                }


            }
            luncher.execute()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_child,parent,false))
    }

    fun showToast(ctx: Context, message: CharSequence) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }



}