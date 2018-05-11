package com.example.sravanreddy.realestateproject.view.activity

import android.util.Log
import android.widget.Toast
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.IDataSource
import com.example.sravanreddy.realestateproject.models.PropertyModel

class PresenterBuyer(dataManager: DataManager, buyerActivity: BuyerActivity) : BuyerContract.IPresenter {

    private var buyerActivity = buyerActivity
    private var mDataManager = dataManager
    private var propertyModels: ArrayList<PropertyModel> = ArrayList()


    override fun start() {
        propertyModels.clear()
        mDataManager.getProperties(object : IDataSource.NetworkCallBack {
            override fun onSuccess(response: Any, areaId: String) {

            }

            override fun onSuccess(response: Any) {
                val propertyModel = response as ArrayList<PropertyModel>
                //propertyModels.add(propertyModel)
             //   Log.i("Size of List", "Size: " + propertyModels.size)
                buyerActivity.loadFragment(propertyModel)

            }

            override fun onFailure(t: Throwable) {
                Toast.makeText(buyerActivity.baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

        }, "")
    }

    override fun startSearch(searchText: String) {
        propertyModels.clear()
//        mDataManager.getProperties(this@PresenterBuyer, searchText)
    }
}