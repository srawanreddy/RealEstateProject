package com.example.sravanreddy.realestateproject.view.fragment

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.IDataManager
import com.example.sravanreddy.realestateproject.data.remote.IRemoteDataHelper
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.view.fragment.PropertyListFragment.PropertyListFragment

class PresenterPropertyList(propertyListFragment: PropertyListFragment): PropertyListContract.IPresenterPropertList, IRemoteDataHelper.IRemoteDataHelperListener {

    private var propertyListFragment : PropertyListFragment = propertyListFragment
    private var iDataManager : IDataManager = DataManager()
    private var propertyModels: ArrayList<PropertyModel> = ArrayList()
    override fun onSuccess(PropertyModels: PropertyModel) {
        propertyModels.add(PropertyModels)
        Log.i("Size of List", "Size: "+propertyModels.size)
        propertyListFragment.setRecylcerView(propertyModels)

    }

    override fun onFailure(message: String) {
    Log.i("Response Error", message)
    }

    override fun start() {
    iDataManager.getProperties(this@PresenterPropertyList, "")
    }

    override fun startSearch(searchText: String) {
        iDataManager.getProperties(this@PresenterPropertyList, searchText)
    }

}