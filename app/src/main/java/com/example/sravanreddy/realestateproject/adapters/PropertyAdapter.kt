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
import com.example.sravanreddy.realestateproject.data.local.PropertyDataBase
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener
import org.greenrobot.eventbus.EventBus
import java.util.*
import kotlin.math.abs


class PropertyAdapter(var properties: List<PropertyModel>,
                      var clickListener: View.OnClickListener, var mContext: Context, var calledFrom: Int) :
        RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {


    var propertyDatabase : PropertyDataBase = PropertyDataBase.getInstance(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        return PropertyViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.property_item, parent, false))
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = properties[position]

        val random = Random()
        val cityNames: Array<String> = mContext.resources.getStringArray(R.array.city_names)
        val imgUrls: Array<String> = mContext.resources.getStringArray(R.array.dummy_pics)
        //val cName: Int = abs(random.nextInt() % cityNames.size)
        val imgIdx: Int = abs(random.nextInt() % imgUrls.size)
        holder.type.text = property.getPropertyType() + ""
        holder.cost.text = "$" + property.getPropertyCost()

        Firebase.setAndroidContext(mContext)
        val wishListReference = Firebase("https://realestateproject-882e2.firebaseio.com/" + "wish_List")
        wishListReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: FirebaseError?) {
                Log.i("Firebase Text", p0.toString())
            }
            override fun onDataChange(p0: DataSnapshot?) {
                for (i in p0!!.children) {
                    var propertyFirebase = i.getValue(PropertyModel::class.java)
                    if (property.getPropertyId()!!.equals(propertyFirebase.getPropertyId())) {
                        holder.favBtn.setImageResource(R.drawable.ic_heartred)
                        holder.favBtn.isClickable = false
                    }

                }
            }

        })

        val watchListReferences = Firebase("https://realestateproject-882e2.firebaseio.com/" + "watch_List")
        wishListReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: FirebaseError?) {
                Log.i("Firebase Text", p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot?) {


                for (i in p0!!.children) {
                    var propertyFirebase = i.getValue(PropertyModel::class.java)
                    if (property.getPropertyId()!!.equals(propertyFirebase.getPropertyId())) {
                        holder.watchBtn.setImageResource(R.drawable.ic_eyesred)
                        holder.watchBtn.isClickable = false
                    }

                }
            }

        })

        holder.type.text = property.getPropertyType() + ""
        holder.cost.text = "$" + property.getPropertyCost()
        holder.details.text = property.getPropertyDesc()
        holder.address.text = property.getPropertyAddress1() + ", \n" + property.getPropertyAddress2()
       // property.setPropertyAddress2(cityNames[cName])
        Glide.with(mContext)
                .load(imgUrls[imgIdx])
                .into(holder.propertyImg)

        if (calledFrom == 0) {
            holder.favBtn.setVisibility(View.INVISIBLE)
            holder.watchBtn.setVisibility(View.INVISIBLE)
        } else if (calledFrom == 1) {
            holder.watches.setVisibility(View.INVISIBLE)
            holder.favorites.setVisibility(View.INVISIBLE)
        }
        holder.propertyImg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                EventBus.getDefault().post(property)
            }
        })


        holder.watchBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                pushPropertyToFireBaseForWatchList(property)
                holder.watchBtn.setImageResource(R.drawable.ic_eyesred)
            }
        })
        holder.favBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                pushPropertyToFireBase(property)
                holder.favBtn.setImageResource(R.drawable.ic_heartred)
            }
        })
    }

    private fun pushPropertyToFireBaseForWatchList(property: PropertyModel) {
        Firebase.setAndroidContext(mContext)
        val wishListReference = Firebase("https://realestateproject-882e2.firebaseio.com/" + "watch_List")
        var childId: String = wishListReference.push().key
        property.setFirebaseDBId(childId)
        wishListReference.child(childId).setValue(property)
    }

    public fun pushPropertyToFireBase(property: PropertyModel) {
        Firebase.setAndroidContext(mContext)
        val wishListReference = Firebase("https://realestateproject-882e2.firebaseio.com/" + "wish_List")
        var childId: String = wishListReference.push().key
        property.setFirebaseDBId(childId)
        wishListReference.child(childId).setValue(property)

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


}

