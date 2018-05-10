package com.example.sravanreddy.realestateproject.data

import com.example.sravanreddy.realestateproject.data.remote.IRemoteDataHelper
import com.example.sravanreddy.realestateproject.data.remote.RemoteDataHelper

class DataManager : IDataManager{

     var iRemoteDataHelper : IRemoteDataHelper = RemoteDataHelper()


    override fun getProperties(iRemoteDataHelperListener: IRemoteDataHelper.IRemoteDataHelperListener, searchText : String) {
    iRemoteDataHelper.getProperties(iRemoteDataHelperListener, searchText)
    }
}