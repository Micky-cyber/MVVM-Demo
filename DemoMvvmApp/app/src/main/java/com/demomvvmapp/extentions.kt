package com.demomvvmapp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.autoDispose(compositeDisposable: CompositeDisposable){
    compositeDisposable.add(this)
}