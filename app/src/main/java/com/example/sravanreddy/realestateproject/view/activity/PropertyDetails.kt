package com.example.sravanreddy.realestateproject.view.activity


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
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
    private var calledFrom = 0
    lateinit var chatDialogFragment : ChatDialogFragment

    private var isFabClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_details)
        init()
        if(calledFrom == 0|| calledFrom == 1 || calledFrom == 2) bindDataToView()

    }

     fun init() {
        propertyImage = findViewById(R.id.imageView_propertyImage_propertyDetails)
        propertyTypeTv = findViewById(R.id.tv_propert_type_propertyDetails)
        propertyCostTv = findViewById(R.id.tv_propert_cost_propertyDetails)
        propertyAddressTv = findViewById(R.id.tv_propert_address_propertyDetails)
        propertyDescTv = findViewById(R.id.tv_propert_desc_propertyDetails)
        fab = findViewById(R.id.fab_chat_propertyDetails)
        calledFrom = intent.getIntExtra("Called From", 0)
        chatDialogFragment = ChatDialogFragment()
        if(calledFrom == 3) chatDialogFragment.show(this.fragmentManager, "Chat Dialog")
        fab.setOnClickListener(this::onClickListener)

    }

     fun bindDataToView() {
         val imgUrls: Array<String> = this.resources.getStringArray(R.array.dummy_pics)
         val propertyModel: PropertyModel = intent.extras.getParcelable(Constants.PROPERTY_KEY)
         Glide.with(this)
                 .load(imgUrls[0])
                 .into(propertyImage)
        propertyTypeTv.setText(propertyModel.getPropertyType())
        propertyCostTv.setText("$" + propertyModel.getPropertyCost())
        propertyAddressTv.setText(propertyModel.getPropertyAddress1() + " ," + propertyModel.getPropertyAddress2() + " " + propertyModel.getPropertyZip())
        propertyDescTv.setText(propertyModel.getPropertyDesc())
    }


    fun onClickListener(view: View?){
            if(calledFrom ==1)
            chatDialogFragment.show(this.fragmentManager, "Chat Dialog")
        else if(calledFrom == 2){
                startActivity(Intent(this@PropertyDetails, SellerChat::class.java))
            }


    }
}
