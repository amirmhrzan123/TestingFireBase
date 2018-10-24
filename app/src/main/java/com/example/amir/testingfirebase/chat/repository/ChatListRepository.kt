package com.example.amir.testingfirebase


import android.util.Log
import com.example.amir.testingfirebase.chat.model.ChatRooms
import com.example.amir.testingfirebase.chat.model.User
import com.example.amir.testingfirebase.chat.model.Users
import com.example.amir.testingfirebase.chatModules.repository.SessionRepository
import com.google.firebase.database.*
import com.google.gson.Gson
import com.ringchat.RingChat.chat.repository.FirebaseData

/**
 * Created by Amir on 10/13/2018.
 */


class ChatListRepository {

    companion object {
        const val CHATROOMS = "chatRooms"
        const val MESSAGES = "messages"
        const val ONETOONECHATROOMS = "oneToOneChatRooms"
        const val USERCHATROOMS = "userChatRooms"
        const val USERS = "users"
    }




    val chatRoomList:MutableList<ChatRooms> = mutableListOf()

    val chatRoomIdReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance()
                .getReference(CHATROOMS)
    }

    val userInfo: DatabaseReference by lazy{
        FirebaseDatabase.getInstance()
                .getReference(USERS)
    }

    val firebaseData: FirebaseData

    init {
        firebaseData = FirebaseData(userInfo)
    }

    fun getList(){
       //val query =  chatRoomIdReference.child(USERS).child(SessionRepository.uid!!)
        val query = chatRoomIdReference.orderByChild(SessionRepository.uid!!).equalTo(true)
        Log.d("currentUerrs", SessionRepository.uid)
        query.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.d("firemessagee","cancel")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    chatRoomList.clear()
                    for(fields in snapshot.children){
                        val chatRooms:ChatRooms? = fields.getValue(ChatRooms::class.java)
                        Log.d("keyofusers",fields.key.toString())
                        val map : HashMap<String,User?> = HashMap()
                        for(uservalues in fields.child(USERS).children){
                            map.put(uservalues.key!!,find(uservalues.key!!))

                        }
                        val users = Users()
                        users.maps = map
                        chatRooms?.users = users
                        users.maps.size
                        Log.d("sizeofmap",map.size.toString())
                        chatRoomList.add(chatRooms!!)
                        val gson = Gson()

                        Log.d("printlist",gson.toJson(chatRoomList))
                    }


                }
                Log.d("print",chatRoomList.toString())
                Log.d("size",chatRoomList.size.toString())
            }

        })

        for(chatRooms in chatRoomList){
            for(userInformation in chatRooms.users.maps){
                var user = find(userInformation.key)
                Log.d("username",user?.username)
            }
        }
    }

    fun find(uid: String?): User? {
        return firebaseData.snapshots.find { it.key == uid }?.getValue(User::class.java)
    }




    val timeStamp: Long
        get() = System.currentTimeMillis()





}