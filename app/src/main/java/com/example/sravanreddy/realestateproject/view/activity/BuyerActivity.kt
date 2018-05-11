package com.example.sravanreddy.realestateproject.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.base.BaseActivity
import com.example.sravanreddy.realestateproject.common.Constants
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.utils.dagger.AppComponent
import com.example.sravanreddy.realestateproject.view.fragment.MapViewFragment
import com.example.sravanreddy.realestateproject.view.fragment.PropertyListFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class BuyerActivity : BaseActivity(), BuyerContract.IView {

    private lateinit var ipresenter : BuyerContract.IPresenter
    private lateinit var mapviewtv : TextView
    private lateinit var listViewtv : TextView
    private lateinit var sorttv : TextView
    private lateinit var searchBar : EditText
    private lateinit var searchButton : ImageButton
    private lateinit var propertyModels : ArrayList<PropertyModel>

    @Inject
    lateinit var dataManager : DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer)
        ipresenter = PresenterBuyer(dataManager, this)

        mapviewtv = findViewById(R.id.tv_mapView_buyer)
        listViewtv = findViewById(R.id.tv_listView_buyer)
        sorttv = findViewById(R.id.tv_sort_buyer)
        searchBar = findViewById(R.id.edt_searchbar_buyer)
        searchButton = findViewById(R.id.button_search_buyer)
        searchButton.setOnClickListener(this::searchProperty)
        propertyModels = ArrayList()
        ipresenter.start()

        mapviewtv.setOnClickListener(object :View.OnClickListener {
            override fun onClick(p0: View?) {
                sorttv.visibility = View.INVISIBLE
                val propertiesData = Bundle()
                propertiesData.putParcelableArrayList("Property Model", propertyModels)
                val mapViewFragment =  MapViewFragment()
                mapViewFragment.arguments = propertiesData
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_results, mapViewFragment).commit()
            }
        })
        listViewtv.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                sorttv.visibility = View.VISIBLE
              val propertyListFragment = PropertyListFragment()
                val calledFrom = Bundle()
                calledFrom.putInt("Called From", 1)
                calledFrom.putParcelableArrayList("Property Model", propertyModels)
                propertyListFragment.arguments = calledFrom
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_results, propertyListFragment).commit()
            }
        })

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this@BuyerActivity)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this@BuyerActivity)
    }

    @Subscribe
    fun onPropertySelected(property : PropertyModel){
       var propertyBundle = Bundle()
        propertyBundle.putParcelable(Constants.PROPERTY_KEY, property)
        val propertyDetailsIntent = Intent(this@BuyerActivity , PropertyDetails ::class.java)
        propertyDetailsIntent.putExtras(propertyBundle)
        startActivity(propertyDetailsIntent)
    }

    fun loadFragment(propertModels : ArrayList<PropertyModel>){
        this.propertyModels = propertModels
        val propertyListFragment = PropertyListFragment()
        val calledFrom = Bundle()
        calledFrom.putParcelableArrayList("Property Model", propertModels)
        calledFrom.putInt("Called From", 1)
        propertyListFragment.arguments = calledFrom
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_results, propertyListFragment).commit()
    }

    fun searchProperty(view : View){
       val searchText = searchBar.text.toString()
        ipresenter.startSearch(searchText)
    }

    override fun setPresenter(presenter: BuyerContract.IPresenter) {
        ipresenter = presenter
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        appComponent.inject(this@BuyerActivity)
    }
}
