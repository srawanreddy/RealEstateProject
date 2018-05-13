package com.example.sravanreddy.realestateproject.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.base.BaseActivity
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.local.LocalDataSource
import com.example.sravanreddy.realestateproject.data.remote.RemoteDataSource
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.utils.dagger.AppComponent
import com.example.sravanreddy.realestateproject.view.fragment.*
import kotlinx.android.synthetic.main.activity_seller.*

class SellerActivity : BaseActivity() {
    private lateinit var propertyModels : ArrayList<PropertyModel>
    private lateinit var boundaryPresenter:BoundaryContract.IPresenter
    private lateinit var propertyListPresenter:PropertyListContract.IPresenter
    //@Inject
    lateinit var dataManager: DataManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
        dataManager = DataManager.getInstance(RemoteDataSource.getRemoteInstance(), LocalDataSource.getLocalInstance())

        val et_searchbar_seller:EditText = findViewById(R.id.et_searchbar_seller)

        val propertyListFragment = PropertyListFragment()
        val calledFrom = Bundle()
        calledFrom.putInt("Called From", 0)
        propertyListFragment.arguments = calledFrom

        propertyListPresenter = PresenterPropertyList(dataManager, propertyListFragment)

        supportFragmentManager.beginTransaction().replace(
                R.id.fragmentSellerContainer,
                propertyListFragment
        ).commit()


        button_search_seller.setOnClickListener{

            Log.d("SearchBtn", "Search Button clicked" + et_searchbar_seller.text.toString())
            val cityName:String = et_searchbar_seller.text.toString()
            val boundaryFragment = BoundaryFragment.newInstance(cityName)
            boundaryPresenter = BoundaryPresenter(boundaryFragment, this, dataManager)
            supportFragmentManager.beginTransaction().replace(R.id.fragmentSellerContainer, boundaryFragment).commit()
        }


    }
    override fun setupActivityComponent(appComponent: AppComponent) {
        appComponent.inject(this@SellerActivity)
    }

}
