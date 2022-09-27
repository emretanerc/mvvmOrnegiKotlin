package com.etcmobileapps.mvvmornegikotlin.service

import com.etcmobileapps.mvvmornegikotlin.model.UniversityModelElement
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DataAPI {
    //https://api.github.com/users/hadley/
    @GET("search?country=Turkey")
    fun getBaseData(): Single<List<UniversityModelElement>>
}