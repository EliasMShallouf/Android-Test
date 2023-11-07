package com.elias_shallouf.example.androidtest.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.elias_shallouf.example.androidtest.R
import java.util.*

class CarouselAdapter(
    private val context: Context,
    private var images: MutableList<Int>
): PagerAdapter() {
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun isViewFromObject(view: View, obj: Any): Boolean
            = view == (obj as LinearLayout)

    override fun getCount(): Int
            = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = inflater.inflate(R.layout.carousel_item, container, false)

        val imageView = itemView.findViewById(R.id.image) as ImageView
        Glide.with(itemView.context)
            .load(images[position])
            .placeholder(R.mipmap.ic_launcher)
            .into(imageView)

        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any)
            = container.removeView(obj as LinearLayout)
}