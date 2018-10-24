package com.example.amir.testingfirebase.chatModules.chats

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.amir.testingfirebase.chatModules.chats.adapter.ChatAdapter
import com.example.amir.testingfirebase.chatModules.chats.model.ChatRooms
import com.example.amir.testingfirebase.chatModules.util.extension.loadImages

/**
 * Created by Amir on 10/18/2018.
 */
object ChatBindings {

    @BindingAdapter("listChatRooms")
    @JvmStatic
    fun setChatList(recyclerView: RecyclerView, chatList: List<ChatRooms>?) {
        with(recyclerView.adapter as ChatAdapter) {
            setChatRoomList(chatList)
        }
    }

    @BindingAdapter("dataChatRooms","imageNumber")
    @JvmStatic
    fun showHideImages(imageView: ImageView,data:ChatRooms,imageNumber:Int ){
        val userKeyList = ArrayList(data.users.maps.keys)

        if(imageNumber==2){
            if(data.users.maps.size>1){
                imageView.visibility = View.VISIBLE
                imageView.loadImages(data.users.maps.get(userKeyList[1])?.userImg)
            }else{
                imageView.visibility = View.GONE
            }
        }else if (imageNumber==3){
            if(data.users.maps.size>2){
                imageView.visibility = View.VISIBLE
                imageView.loadImages(data.users.maps.get(userKeyList[2])?.userImg)
            }else{
                imageView.visibility = View.GONE
            }
        }else {
            imageView.loadImages(data.users.maps.get(userKeyList[0])?.userImg)
        }
    }

    @BindingAdapter("dataChatRoom")
    @JvmStatic
    fun setUsetNameText(textview: TextView, data:ChatRooms){
        val userKeyList = ArrayList(data.users.maps.keys)
        if(data.users.maps.size==1){
            textview.text = data.users.maps.get(userKeyList[0])?.username
        }else if(data.users.maps.size==2)
            textview.text = data.users.maps.get(userKeyList[0])?.username +" and " +data.users.maps.get(userKeyList[1])?.username
        else if(data.users.maps.size==3)
            textview.text = data.users.maps.get(userKeyList[0])?.username+","+data.users.maps.get(userKeyList[1])?.username +" and "+
                            data.users.maps.get(userKeyList[2])
    }

    @BindingAdapter("searchFilter")
    @JvmStatic
    fun setSearchFilter(recyclerView: RecyclerView, filterText: String?) {
        with(recyclerView){
            filterText.let {
                (recyclerView.adapter as ChatAdapter).filter.filter(filterText)
            }
        }
    }

}