package com.example.sravanreddy.realestateproject.view.activity

import android.util.Log
import android.widget.Toast
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.IDataManager
import com.example.sravanreddy.realestateproject.data.remote.IRemoteDataHelper
import com.example.sravanreddy.realestateproject.models.PropertyModel

class PresenterBuyer(buyerActivity: BuyerActivity) : BuyerContract.IPresenter, IRemoteDataHelper.IRemoteDataHelperListener {

    private var buyerActivity = buyerActivity
    private var iDataManager : IDataManager = DataManager()
    private var propertyModels: ArrayList<PropertyModel> = ArrayList()

    override fun onSuccess(PropertyModels: PropertyModel) {
        propertyModels.add(PropertyModels)
        Log.i("Size of List", "Size: "+propertyModels.size)
        buyerActivity.loadFragment(propertyModels)
    }

    override fun onFailure(message: String) {
    Toast.makeText(buyerActivity.baseContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun start() {
        propertyModels.clear()
        iDataManager.getProperties(this@PresenterBuyer, "")
    }

    override fun startSearch(searchText: String) {
        propertyModels.clear()
        iDataManager.getProperties(this@PresenterBuyer, searchText)
    }
}