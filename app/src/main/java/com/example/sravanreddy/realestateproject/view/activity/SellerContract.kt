package com.example.sravanreddy.realestateproject.view.activity

import com.example.sravanreddy.realestateproject.base.BasePresenter
import com.example.sravanreddy.realestateproject.base.BaseView
import com.example.sravanreddy.realestateproject.models.PropertyModel

interface SellerContract {
    interface IView : BaseView<IPresenter> {
        fun loadFragment(propertyModelList: ArrayList<PropertyModel>) {

        }

    }
    interface IPresenter : BasePresenter{

    }
}