package com.example.sravanreddy.realestateproject.base

import android.icu.lang.UCharacter.GraphemeClusterBreak.T



interface BaseView<T> {

    fun setPresenter(presenter: T)
}