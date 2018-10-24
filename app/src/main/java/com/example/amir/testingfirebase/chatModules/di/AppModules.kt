package com.example.amir.testingfirebase.chatModules.di

import com.example.amir.testingfirebase.ApplicationSchedulerProvider
import com.example.amir.testingfirebase.SchedulerProvider
import com.example.amir.testingfirebase.chatModules.chats.ChatViewModel
import com.example.amir.testingfirebase.chatModules.contacts.ContactsViewModel
import com.example.amir.testingfirebase.chatModules.repository.IRepository
import com.example.amir.testingfirebase.chatModules.repository.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Amir on 10/18/2018.
 */



val otherModules = module{
    single{
        ApplicationSchedulerProvider() as SchedulerProvider
    }

    single{
        provideRepository()
    }

    single{ provideGson() }

    single{ provideCompositeDisposable()}



}
val viewModelModules = module{

    viewModel { ChatViewModel(get()) }
    viewModel{ ContactsViewModel(get()) }
}

val appModule = listOf(viewModelModules, otherModules)


fun provideGson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

fun provideCompositeDisposable(): CompositeDisposable {
    return CompositeDisposable()
}

fun provideRepository(): Repository = Repository()



