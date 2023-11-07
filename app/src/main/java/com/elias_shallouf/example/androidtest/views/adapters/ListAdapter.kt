package com.elias_shallouf.example.androidtest.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elias_shallouf.example.androidtest.R
import com.elias_shallouf.example.androidtest.models.ListItem

class ListAdapter(
    private var list: MutableList<ListItem>,
    var searchText: String = ""
): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int
            = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateList(newList: MutableList<ListItem>) {
        this.list.clear()
        this.list.addAll(newList)
        this.notifyDataSetChanged()
    }

    class ViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view) {
        private var title: TextView = itemView.findViewById(R.id.item_title)
        private var image: ImageView = itemView.findViewById(R.id.item_image)

        fun bind(item: ListItem) {
            title.text = item.title

            Glide.with(itemView.context)
                .load(item.imageURL)
                .into(image)
        }
    }
}