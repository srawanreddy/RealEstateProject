package com.example.sravanreddy.realestateproject.data.remote

import com.example.sravanreddy.realestateproject.models.PropertyModel

interface IRemoteDataHelper {
    public fun getProperties(iRemoteDataHelperListener: IRemoteDataHelperListener, searchText : String)
    interface IRemoteDataHelperListener {
        public fun onSuccess(PropertyModels : PropertyModel)
        public fun onFailure(message : String)
    }
}