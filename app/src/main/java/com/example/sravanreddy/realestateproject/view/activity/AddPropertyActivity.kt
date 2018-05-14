package com.example.sravanreddy.realestateproject.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.data.local.FireBaseHelper
import com.example.sravanreddy.realestateproject.models.PropertyData
import com.firebase.client.Firebase
import kotlinx.android.synthetic.main.activity_add_property.*


class AddPropertyActivity : AppCompatActivity() {
    var databaseProperties = FireBaseHelper.getInstance().getReference("properties")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)
        saveProperty.setOnClickListener {
            val name: String = property_name.text.toString()
            val type: String = property_type.text.toString()
            val add1: String = property_addr1.text.toString()
            val add2: String = property_addr2.text.toString()
            val lat: Double = property_lat.text.toString().toDouble()
            val lng: Double = property_lon.text.toString().toDouble()
            val zcode: String = property_zip.text.toString()
            val des: String = property_des.text.toString()
            if (name.isEmpty() || type.isEmpty() || add1.isEmpty() || add2.isEmpty() || lat.isNaN() || lng.isNaN() || zcode.isEmpty() || des.isEmpty()) {
                Toast.makeText(this, "No field can be empty!", Toast.LENGTH_SHORT).show()
            } else {
                val property = PropertyData(name, type, add1, add2, lat, lng, zcode, des)
                Firebase.setAndroidContext(this)
                val propertyListReference = Firebase("https://realestateproject-882e2.firebaseio.com/" + "property_List")
                var childId: String = propertyListReference.push().key
                property.setFirebaseDBId(childId)
                propertyListReference.child(childId).setValue(property)
//                val id: String = databaseProperties!!.push().key
//                databaseProperties!!.child(id).setValue(property)
                val sellerIntent = Intent(this, SellerActivity::class.java)
                startActivity(sellerIntent)
            }
        }

    }

}
