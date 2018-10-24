package com.example.amir.testingfirebase.chatModules.chats

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.util.Log
import com.example.amir.testingfirebase.SchedulerProvider
import com.example.amir.testingfirebase.chatModules.base.BaseCallback
import com.example.amir.testingfirebase.chatModules.base.BaseViewModel
import com.example.amir.testingfirebase.chatModules.chats.model.ChatRooms
import com.example.amir.testingfirebase.chatModules.repository.Repository
import com.example.amir.testingfirebase.chatModules.util.SingleLiveEvent

/**
 * Created by Amir on 10/18/2018.
 */
class ChatViewModel constructor(private val repository: Repository):BaseViewModel() {

    //val chatRoomList = ObservableField<List<ChatRooms>>(emptyList())

    val chatRoomList : ObservableList<ChatRooms> = ObservableArrayList<ChatRooms>()

    val thirdImage = ObservableBoolean()

    val secondImage = ObservableBoolean()

    val groupListingEvent = SingleLiveEvent<Void>()

    val searchText = ObservableField<String>("")


    fun getGroupList() {

        repository.getChatRoomsLists(object : BaseCallback<List<ChatRooms>> {
            override fun onSuccess(response: List<ChatRooms>) {
                Log.d("successs","Success")
                chatRoomList.clear()
                chatRoomList.addAll(response)
            }


            override fun onError(message: String) {
                Log.d("eroor", message)
            }


        })
    }


    fun showHideSecondImage(show: Boolean){
        secondImage.set(show)
    }

    fun showHideThirdImage(show:Boolean){
        thirdImage.set(show)
    }


}