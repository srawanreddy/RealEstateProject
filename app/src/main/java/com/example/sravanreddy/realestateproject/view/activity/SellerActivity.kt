package com.example.sravanreddy.realestateproject.view.activity

import android.os.Bundle
import android.util.Log
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.base.BaseActivity
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.utils.dagger.AppComponent
import com.example.sravanreddy.realestateproject.view.fragment.PropertyListFragment
import javax.inject.Inject

class SellerActivity : BaseActivity(), SellerContract.IView {
    private lateinit var ipresenter: SellerContract.IPresenter
    private lateinit var propertyModels : ArrayList<PropertyModel>
    @Inject
    lateinit var dataManager: DataManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
        ipresenter = PresenterSeller(dataManager, this)
        ipresenter.start()

    }


    fun loadFragment(propertModels : ArrayList<PropertyModel>){
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
