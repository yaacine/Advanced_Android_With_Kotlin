package com.example.exo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val words = listOf( "Apple" , "Beard" , "Car", "Donkey","Eye")

        val ex1 = Word("apple",R.drawable.buttonshape,R.raw.apple,0,"audio")
        val ex2 = Word("car",0,R.raw.car,0,"audio")
        val ex3 = Word("donkey",R.drawable.eyes_background,R.raw.donkey,0,"audio")
        val ex4 = Word("eye",R.drawable.ic_launcher_foreground,R.raw.eye,0,"audio")
        val ex5 = Word("beard",0,0,R.raw.beardvid,"video")
        val ex6 = Word("covid",0,0,R.raw.covid,"video")

        val exampleItem = ArrayList<Word>()
        //for (w in words)   exampleItem.add(ExampleItem(w))
        exampleItem.add(ex1)
        exampleItem.add(ex2)
        exampleItem.add(ex3)
        exampleItem.add(ex4)
        exampleItem.add(ex5)
        exampleItem.add(ex6)

        recycler_view.adapter = ExampleAdapter(exampleItem,this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }



    }

