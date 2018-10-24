package com.example.amir.testingfirebase.chatModules.base

/**
 * Created by Amir on 10/19/2018.
 */
interface BaseCallback<T> {

    fun onSuccess(response:T)

    fun onError(message: String)
}