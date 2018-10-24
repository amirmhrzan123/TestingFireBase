package com.example.amir.testingfirebase.chatModules.repository

import android.renderscript.Sampler
import android.util.Log
import com.example.amir.testingfirebase.ChatListRepository
import com.example.amir.testingfirebase.chatModules.base.BaseCallback
import com.example.amir.testingfirebase.chatModules.chats.model.ChatRooms
import com.example.amir.testingfirebase.chatModules.chats.model.User
import com.example.amir.testingfirebase.chatModules.chats.model.Users
import com.example.amir.testingfirebase.chatModules.contacts.ContactsModel
import com.google.firebase.database.*
import com.google.gson.Gson
import com.ringchat.RingChat.chat.repository.FirebaseData
import io.reactivex.Observable

/**
 * Created by Amir on 10/18/2018.
 */
class Repository: IRepository {

    val gsons = Gson()

    companion object {
        const val CHATROOMS = "chatRooms"
        const val MESSAGES = "messages"
        const val ONETOONECHATROOMS = "oneToOneChatRooms"
        const val USERCHATROOMS = "userChatRooms"
        const val USERS = "users"
        const val CONTACTS = "contacts"
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

    val contactsList: DatabaseReference by lazy{
        FirebaseDatabase.getInstance()
                .getReference(CONTACTS)
                .child(SessionRepository.uid!!)

    }

    val firebaseDataUserInfo: FirebaseData

    init {
        firebaseDataUserInfo = FirebaseData(userInfo)
    }

    override fun getChatRoomsLists(callBack: BaseCallback<List<ChatRooms>>) {
        val query = chatRoomIdReference.orderByChild(SessionRepository.uid!!).equalTo(true)

        query.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                callBack.onError(p0.message)
                Log.d("cancell",p0.message)
            }


            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    chatRoomList.clear()
                    for(fields in snapshot.children){
                        val chatRooms: ChatRooms? = fields.getValue(ChatRooms::class.java)
                        Log.d("keyofusers",fields.key.toString())

                            val map : LinkedHashMap<String,User?> = LinkedHashMap()
                            for(uservalues in fields.child(ChatListRepository.USERS).children){
                                Log.d("uid",SessionRepository.uid)
                                Log.d("userKeys",uservalues.key)
                                if(!SessionRepository.uid.equals(uservalues.key)){
                                    map.put(uservalues.key!!,findUser(uservalues.key!!))
                                }
                            }
                            val users = Users()
                            users.maps = map
                            chatRooms?.users = users
                            chatRoomList.add(chatRooms!!)
                            val keyList = ArrayList(users.maps.keys)


                        val gson = Gson()
                        Log.d("printlist",gson.toJson(chatRoomList))
                    }


                }else{
                    Log.d("not existe","not exists")
                }
                Log.d("print",chatRoomList.toString())
                Log.d("size",chatRoomList.size.toString())
                callBack.onSuccess(chatRoomList)

            }


        })
    }

     override fun findUser(uid: String?): User? {
        return firebaseDataUserInfo.snapshots.find { it.key == uid }?.getValue(User::class.java)
    }

    override fun getContactLists(callBack: BaseCallback<List<ContactsModel>>) {
        val listContacts: MutableList<ContactsModel> = mutableListOf()
        contactsList.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.d("cancell","oncancel")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    listContacts.clear()
                    for(fields in p0.children){
                        val contacts: ContactsModel? = fields.getValue(ContactsModel::class.java)
                        if(!contacts?.user.isNullOrEmpty()){
                            contacts?.userModel = findUser(contacts?.user)
                        }
                        listContacts.add(contacts!!)
                    }
                }
                Log.d("printContacts",gsons.toJson(listContacts))
                return callBack.onSuccess(listContacts)

            }

        })
    }



}