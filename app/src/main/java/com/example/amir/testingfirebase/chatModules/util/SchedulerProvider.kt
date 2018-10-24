package com.example.amir.testingfirebase

import io.reactivex.Scheduler

/**
 * Created by Amir on 10/13/2018.
 */


interface SchedulerProvider{
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}