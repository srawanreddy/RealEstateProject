package com.example.sravanreddy.realestateproject.view.activity

import android.util.Log
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.IDataSource
import com.example.sravanreddy.realestateproject.models.PropertyModel

class PresenterSeller(dataManager: DataManager, sellerActivity: SellerActivity): SellerContract.IPresenter {

    private var mSellerActivity:SellerContract.IView = sellerActivity
    private var mDataManager:DataManager = dataManager
    private var propertyModels: ArrayList<PropertyModel> = ArrayList()
    override fun start() {
        propertyModels.clear()
        mDataManager.getProperties(object :IDataSource.NetworkCallBack{
            override fun onSuccess(response: Any, areaId: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(response: Any) {
                val propertyModelList = response as ArrayList<PropertyModel>
               // propertyModels.add(propertyModel)
                Log.d("Size of List", "Size: " + propertyModelList.size)
                mSellerActivity.loadFragment(propertyModelList)
            }
        }, "")
    }
}