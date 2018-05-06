package com.example.sravanreddy.realestateproject.view.activity

import android.support.v4.view.ViewPager
import android.widget.LinearLayout
import com.example.sravanreddy.realestateproject.LoginActivity
import com.example.sravanreddy.realestateproject.base.BasePresenter
import com.example.sravanreddy.realestateproject.base.BaseView
import com.example.sravanreddy.realestateproject.utils.adapters.ViewPagerAdapter

interface LoginContract {

    interface IView : BaseView<IPresenter> {
        fun showVp(viewPagerAdapter: ViewPagerAdapter)

    }

    interface IPresenter : BasePresenter {
        fun initViewPager(loginActivity: LoginActivity, sliderDots: LinearLayout?)
        fun onPageChanged(position : Int)
    }
}