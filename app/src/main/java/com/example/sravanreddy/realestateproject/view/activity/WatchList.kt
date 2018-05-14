package com.example.sravanreddy.realestateproject.view.activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
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

    private fun getFireBaseData()  {
        var propertyModels: ArrayList<PropertyModel>
        Firebase.setAndroidContext(this@WatchList)
        val wishListReference = Firebase("https://realestateproject-882e2.firebaseio.com/"+"watch_List")
        wishListReference.addValueEventListener(object  : ValueEventListener {
            override fun onCancelled(p0: FirebaseError?) {
                Log.i("Firebase Text", p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot?) {
                propertyModels = ArrayList<PropertyModel>()
                var isExists : Boolean = false
                for(i in p0!!.children){
                    var  property =  i.getValue(PropertyModel::class.java)


                    for(j in propertyModels){
                        if(!property.getPropertyId().equals(j.getPropertyId()))
                          isExists =  false
                        else{
                           isExists = true

                            var id = j.getPropertyId()
                            j.setPropertyId("*"+id)
                        }


                    }
                    if(!isExists) propertyModels.add(property)


                }
                notifyUser()
                setRecyclerView(propertyModels)
            }

        })

        //return propertyModels
    }

    private fun setRecyclerView(propertyModels : List<PropertyModel>) {
        var watchListAdapter = WatchListAdapter(propertyModels, this@WatchList)
        recyclerView!!.adapter  = watchListAdapter
        watchListAdapter.notifyDataSetChanged()
    }


    private fun notifyUser() {
        val mBuilder = NotificationCompat.Builder(applicationContext, "notify_001")
        // val ii = Intent(getApplicationContext(), RootActivity::class.java)
        //  val pendingIntent = PendingIntent.getActivity(mContext, 0, ii, 0)

        val intent = Intent(this@WatchList, WatchList::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this@WatchList, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)


        val bigText = NotificationCompat.BigTextStyle()
        //bigText.bigText(verseurl)
        bigText.setBigContentTitle("1 New Notification")
        bigText.setSummaryText("Item in WatchList has Updated")

        mBuilder.setContentIntent(pendingIntent)
        mBuilder.setSmallIcon(R.drawable.ic_comment_black_24dp)
        mBuilder.setContentTitle("1 New Notification")
        mBuilder.setContentText("Item in WatchList has Updated")
        mBuilder.priority = Notification.PRIORITY_MAX
        mBuilder.setStyle(bigText)

        val mNotificationManager = this@WatchList.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("notify_001",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT)
            mNotificationManager.createNotificationChannel(channel)
        }

        mNotificationManager.notify(0, mBuilder.build())
    }
}
