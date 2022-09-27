package com.etcmobileapps.mvvmornegikotlin.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etcmobileapps.mvvmornegikotlin.model.UniversityModelElement
import com.etcmobileapps.mvvmornegikotlin.service.APIUrl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver


class MainViewModel: ViewModel() {
    val data = MutableLiveData<List<UniversityModelElement>>()
    val dataError = MutableLiveData<Boolean>()
    val dataLoading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val dataApiService = APIUrl()


    fun refreshData(){
        getDataFromApi()
    }


private fun getDataFromApi(){
    dataLoading.value= true
    disposable.add(
        dataApiService.getData()
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<UniversityModelElement>>(){
                override fun onSuccess(t: List<UniversityModelElement>) {
                    data.value=t
                    dataError.value=false
                    dataLoading.value=false
                }
                override fun onError(e: Throwable) {
                    dataLoading.value=false
                    dataError.value=true
                    e.printStackTrace()
                }
            })
    )
}


}
