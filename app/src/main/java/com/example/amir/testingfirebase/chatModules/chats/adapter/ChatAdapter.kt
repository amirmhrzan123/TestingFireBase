package com.example.amir.testingfirebase.chatModules.chats.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.amir.testingfirebase.chatModules.chats.ChatViewModel
import com.example.amir.testingfirebase.chatModules.chats.model.ChatRooms
import com.example.amir.testingfirebase.databinding.ItemChatGroupBinding

/**
 * Created by Amir on 10/18/2018.
 */
class ChatAdapter constructor(private val chatViewModel: ChatViewModel,
                              private val chatRoomList:MutableList<ChatRooms> = arrayListOf()):
        RecyclerView.Adapter<RecyclerView.ViewHolder>(),Filterable {


    private var filterChatroomList: MutableList<ChatRooms> = arrayListOf()



    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemChatGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = filterChatroomList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(position)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                filterChatroomList = if (charString.isEmpty()) {
                    chatRoomList
                } else {
                    val filteredList: ArrayList<ChatRooms> = arrayListOf()
                    for (row in chatRoomList) {
                        // name match condition. this might differ depending on your requirement
                        if (row.searchTerm?.toLowerCase()!!.contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }

                    filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filterChatroomList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                filterChatroomList = filterResults.values as MutableList<ChatRooms>
                notifyDataSetChanged()
            }
        }    }


    inner class ViewHolder(private val itemChatGroupBinding: ItemChatGroupBinding)
        : RecyclerView.ViewHolder(itemChatGroupBinding.root) {
        fun onBind(position: Int) {
            with(itemChatGroupBinding) {
                data = filterChatroomList[position]
               /* if(data!!.users.maps.size==1){
                    chatViewModel.showHideSecondImage(false)
                    chatViewModel.showHideSecondImage(false)
                }else if(data!!.users.maps.size==2){
                    chatViewModel.showHideSecondImage(true)
                    chatViewModel.showHideSecondImage(false)
                }else if(data!!.users.maps.size==3){
                    chatViewModel.showHideSecondImage(true)
                    chatViewModel.showHideThirdImage(true)
                }*/
                root.setOnClickListener {
                   // searchCountryViewModel.selectedCountry.value = data
                }

                executePendingBindings()
            }
        }
    }


    fun setChatRoomList(list: List<ChatRooms>?) {
        list?.let {
            chatRoomList.clear()
            chatRoomList.addAll(it)
            this.filterChatroomList.clear()
            this.filterChatroomList.addAll(it)
            notifyDataSetChanged()
        }
    }
}