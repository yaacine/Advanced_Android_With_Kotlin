package com.example.exo1


data class Word(val word: String, val photo :Int, val audio:Int, val video : Int, val type :String)

// type is audio / video
// if photo = 0 the image visibility is gone