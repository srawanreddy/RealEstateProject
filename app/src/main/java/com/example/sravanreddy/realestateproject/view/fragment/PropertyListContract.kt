package com.example.sravanreddy.realestateproject.view.fragment

import com.example.sravanreddy.realestateproject.base.BasePresenter
import com.example.sravanreddy.realestateproject.base.BaseView
import com.example.sravanreddy.realestateproject.models.PropertyModel

interface PropertyListContract {

    interface IViewPropertyList : BaseView<IPresenterPropertList>{
        public fun setRecylcerView(propertyModels : ArrayList<PropertyModel>)
    }

    interface IPresenterPropertList : BasePresenter{
        public fun startSearch(searchText : String);
    }
}