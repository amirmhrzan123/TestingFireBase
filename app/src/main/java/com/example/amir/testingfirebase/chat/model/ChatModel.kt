package com.example.amir.testingfirebase.chat.model

import com.google.firebase.database.IgnoreExtraProperties

/**
 * Created by Amir on 10/13/2018.
 */


@IgnoreExtraProperties
class Users{
    var maps: HashMap<String,User?> = HashMap()
}

data class ChatRooms(val searchTerm:String?="",
                      val text:String?="",
                      val time:Long=0,
                       var users:Users= Users(),
                        var usermaps: HashMap<String,Any> = HashMap())

data class User(val profileMessage:String?="",
                val userImg: String?="",
                val username:String?="")

