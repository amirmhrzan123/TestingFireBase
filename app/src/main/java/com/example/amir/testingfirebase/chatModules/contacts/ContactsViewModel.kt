package com.example.amir.testingfirebase.chatModules.contacts

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.example.amir.testingfirebase.chatModules.base.BaseCallback
import com.example.amir.testingfirebase.chatModules.base.BaseViewModel
import com.example.amir.testingfirebase.chatModules.repository.Repository
import com.example.amir.testingfirebase.chatModules.util.SingleLiveEvent

/**
 * Created by Amir on 10/20/2018.
 */



class ContactsViewModel constructor(private val repository: Repository):BaseViewModel() {

    val listContacts : ObservableList<ContactsModel> = ObservableArrayList<ContactsModel>()

    val onInvitationEvent = SingleLiveEvent<ContactsModel>()

    val listContactsEvent = SingleLiveEvent<List<ContactsModel>>()

    val searchItem = ObservableField<String>("")

    val onAddContactEvent = SingleLiveEvent<Void>()

    val showHideRadio = ObservableBoolean(false)

    val memberClickEvent = SingleLiveEvent<ContactsModel>()

    val groupClickEvent = SingleLiveEvent<Void>()


    fun getContactsList(){
        repository.getContactLists(object:BaseCallback<List<ContactsModel>>{
            override fun onSuccess(response: List<ContactsModel>) {
                listContacts.clear()
                listContacts.addAll(response)
            }

            override fun onError(message: String) {
            }

        })
    }

    fun inviteForRingChat(contactsModel:ContactsModel){
        onInvitationEvent.value = contactsModel

    }

    fun onAddContacts(){
        onAddContactEvent.call()
    }

    fun onMemberClicked(data:ContactsModel){
        memberClickEvent.value = data

    }

    fun onGroupClicked():Boolean{
        if(showHideRadio.get()){
            showHideRadio.set(false)
            return false
        }else{
            showHideRadio.set(true)
            return true
        }

    }


}