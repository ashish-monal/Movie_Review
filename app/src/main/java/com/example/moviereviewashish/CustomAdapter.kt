package com.example.moviereviewashish

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class CustomAdapter(
    private var context: Context,
    private var title_of_movie: ArrayList<String>,
    private var content_of_movie: ArrayList<String>,
    private var rating_of_movie: ArrayList<String>,
    private var image_of_movie: ArrayList<String>
) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: CustomAdapter.MyViewHolder, position: Int) {

        holder.title.text = (title_of_movie[position])
        holder.content.text = (content_of_movie[position])
        holder.rating.text = (rating_of_movie[position])
        try {
            Glide.with(context)
                .load(image_of_movie[position])
                .error(R.drawable.error)
                .into(holder.image)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        holder.itemView.setOnClickListener { // display a Snake with movie name on item click
            val snackBar =
                Snackbar.make(it, "Clicked on ${title_of_movie[position]}", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null)
            snackBar.setActionTextColor(Color.GREEN)
            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(Color.GRAY)
            snackBar.show()
        }
    }

    override fun getItemCount(): Int {
        return title_of_movie.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById<View>(R.id.title_of_movie) as TextView
        var content: TextView = itemView.findViewById<View>(R.id.description_of_Movies) as TextView
        var rating: TextView = itemView.findViewById<View>(R.id.rating_of_movies) as TextView
        var image: ImageView = itemView.findViewById(R.id.movie_img) as ImageView
    }

}