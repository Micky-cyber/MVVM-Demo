package com.demomvvmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    protected var compositeDisposable : CompositeDisposable = CompositeDisposable()

    var isLoading : MutableLiveData<Boolean> = MutableLiveData()
    var isError : MutableLiveData<Throwable> = MutableLiveData()
    var isFail : MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}