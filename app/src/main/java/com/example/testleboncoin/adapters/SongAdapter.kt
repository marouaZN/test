package com.example.testleboncoin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testleboncoin.R
import com.example.testleboncoin.models.SongModel
import com.squareup.picasso.Picasso

class SongAdapter(val songList: ArrayList<SongModel>) : RecyclerView.Adapter<SongAdapter.ViewHolder>(){

    // Inflates the song layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    // Total number of songs
    override fun getItemCount(): Int {
        return songList.size
    }

    // Binds the data in each song
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = songList.get(position)
        holder.tvTitle?.text = item.title
        holder.tvAlbum?.text = "Album #" + item.albumId
        Picasso.get().load(item.thumbnailUrl).into(holder.ivPoster);
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView? = null
        var ivPoster: ImageView? = null
        var tvAlbum: TextView? = null

        init {
            ivPoster = itemView?.findViewById(R.id.idIvPoster)
            tvTitle = itemView?.findViewById(R.id.idTvLabel)
            tvAlbum = itemView?.findViewById(R.id.tvAlbum)
        }

    }
}
