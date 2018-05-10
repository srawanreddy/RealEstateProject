package com.example.sravanreddy.realestateproject.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.common.Constants
import com.squareup.picasso.Picasso

class ViewPagerAdapter (context: Context) : PagerAdapter(){
    var context : Context? = null
    var images : ArrayList<String>? = null
    var layoutInflater : LayoutInflater? = null

    init {
        this.context = context
        this.images = Constants.IMAGES
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return view == `object`
    }

    override fun getCount(): Int {
        return images!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
       val view =  layoutInflater!!.inflate(R.layout.layout_imageslider, null)
        val image = view.findViewById<ImageView>(R.id.ImageView_imageSlider)
        Picasso.get().load(images!![position]).fit().into(image)
        val viewPager : ViewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: View, position: Int, `object`: Any) {
        val viewPager : ViewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }
}