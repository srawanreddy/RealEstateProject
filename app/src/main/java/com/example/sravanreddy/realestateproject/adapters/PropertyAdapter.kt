package com.example.sravanreddy.realestateproject.adapters

import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.common.Constants
import com.example.sravanreddy.realestateproject.data.local.PropertyDataBase
import com.example.sravanreddy.realestateproject.data.local.PropertyTable
import com.example.sravanreddy.realestateproject.models.Property
import com.example.sravanreddy.realestateproject.models.PropertyModel
import org.greenrobot.eventbus.EventBus


class PropertyAdapter(var properties: List<PropertyModel>,
                      var clickListener: View.OnClickListener, var mContext: Context, var calledFrom : Int) :
        RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    var propertyDatabase : PropertyDataBase

    init {
        propertyDatabase = PropertyDataBase.getInstance(mContext)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        return PropertyViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.property_item, parent, false))
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = properties[position]
        //holder.watches.text = property.watches.toString()
        //holder.favorites.text = property.favorites.toString()
        holder.type.text = property.getPropertyType()+""
        holder.cost.text = "$"+property.getPropertyCost()
        holder.details.text = property.getPropertyDesc()
        holder.address.text = property.getPropertyAddress1()+", \n"+ property.getPropertyAddress2()
        if(calledFrom == 0){
            holder.favBtn.setVisibility(View.INVISIBLE)
            holder.watchBtn.setVisibility(View.INVISIBLE)
        }
        else if(calledFrom == 1){
            holder.watches.setVisibility(View.INVISIBLE)
            holder.favorites.setVisibility(View.INVISIBLE)
        }
        holder.propertyImg.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                EventBus.getDefault().post(property)
            }
        })
        holder.favBtn.setOnClickListener( object : View.OnClickListener{
            override fun onClick(p0: View?) {
                InsertionAsyncTask(property).execute()
            }
        })



            Glide.with(mContext)
                    .load(property.getPropertyImage1())
                    .into(holder.propertyImg)

    }

    override fun getItemCount(): Int {
        return properties.size
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




    inner class InsertionAsyncTask(property : PropertyModel) : AsyncTask<Void, Void, Void>(){
        lateinit var propertyTable : PropertyTable
        init {
            propertyTable = PropertyTable(property.getPropertyId()!!,property.getPropetyName()!!, property.getPropertyType()!!
                   ,property.getPropertyCategory()!!, property.getPropertyAddress1()!!, property.getPropertyAddress2()!!, property.getPropertyZip()!!
                    ,property.getPropertyImage1()!! ,property.getPropertyImage2()!!,property.getPropertyImage3()!!, property.getPropertyLatitue()
                    , property.getPropertyLongitude(), property.getPropertyCost()!!, property.getPropertySize()!!, property.getPropertyDesc()!!
                    ,property.getPropertyPublishDate()!!, property.getPropertyModifyDate()!!, property.getPropertStatus()!!, property.getUserId()!! )
        }
        override fun doInBackground(vararg p0: Void?): Void? {

            propertyDatabase.propertyDao().insertPropertyForFav(propertyTable)
            return null
        }


    }




}