package com.example.sravanreddy.realestateproject.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.adapters.FavouriteListAdapter
import com.example.sravanreddy.realestateproject.adapters.WatchListAdapter
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener

class WatchList : AppCompatActivity() {
    private var recyclerView : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_list)
        recyclerView = findViewById(R.id.RecyclerView_Watch_list)
        recyclerView!!.layoutManager = LinearLayoutManager(this@WatchList)
        getFireBaseData()
    }

    private fun getFireBaseData() : List<PropertyModel> {
        var propertyModels = ArrayList<PropertyModel>()
        Firebase.setAndroidContext(this@WatchList)
        val wishListReference = Firebase("https://realestateproject-882e2.firebaseio.com/"+"watch_List")
        wishListReference.addValueEventListener(object  : ValueEventListener {
            override fun onCancelled(p0: FirebaseError?) {
                Log.i("Firebase Text", p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot?) {


                for(i in p0!!.children){
                    var  property =  i.getValue(PropertyModel::class.java)
                    propertyModels.add(property)
                }
                setRecyclerView(propertyModels)
            }

        })

        return propertyModels
    }

    private fun setRecyclerView(propertyModels : List<PropertyModel>) {
        var watchListAdapter = WatchListAdapter(propertyModels, this@WatchList)
        recyclerView!!.adapter  = watchListAdapter
        watchListAdapter.notifyDataSetChanged()
    }
}
