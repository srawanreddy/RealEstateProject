package com.example.sravanreddy.realestateproject.view.fragment

import com.example.sravanreddy.realestateproject.base.BasePresenter
import com.example.sravanreddy.realestateproject.base.BaseView
import com.example.sravanreddy.realestateproject.models.PropertyModel

interface PropertyListContract {

    interface IView : BaseView<IPresenter>{
        fun setRecylcerView(propertyModels : ArrayList<PropertyModel>)
    }

    interface IPresenter : BasePresenter{
        fun startSearch(searchText : String);
    }
}