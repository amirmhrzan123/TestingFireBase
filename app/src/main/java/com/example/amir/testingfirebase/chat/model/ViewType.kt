package com.example.amir.testingfirebase.chat.model

import com.example.amir.testingfirebase.R

/**
 * Created by Amir on 10/13/2018.
 */
enum class ViewType(val id: Int) {

    OTHERS(0) {
        override fun getLayout(): Int {
            return R.layout.item_message
        }
    },
    ME(1) {
        override fun getLayout(): Int {
            return R.layout.item_message_me
        }
    };

    abstract fun getLayout(): Int

}