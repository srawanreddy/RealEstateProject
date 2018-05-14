package com.example.sravanreddy.realestateproject

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.sravanreddy.realestateproject.adapters.ViewPagerAdapter
import com.example.sravanreddy.realestateproject.data.local.PropertyDataBase
import com.example.sravanreddy.realestateproject.view.activity.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import kotlin.collections.ArrayList


class LoginActivity : AppCompatActivity(), LoginContract.IView, View.OnClickListener {

    private var viewPager: ViewPager? = null
    private var sellerBtn: Button? = null
    private var buyerButton: Button? = null
    private var sliderDots: LinearLayout? = null
    private var dotsCount: Int = 0
    private var dots: ArrayList<ImageView>? = null
    private lateinit var loginPresenter: LoginContract.IPresenter
    private var mDb: PropertyDataBase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = PresenterLogin(this)

        viewPager = findViewById(R.id.vp_imageslider_login)
        sliderDots = findViewById(R.id.dots_panel)

        // sellerBtn = findViewById(R.id.button_seller_login)
        button_seller_login!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, SellerActivity::class.java)
            startActivity(intent)
        }

//        val pmodel = PropertyData(1, "0001", "Prair View apt", "Available",
//                "Human", "1840 Wessel Ct", "ddd", "099", "88909", "89a88ds",
//                "999", 89.88, -78.34, "900", "333", "abcd", "lemo", "000", "999", "888")

        //db.propertyDao().insertProperty(pmodel)
       // PropertyDataBase.getInstance(this).propertyDao().insertProperty(pmodel)
        buyerButton = findViewById(R.id.button_buyer_login)
        buyerButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                startActivity(Intent(this@LoginActivity, BuyerActivity::class.java))
            }
        })
        val viewPagerAdapter = ViewPagerAdapter(this)
        this.viewPager!!.adapter = viewPagerAdapter
        dotsCount = viewPagerAdapter.count
        dots = ArrayList()
        for (i in 0 until dotsCount) {
            val imageView = ImageView(this)
            dots!!.add(imageView)
        }
        print(dots!!.size)
        loginPresenter.initViewPager(this, sliderDots)

        btn_who.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_who -> {
                startActivity(Intent(this@LoginActivity, OcrActivity::class.java))
            }
        }
    }


    open class ImageSliderTimer(loginActivity: LoginActivity) : TimerTask() {
        private var loginActivity: LoginActivity? = null

        init {
            this.loginActivity = loginActivity
        }

        override fun run() {
            loginActivity!!.runOnUiThread {
                if (loginActivity!!.viewPager!!.currentItem < loginActivity!!.dotsCount - 1) {
                    loginActivity!!.viewPager!!.setCurrentItem(loginActivity!!.viewPager!!.currentItem + 1)
                } else {
                    loginActivity!!.viewPager!!.setCurrentItem(0)

                }
            }
        }

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

        val imageSliderTimer = LoginActivity.ImageSliderTimer(this)
        val timer = Timer()
        timer.scheduleAtFixedRate(imageSliderTimer, 2000, 4000)
    }

    override fun setPresenter(presenter: LoginContract.IPresenter) {
        loginPresenter = presenter
    }
}
