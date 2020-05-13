package com.example.exo4_advenced

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter: TodosListAdapter
    var todosList =  arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TodosListAdapter(this, R.layout.row, todosList)
        todosListview.adapter = adapter

        buttonGet.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create<TodosService>(TodosService::class.java)
            service.getPost().enqueue(object: Callback<ArrayList<Todo>> {
                override fun onResponse(call: Call<ArrayList<Todo>>, response: retrofit2.Response<ArrayList<Todo>>?) {
                    if ((response != null) && (response.code() == 200)) {

                        todosListview.adapter = adapter
                        var todosList1 = ArrayList(response.body()!!.subList(0,30))
                        todosList.addAll(todosList1)
                        adapter.notifyDataSetChanged()
                        Toast.makeText(this@MainActivity, "Succès", Toast.LENGTH_LONG).show()

                    }

                }

                override fun onFailure(call: Call<ArrayList<Todo>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Echec", Toast.LENGTH_LONG).show()
                }




            })
        }
    }

}
