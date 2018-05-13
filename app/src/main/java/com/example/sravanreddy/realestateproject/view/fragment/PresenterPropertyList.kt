package com.example.sravanreddy.realestateproject.view.fragment

import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.IDataSource
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.view.fragment.PropertyListFragment.PropertyListFragment

class PresenterPropertyList(dataManager: DataManager, propertyListFragment: PropertyListContract.IView) : PropertyListContract.IPresenter {

    var fragmentView = propertyListFragment
    var propertyModels: ArrayList<PropertyModel> = ArrayList()
    var mDataManager = dataManager

    init {
        fragmentView.setPresenter(this)
    }

    override fun start() {
        propertyModels.clear()
        mDataManager.getProperties(object : IDataSource.NetworkCallBack {

            override fun onSubscribe() {
                //show progress dlg
            }

            override fun onSuccess(response: Any, areaId: String) {
            }

            override fun onFailure(t: Throwable) {
            }

            override fun onSuccess(response: Any) {
                propertyModels = response as ArrayList<PropertyModel>
                fragmentView.setRecylcerView(propertyModels)
            }
        }, "")
    }

    override fun startSearch(searchText: String) {
//        iDataManager.getProperties(this@PresenterPropertyList, searchText)
    }

}