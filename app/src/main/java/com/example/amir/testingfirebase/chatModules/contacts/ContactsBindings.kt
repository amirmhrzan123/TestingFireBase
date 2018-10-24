package com.example.amir.testingfirebase.chatModules.contacts

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup

/**
 * Created by Amir on 10/20/2018.
 */
object ContactsBindings {

    @BindingAdapter("listContacts")
    @JvmStatic
    fun setContactsList(recyclerView: RecyclerView, chatList: List<ContactsModel>?) {
        with(recyclerView.adapter as ContactsAdapter) {
            setContactList(chatList)
        }
    }


    @BindingAdapter("filterText")
    @JvmStatic
    fun setSearchFilters(recyclerView: RecyclerView, filterText: String?) {
        with(recyclerView){
            filterText.let {
                (recyclerView.adapter as ContactsAdapter).filter.filter(filterText)
            }
        }
    }

    @BindingAdapter("visibility")
    @JvmStatic
    fun showHideRadioButton(checkBox: CheckBox,state:Boolean){

        if(state){
            checkBox.visibility = View.VISIBLE
        }else{
            checkBox.visibility = View.GONE
        }
    }



}