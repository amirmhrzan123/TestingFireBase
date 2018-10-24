package com.example.amir.testingfirebase

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Amir on 10/13/2018.
 */


class ApplicationSchedulerProvider: SchedulerProvider{
    override fun io()= Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()

    override fun computation()= Schedulers.computation()

}