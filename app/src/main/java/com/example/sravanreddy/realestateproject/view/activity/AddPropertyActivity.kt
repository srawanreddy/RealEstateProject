package com.example.sravanreddy.realestateproject.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.data.local.FireBaseHelper
import com.example.sravanreddy.realestateproject.models.PropertyData
import kotlinx.android.synthetic.main.activity_add_property.*

class AddPropertyActivity : AppCompatActivity() {
    var databaseProperties = FireBaseHelper.getInstance().getReference("properties")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)
        saveProperty.setOnClickListener{
            val name:String = property_name.text.toString()
            val type:String = property_type.text.toString()
            val add1:String = property_addr1.text.toString()
            val add2:String = property_addr2.text.toString()
            val lat:Double = property_lat.text.toString().toDouble()
            val lng:Double = property_lon.text.toString().toDouble()
            val zcode:String = property_zip.text.toString()
            val des:String = property_des.text.toString()
            val id:String = databaseProperties!!.push().key
            val property:PropertyData = PropertyData(name, type, add1, add2, lat, lng, zcode, des)
            databaseProperties!!.child(id).setValue(property)
        }
    }
}
