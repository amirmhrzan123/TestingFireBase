package com.example.amir.testingfirebase.chatModules.contacts

import com.example.amir.testingfirebase.chatModules.chats.model.User

/**
 * Created by Amir on 10/20/2018.
 */

data class ContactsModel(val name:String?="",
                    val number:String?="",
                    val user:String?="",
                         var showHide: Boolean?=false,
                         var userModel:User?=null)