package com.example.sravanreddy.realestateproject.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.common.Constants
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.view.fragment.ChatDialogFragment
import com.squareup.picasso.Picasso

class PropertyDetails : AppCompatActivity() {
    private lateinit var propertyTypeTv: TextView
    private lateinit var propertyCostTv: TextView
    private lateinit var propertyAddressTv: TextView
    private lateinit var propertyDescTv: TextView
    private lateinit var propertyImage : ImageView
    private lateinit var fab : FloatingActionButton
    lateinit var chatDialogFragment : ChatDialogFragment
    private var isFabClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_details)
        init()
        bindDataToView()

    }

    public fun init(){
        propertyImage = findViewById(R.id.imageView_propertyImage_propertyDetails)
        propertyTypeTv = findViewById(R.id.tv_propert_type_propertyDetails)
        propertyCostTv = findViewById(R.id.tv_propert_cost_propertyDetails)
        propertyAddressTv = findViewById(R.id.tv_propert_address_propertyDetails)
        propertyDescTv = findViewById(R.id.tv_propert_desc_propertyDetails)
        fab = findViewById(R.id.fab_chat_propertyDetails)
        fab.setOnClickListener(this::onClickListener)
        chatDialogFragment = ChatDialogFragment()
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

            //fab.setImageDrawable(getDrawable(R.drawable.ic_close_black_24dp))
            //fab.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
            chatDialogFragment.show(this.fragmentManager, "Chat Dialog")

    }
}
