package com.example.sravanreddy.realestateproject.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.firebase.client.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class WatchListAdapter(propertyModels: List<PropertyModel>, context : Context) : RecyclerView.Adapter<WatchListAdapter.MyViewHolder>() {


    var propertyModels : MutableList<PropertyModel>
    var context : Context
    init {
        this.propertyModels = propertyModels as MutableList<PropertyModel>
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListAdapter.MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.favourites_list_item, parent, false)
        return WatchListAdapter.MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return propertyModels.size
    }

    override fun onBindViewHolder(holder: WatchListAdapter.MyViewHolder, position: Int) {

        var propertyModel = propertyModels[position]
        holder.type.setText(propertyModel.getPropertyType())
        holder.cost.setText(propertyModel.getPropertyCost())
        holder.details.setText(propertyModel.getPropertyDesc())
        holder.address.text = propertyModel.getPropertyAddress1()+", \n"+ propertyModel.getPropertyAddress2()
        Picasso.get().load(propertyModel.getPropertyAddress1()).into(holder.propertyImg)

        holder.remove.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                removeItem(propertyModel)
                notifyDataSetChanged()
                propertyModels.removeAt(position)
            }
        })

    }

    private fun removeItem(propertyModel : PropertyModel) {
        Firebase.setAndroidContext(context)
        val wishListReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("watch_List").child(propertyModel.getFirebaseDBId())
        wishListReference.removeValue()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var type: TextView
        var cost: TextView
        var details: TextView
        var address: TextView
        var propertyImg: ImageView
        var remove : ImageButton

        init {

            itemView.tag = this
            type = itemView.findViewById(R.id.property_type_favourites) as TextView
            cost = itemView.findViewById(R.id.property_price_favourites) as TextView
            details = itemView.findViewById(R.id.property_room_area_favourites) as TextView
            address = itemView.findViewById(R.id.property_address_favourites) as TextView
            propertyImg = itemView.findViewById(R.id.property_image_favourites) as ImageView
            remove = itemView.findViewById(R.id.remove_favouriteList)
        }
    }
}