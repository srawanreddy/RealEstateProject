package com.example.sravanreddy.realestateproject.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.models.propertybean.Property
import java.util.*
import kotlin.math.abs

class DialogDetail : DialogFragment() {
    private lateinit var proClass: String
    private lateinit var address: String
    private var lot: Double = 0.0
    private var bath: Float = 0.0f
    private var bed: Int = 0
    private lateinit var time: String
    private val imgArray = arrayOf("https://cdn.pixabay.com/photo/2016/06/24/10/47/render-1477041_960_720.jpg",
            "https://cdn.pixabay.com/photo/2018/05/11/21/01/old-house-3391636_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/03/30/04/14/house-2187170_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/03/30/04/14/house-2187170_960_720.jpg",
            "https://cdn.pixabay.com/photo/2013/06/08/21/14/rock-island-123406_960_720.jpg")


    override fun onStart() {
        super.onStart()
        val window = dialog.window
        val params = window!!.attributes
        params.gravity = Gravity.CENTER
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = params
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //dialog rootView
        val v = inflater.inflate(R.layout.dialog_detail, container, false)

        getData()

        initView(v)

        return v
    }


    private fun getData() {
        proClass = arguments!!.getString("class")
        address = arguments!!.getString("address")
        lot = arguments!!.getDouble("lot")
        bath = arguments!!.getFloat("bath")
        bed = arguments!!.getInt("bed")
        time = arguments!!.getString("time")
    }

    private fun initView(v: View) {
        val img = v.findViewById<ImageView>(R.id.img)
        val classTv = v.findViewById<TextView>(R.id.tv_class)
        val addressTv = v.findViewById<TextView>(R.id.tv_address)
        val lotTv = v.findViewById<TextView>(R.id.tv_lot)
        val bathTv = v.findViewById<TextView>(R.id.tv_bath)
        val bedTv = v.findViewById<TextView>(R.id.tv_bed)
        val timeTv = v.findViewById<TextView>(R.id.tv_last_modify)


        classTv.text = "Class: $proClass"
        addressTv.text = "Address: $address"
        lotTv.text = "Lot: $lot Acre"
        bathTv.text = "Bath: $bath"
        bedTv.text = "Bed: $bed"
        timeTv.text = "Last Modified: $time"

        val random = Random()


        Glide.with(this).load(imgArray[abs(random.nextInt() % 5)]).into(img)
    }

    fun showDialog(fragmentManager: FragmentManager, tag: String) {
        if (dialog == null || !dialog.isShowing) {
            show(fragmentManager, tag)
        }
    }

    companion object {

        fun newInstance(property: Property): DialogDetail {
            val args = Bundle()
            args.putString("class", property.summary.propclass)
            args.putString("address", property.address.oneLine)
            args.putDouble("lot", property.lot.lotSize1)
            args.putFloat("bath", property.building.rooms.bathstotal)
            args.putInt("bed", property.building.rooms.beds)
            args.putString("time", property.vintage.lastModified)


            val dialogOrderDetail = DialogDetail()
            dialogOrderDetail.arguments = args
            return dialogOrderDetail
        }
    }
}