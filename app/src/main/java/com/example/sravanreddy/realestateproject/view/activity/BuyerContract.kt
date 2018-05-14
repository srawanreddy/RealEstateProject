package com.example.sravanreddy.realestateproject.view.activity

import com.example.sravanreddy.realestateproject.base.BasePresenter
import com.example.sravanreddy.realestateproject.base.BaseView
import com.example.sravanreddy.realestateproject.utils.dagger.AppComponent

interface BuyerContract {

    interface IView : BaseView<IPresenter>{
        fun setupActivityComponent(appComponent: AppComponent)
    }

    interface IPresenter : BasePresenter{
        fun startSearch(searchText: String)
    }

}