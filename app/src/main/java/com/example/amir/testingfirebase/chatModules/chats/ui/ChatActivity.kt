package com.example.amir.testingfirebase.chatModules.chats.ui

import android.os.Bundle
import com.example.amir.testingfirebase.R
import com.example.amir.testingfirebase.chatModules.base.BaseActivity
import com.example.amir.testingfirebase.chatModules.util.extension.addFragmentToActivity
import com.example.amir.testingfirebase.databinding.ActivityChatBinding

/**
 * Created by Amir on 10/18/2018.
 */
class ChatActivity : BaseActivity<ActivityChatBinding>(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int = R.layout.activity_chat

    override fun initBinder() {

        addFragmentToActivity(ChatFragment.newInstance(),R.id.container, ChatFragment.TAG)
    }
}