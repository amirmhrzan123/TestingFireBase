package com.example.amir.testingfirebase.chatModules.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.support.annotation.StringRes
import com.example.amir.testingfirebase.chatModules.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Amir on 10/18/2018.
 */
 abstract class BaseViewModel:ViewModel() {
    protected var compositeDisposable: CompositeDisposable = CompositeDisposable()
    internal val progressDialogEvent = SingleLiveEvent<Int>()
    internal val alertMessageEvent = SingleLiveEvent<String>()
    internal val confirmationDialogEvent = SingleLiveEvent<String>()
    val progressBarVisibility = ObservableBoolean(false)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun showProgressDialog(@StringRes message: Int = 0) {
        progressDialogEvent.value = message
    }

    protected fun hideProgressDialog() {
        progressDialogEvent.value = -1
    }

    protected fun showAlertDialog(@StringRes message: Int) {
       // alertMessageEvent.value = resources.getString(message)
    }

    protected fun showAlertDialog(message: String) {
        alertMessageEvent.value = message
    }

    protected fun showProgressBar() {
        progressBarVisibility.set(true)
    }

    protected fun hideProgressBar() {
        progressBarVisibility.set(false)
    }


    protected fun showConfirmationDialog(message: String) {
        confirmationDialogEvent.value = message
    }



}