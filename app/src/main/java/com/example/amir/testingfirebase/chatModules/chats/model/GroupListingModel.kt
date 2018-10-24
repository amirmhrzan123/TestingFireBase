package com.example.amir.testingfirebase.chatModules.chats.model

import com.google.firebase.database.IgnoreExtraProperties

/**
 * Created by Amir on 10/18/2018.
 */

@IgnoreExtraProperties
class Users{
    var maps: LinkedHashMap<String,User?> = LinkedHashMap()
}

data class ChatRooms(val searchTerm:String?="",
                     val text:String?="",
                     val time:Long=0,
                     var users: Users = Users(),
                     var usermaps: HashMap<String,Any> = HashMap())

data class User(val profileMessage:String?="",
                val userImg: String?="",
                val username:String?="")
