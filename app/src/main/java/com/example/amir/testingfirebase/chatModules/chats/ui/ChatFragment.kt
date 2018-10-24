package com.example.amir.testingfirebase.chatModules.chats.ui

import android.support.v7.widget.LinearLayoutManager
import com.example.amir.testingfirebase.R
import com.example.amir.testingfirebase.chatModules.base.BaseFragment
import com.example.amir.testingfirebase.chatModules.chats.ChatViewModel
import com.example.amir.testingfirebase.chatModules.chats.adapter.ChatAdapter
import com.example.amir.testingfirebase.chatModules.contacts.ContactsActivity
import com.example.amir.testingfirebase.databinding.FragmentChatBinding
import kotlinx.android.synthetic.main.toolbar_chat.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Amir on 10/18/2018.
 */
class ChatFragment:BaseFragment<FragmentChatBinding>() {

    private val chatViewModel : ChatViewModel by viewModel()


    companion object {
        const val TAG = "CHATFRAGMENT"

        fun newInstance(): ChatFragment {
            return ChatFragment()
        }
    }

    override fun getLayout(): Int = R.layout.fragment_chat

    override fun initBinder() {
        dataBinding.viewModel = chatViewModel.apply {
            getGroupList()
            initViews()
        }
    }

    fun initViews(){
        dataBinding.rvChatGroup.apply {
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            this.layoutManager = layoutManager
            adapter = chatViewModel.let {
                ChatAdapter(it)
            }
        }

        iv_add.setOnClickListener{
            ContactsActivity.newInstance(this.activity!!)
        }
    }




}