package com.example.testleboncoin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testleboncoin.R
import com.example.testleboncoin.adapters.SongAdapter
import com.example.testleboncoin.getJsonDataFromAsset
import com.example.testleboncoin.models.SongModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var songs: ArrayList<SongModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data to populate the RecyclerView with
        songs = ArrayList()

        // read json file from assets folder
        val jsonFileString = getJsonDataFromAsset(applicationContext, "albums.json")

        val gson = Gson()
        // create an empty anonymous inner class
        val listPersonType = object : TypeToken<List<SongModel>>() {}.type

        // Converting from JSON String to a Data Class
        var songs: ArrayList<SongModel> = gson.fromJson(jsonFileString, listPersonType)

        // lay out items in a grid
        recyclerview.layoutManager = GridLayoutManager(this,2)

        // Create adapter
        val adapter = SongAdapter(songs)

        // Attach the adapter to the recyclerview to populate items
        recyclerview.adapter = adapter
    }

}