package com.example.sravanreddy.realestateproject.view.activity

import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.sravanreddy.realestateproject.LoginActivity
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.utils.adapters.ViewPagerAdapter

class PresenterLogin(loginActivity: LoginActivity) : LoginContract.IPresenter {

    private lateinit var dots: ArrayList<ImageView>
    private var dotsCount: Int = 0
    private var loginView: LoginContract.IView = loginActivity
    private var loginActivity = loginActivity
    override fun initViewPager(loginActivity: LoginActivity, sliderDots: LinearLayout?) {

        val viewPagerAdapter = ViewPagerAdapter(loginActivity)
        dotsCount = viewPagerAdapter.count
        dots = ArrayList()

        for (i in 0 until dotsCount) {
            dots.add(ImageView(loginActivity.baseContext))
            dots.get(i).setImageDrawable(ContextCompat.getDrawable(loginActivity.baseContext, R.drawable.non_anctive_dot))
            var param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            param.setMargins(8, 0, 8, 0)
            sliderDots!!.addView(dots[i], param)
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(loginActivity.baseContext, R.drawable.active_dot))


        loginView.showVp(viewPagerAdapter)

    }


    override fun onPageChanged(position: Int) {
        for (i in 0 until dotsCount) {
            dots!![i].setImageDrawable(ContextCompat.getDrawable(loginActivity.baseContext, R.drawable.non_anctive_dot))
        }
        dots!![position].setImageDrawable(ContextCompat.getDrawable(loginActivity.baseContext, R.drawable.active_dot))
    }
    override fun start() {

    }
}