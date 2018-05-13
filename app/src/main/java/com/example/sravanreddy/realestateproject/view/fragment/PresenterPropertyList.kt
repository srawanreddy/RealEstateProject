package com.example.sravanreddy.realestateproject.view.fragment

import android.os.Bundle
import android.util.Log
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.IDataSource
import com.example.sravanreddy.realestateproject.models.PropertyModel

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
                Log.d("ModelNum", t.message)
            }

            override fun onSuccess(response: Any) {
                propertyModels = response as ArrayList<PropertyModel>
                //val calledFrom = Bundle()
                Log.d("ModelNum", propertyModels.size.toString())

                //calledFrom.putParcelableArrayList("Property Model", propertyModels)
                fragmentView.setRecylcerView(propertyModels)
            }
        }, "")
    }

    override fun startSearch(searchText: String) {
//        iDataManager.getProperties(this@PresenterPropertyList, searchText)
    }

}