package com.example.exo4_advenced

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class TodosListAdapter(
    var mCtx: Context, var resource: Int,
    var items: List<Todo>?
) : ArrayAdapter<Todo>(mCtx, resource, items as MutableList<Todo>) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        val imageView: ImageView = view.findViewById(R.id.iconIv)
        var idTodo: TextView = view.findViewById(R.id.idTodo)
        var idTodoUser: TextView = view.findViewById(R.id.idTodo)
        var titleTodo: TextView = view.findViewById(R.id.titleTodo)
        var statusBtn: TextView = view.findViewById(R.id.statusBtn)

        var todo: Todo? = items?.get(position)

        //       imageView.setImageDrawable(mCtx.resources.getDrawable(1,null))

        idTodoUser.text = todo?.userId?.toUpperCase()
        idTodo.text = todo?.id?.toUpperCase()
        titleTodo.text = todo?.title?.toUpperCase()


        if(todo?.completed == true) statusBtn.text = "T"
        else statusBtn.text = "F"



        return view
    }

}


