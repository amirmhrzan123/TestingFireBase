package com.example.amir.testingfirebase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.amir.testingfirebase.chatModules.repository.SessionRepository
import kotlinx.android.synthetic.main.activity_group_listing.*

/**
 * Created by Amir on 10/14/2018.
 */
class GroupListing :AppCompatActivity(){

    private val linearLayoutManager by lazy{
        LinearLayoutManager(this)
    }

    private val chatListingRepository by lazy{
        ChatListRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_listing)
        recycler_view.layoutManager = linearLayoutManager

        textView.setOnClickListener{
            Log.d("currentuser", SessionRepository.currentUser?.uid)

            chatListingRepository.getList()

        }

    }




}