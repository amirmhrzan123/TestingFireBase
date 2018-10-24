package com.example.amir.testingfirebase.chatModules.repository

import android.provider.ContactsContract
import com.example.amir.testingfirebase.chatModules.base.BaseCallback
import com.example.amir.testingfirebase.chatModules.chats.model.ChatRooms
import com.example.amir.testingfirebase.chatModules.chats.model.User
import com.example.amir.testingfirebase.chatModules.contacts.ContactsModel
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Amir on 10/18/2018.
 */
interface IRepository {
    fun findUser(uid: String?): User?

    fun getChatRoomsLists(callBack: BaseCallback<List<ChatRooms>>)

    fun getContactLists(callBack:BaseCallback<List<ContactsModel>>)



}