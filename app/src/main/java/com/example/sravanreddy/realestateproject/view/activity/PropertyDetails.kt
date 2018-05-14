package com.example.sravanreddy.realestateproject.view.activity

import android.app.Notification
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.common.Constants
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.view.fragment.ChatDialogFragment
import com.squareup.picasso.Picasso
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.content.Context.NOTIFICATION_SERVICE
import android.app.PendingIntent
import android.content.Context
import android.content.Intent



class PropertyDetails : AppCompatActivity() {
    private lateinit var propertyTypeTv: TextView
    private lateinit var propertyCostTv: TextView
    private lateinit var propertyAddressTv: TextView
    private lateinit var propertyDescTv: TextView
    private lateinit var propertyImage : ImageView
    private lateinit var fab : FloatingActionButton
    private var calledFrom = 0
    lateinit var chatDialogFragment : ChatDialogFragment
    private var isFabClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_details)
        init()
        if(calledFrom == 0) bindDataToView()

    }

    public fun init(){
        propertyImage = findViewById(R.id.imageView_propertyImage_propertyDetails)
        propertyTypeTv = findViewById(R.id.tv_propert_type_propertyDetails)
        propertyCostTv = findViewById(R.id.tv_propert_cost_propertyDetails)
        propertyAddressTv = findViewById(R.id.tv_propert_address_propertyDetails)
        propertyDescTv = findViewById(R.id.tv_propert_desc_propertyDetails)
        fab = findViewById(R.id.fab_chat_propertyDetails)
        calledFrom = intent.getIntExtra("Called From", 0)
        chatDialogFragment = ChatDialogFragment()
        if(calledFrom == 1) chatDialogFragment.show(this.fragmentManager, "Chat Dialog")
        fab.setOnClickListener(this::onClickListener)

    }

    public fun bindDataToView(){
        val propertyModel : PropertyModel = intent.extras.getParcelable(Constants.PROPERTY_KEY)
        Picasso.get().load(propertyModel.getPropertyImage1()).into(propertyImage)
        propertyTypeTv.setText(propertyModel.getPropertyType())
        propertyCostTv.setText("$"+propertyModel.getPropertyCost())
        propertyAddressTv.setText(propertyModel.getPropertyAddress1()+" ,"+propertyModel.getPropertyAddress2()+" "+propertyModel.getPropertyZip())
        propertyDescTv.setText(propertyModel.getPropertyDesc())
    }

    public fun onClickListener(view: View?){

            chatDialogFragment.show(this.fragmentManager, "Chat Dialog")

    }
}
