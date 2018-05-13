package com.example.sravanreddy.realestateproject.utils.dagger

import com.example.sravanreddy.realestateproject.LoginActivity
import com.example.sravanreddy.realestateproject.view.activity.BuyerActivity
import com.example.sravanreddy.realestateproject.view.activity.SellerActivity
import com.example.sravanreddy.realestateproject.view.fragment.BoundaryFragment
import dagger.Component
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
@Component(modules = arrayOf(AppModule::class, MvpModule::class))
interface AppComponent {

    fun inject(loginActivity: LoginActivity)
//        fun inject(loginActivity: KClass<BuyerActivity>) {}
    fun inject(buyerActivity: BuyerActivity)

    fun inject(sellerActivity: SellerActivity)
    fun inject(boundaryFragment:  KClass<BoundaryFragment>)
}




