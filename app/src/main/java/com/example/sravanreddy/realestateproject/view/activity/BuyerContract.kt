package com.example.sravanreddy.realestateproject.view.activity

import com.example.sravanreddy.realestateproject.base.BasePresenter
import com.example.sravanreddy.realestateproject.base.BaseView

interface BuyerContract {

    interface IView : BaseView<IPresenter>{

    }

    interface IPresenter : BasePresenter{
        fun startSearch(searchText: String)
    }

}