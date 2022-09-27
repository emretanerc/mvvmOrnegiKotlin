package com.etcmobileapps.mvvmornegikotlin.service

import com.etcmobileapps.mvvmornegikotlin.model.UniversityModelElement
import io.reactivex.rxjava3.core.Single

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory

class APIUrl {

    private val BASE_URL = "http://universities.hipolabs.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(DataAPI::class.java)
    fun getData() : Single<List<UniversityModelElement>> {
        return api.getBaseData()
    }
}