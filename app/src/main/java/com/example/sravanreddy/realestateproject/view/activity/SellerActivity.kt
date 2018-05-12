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
import com.example.sravanreddy.realestateproject.view.fragment.BoundaryFragment
import com.example.sravanreddy.realestateproject.view.fragment.BoundaryPresenter
import com.example.sravanreddy.realestateproject.view.fragment.PropertyListFragment
import kotlinx.android.synthetic.main.activity_seller.*
import javax.inject.Inject

class SellerActivity : BaseActivity(), SellerContract.IView {
    private lateinit var ipresenter: SellerContract.IPresenter
    private lateinit var propertyModels : ArrayList<PropertyModel>
    private lateinit var boundaryPresenter:BoundaryContract.IPresenter
    //@Inject
    lateinit var dataManager: DataManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
        dataManager = DataManager.getInstance(RemoteDataSource.getRemoteInstance(), LocalDataSource.getLocalInstance())
        ipresenter = PresenterSeller(dataManager, this)
        ipresenter.start()
        val et_searchbar_seller:EditText = findViewById(R.id.et_searchbar_seller)

        button_search_seller.setOnClickListener{

            Log.d("SearchBtn", "Search Button clicked" + et_searchbar_seller.text.toString())
            val cityName:String = et_searchbar_seller.text.toString()
            var boundaryFragment = BoundaryFragment.newInstance(cityName)
            boundaryPresenter = BoundaryPresenter(boundaryFragment, this, dataManager)
            supportFragmentManager.beginTransaction().replace(R.id.fragmentSellerContainer, boundaryFragment).commit()
        }


    }


    override fun loadFragment(propertModels : ArrayList<PropertyModel>){
        this.propertyModels = propertModels
        val propertyListFragment = PropertyListFragment()
        val calledFrom = Bundle()
        calledFrom.putParcelableArrayList("Property Model", propertModels)
        calledFrom.putInt("Called From", 0)
        Log.d("LoadFragmentSeller", "Load Fragment from Seller")
        propertyListFragment.arguments = calledFrom
        supportFragmentManager.beginTransaction().replace(R.id.fragmentSellerContainer, propertyListFragment).commit()
    }



    override fun setPresenter(presenter: SellerContract.IPresenter) {
       ipresenter = presenter
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        appComponent.inject(this@SellerActivity)
    }

}
