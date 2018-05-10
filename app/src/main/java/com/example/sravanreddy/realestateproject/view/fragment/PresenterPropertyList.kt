package com.example.sravanreddy.realestateproject.view.fragment

import android.util.Log
import com.example.sravanreddy.realestateproject.models.PropertyModel

class PresenterPropertyList(propertyListFragment: PropertyListFragment): PropertyListContract.IPresenterPropertList {

    private var propertyListFragment : PropertyListFragment = propertyListFragment
//    private var iDataManager : IDataManager = DataManager()
    private var propertyModels: ArrayList<PropertyModel> = ArrayList()

    fun onSuccess(PropertyModels: PropertyModel) {
        propertyModels.add(PropertyModels)
        Log.i("Size of List", "Size: "+propertyModels.size)
        propertyListFragment.setRecylcerView(propertyModels)

    }

    fun onFailure(message: String) {
    Log.i("Response Error", message)
    }

    override fun start() {
//    iDataManager.getProperties(this@PresenterPropertyList, "")
    }

    override fun startSearch(searchText: String) {
//        iDataManager.getProperties(this@PresenterPropertyList, searchText)
    }

}