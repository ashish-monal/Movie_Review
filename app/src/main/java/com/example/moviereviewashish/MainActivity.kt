package com.example.moviereviewashish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private var movieName: ArrayList<String> = ArrayList()
    private var movieDescription: ArrayList<String> = ArrayList()
    private var movieRating: ArrayList<String> = ArrayList()
    private var movieImage: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.moviesReview)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager

        try {
            val obj = JSONObject(loadJSONFromAsset())
            val movieArray = obj.getJSONArray("movies")
            for (i in 0 until movieArray.length()) {
                val movieDetails = movieArray.getJSONObject(i)
                movieName.add(movieDetails.getString("title"))
                movieDescription.add(movieDetails.getString("content"))
                movieRating.add(movieDetails.getString("rating"))
                movieImage.add(movieDetails.getString("imageURL"))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val customAdapter =
            CustomAdapter(this@MainActivity, movieName, movieDescription, movieRating, movieImage)
        recyclerView.adapter = customAdapter
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("movie_details.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}