package com.example.sravanreddy.realestateproject.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.models.Property


class PropertyAdapter(var properties: List<Property>,
                      var clickListener: View.OnClickListener, var mContext: Context, var calledFrom : Int) :
        RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        return PropertyViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.property_item, parent, false))
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = properties[position]
        holder.watches.text = property.watches.toString()
        holder.favorites.text = property.favorites.toString()
        if(calledFrom == 0){
            holder.favBtn.setVisibility(View.INVISIBLE)
            holder.watchBtn.setVisibility(View.INVISIBLE)
        }
        else if(calledFrom == 1){
            holder.watches.setVisibility(View.INVISIBLE)
            holder.favorites.setVisibility(View.INVISIBLE)
        }
        Log.d("IMGURL", property.imgUrl1)
        if (property.imgUrl1.isNotEmpty()) {
            Glide.with(mContext)
                    .load(property.imgUrl1)
                    .into(holder.propertyImg)
        }
    }

    override fun getItemCount(): Int {
        return properties.size
    }


    fun getPhoto(adapterPosition: Int): Property {
        return properties[adapterPosition]
    }

    inner class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder
    (itemView) {
        var type: TextView
        var cost: TextView
        var details: TextView
        var address: TextView
        var watches: TextView
        var favorites: TextView
        var propertyImg: ImageView
        var favBtn: ImageButton
        var watchBtn: ImageButton

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(clickListener)
            }
            itemView.tag = this
            type = itemView.findViewById(R.id.property_type) as TextView
            cost = itemView.findViewById(R.id.property_price) as TextView
            details = itemView.findViewById(R.id.property_room_area) as TextView
            address = itemView.findViewById(R.id.property_address) as TextView
            watches = itemView.findViewById(R.id.watches) as TextView
            favorites = itemView.findViewById(R.id.favorites) as TextView
            favBtn = itemView.findViewById(R.id.favorite_white_label) as ImageButton
            watchBtn = itemView.findViewById(R.id.watch_white_label) as ImageButton
            propertyImg = itemView.findViewById(R.id.property_image) as ImageView
        }
    }
}