package com.example.sravanreddy.realestateproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.sravanreddy.realestateproject.utils.adapters.ViewPagerAdapter

import com.example.sravanreddy.realestateproject.view.activity.LoginContract
import com.example.sravanreddy.realestateproject.view.activity.PresenterLogin

import com.example.sravanreddy.realestateproject.view.activity.SellerActivity

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log


class LoginActivity : AppCompatActivity(), LoginContract.IView {


    private var  viewPager : ViewPager? = null
    private var sellerBtn: Button?=null
    private var sliderDots : LinearLayout? = null

    private var dotsCount: Int = 0
    private var dots: ArrayList<ImageView>? = null
    private lateinit var loginPresenter : LoginContract.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = PresenterLogin(this)

        viewPager = findViewById(R.id.vp_imageslider_login)
        sliderDots = findViewById(R.id.dots_panel)

        sellerBtn = findViewById(R.id.button_seller_login)
        sellerBtn!!.setOnClickListener{
            val intent = Intent(this@LoginActivity, SellerActivity::class.java)
            startActivity(intent)
        }
        var viewPagerAdapter = ViewPagerAdapter(this)
       this.viewPager!!.adapter = viewPagerAdapter
        dotsCount = viewPagerAdapter.count
        dots = ArrayList<ImageView>()
        for(i in 0 until dotsCount){
            var imageView = ImageView(this)
            dots!!.add( imageView)
        }
       print(dots!!.size)



        loginPresenter.initViewPager(this, sliderDots)

    }

    override fun showVp(viewPagerAdapter: ViewPagerAdapter) {
        viewPager!!.adapter = viewPagerAdapter

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                loginPresenter.onPageChanged(position)
            }
        })

        var imageSliderTimer = ImageSliderTimer(this)
        var timer = Timer()
        timer.scheduleAtFixedRate(imageSliderTimer, 2000, 4000)
    }


    override fun setPresenter(presenter: LoginContract.IPresenter) {
        loginPresenter = presenter
    }

    open class ImageSliderTimer(loginActivity: LoginActivity) : TimerTask() {
        private var loginActivity: LoginActivity? = null

        init {
            this.loginActivity = loginActivity
        }

        override fun run() {
            loginActivity!!.runOnUiThread(object : Runnable {
                override fun run() {
                    if (loginActivity!!.viewPager!!.currentItem < loginActivity!!.dotsCount - 1) {
                        loginActivity!!.viewPager!!.setCurrentItem(loginActivity!!.viewPager!!.currentItem + 1)
                    } else {
                        loginActivity!!.viewPager!!.setCurrentItem(0)

                    }
                }
            })
        }

    }
}
