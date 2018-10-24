package com.example.amir.testingfirebase.chatModules.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Amir on 10/13/2018.
 */
object SessionRepository {

    val currentUser: FirebaseUser?
        get() = FirebaseAuth.getInstance().currentUser

    val uid: String?
        get() = currentUser?.uid

    val isLogin: Boolean
        get() = FirebaseAuth.getInstance().currentUser != null

    fun isSelf(uid: String) = SessionRepository.uid == uid

  //  fun isSelf(message: Message) = isSelf(message.uid)

}