package my.aolika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import my.aolika.artbook.R
import my.aolika.roomdb.Art
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Art>() {
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var arts: List<Art>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row, parent, false)
        return ArtViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.art_row_image)
        val nameText = holder.itemView.findViewById<TextView>(R.id.art_row_title)
        val artistNameText = holder.itemView.findViewById<TextView>(R.id.art_row_artist)
        val yearText = holder.itemView.findViewById<TextView>(R.id.art_row_year)

        val art = arts[position]
        holder.itemView.apply {
            nameText.text = "Name: ${art.name}"
            artistNameText.text = "Artist Name: ${art.artistName}"
            yearText.text = "Year: ${art.year}"
            glide.load(art.imageUrl).into(imageView)
        }

    }

    override fun getItemCount(): Int {
        return arts.size
    }

    class ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}