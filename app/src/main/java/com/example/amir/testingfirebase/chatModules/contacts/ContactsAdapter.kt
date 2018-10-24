package com.example.amir.testingfirebase.chatModules.contacts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.amir.testingfirebase.databinding.ItemContactsBinding
import com.example.amir.testingfirebase.databinding.ItemInviteContactBinding
import java.text.FieldPosition

/**
 * Created by Amir on 10/20/2018.
 */
class ContactsAdapter(private val contactViewModel: ContactsViewModel,
                      private val listContacts: MutableList<ContactsModel> = arrayListOf()):
        RecyclerView.Adapter<RecyclerView.ViewHolder>(),Filterable {


    private var filterContactList : MutableList<ContactsModel> = arrayListOf()




    companion object {
        const val RINGCHAT_MEMEBERS = 1
        const val NON_RINGCHAT_MEMEBERS = 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType== RINGCHAT_MEMEBERS) {
            RingChatViewHolde(ItemContactsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }else{
            NonRingChatViewHolder(ItemInviteContactBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!filterContactList[position].user?.isEmpty()!!) {
            RINGCHAT_MEMEBERS
        } else {
            NON_RINGCHAT_MEMEBERS
        }
    }

    override fun getItemCount(): Int = filterContactList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is RingChatViewHolde->holder.onBind(position)
            is NonRingChatViewHolder->holder.onBind(position)
        }
    }

    inner class RingChatViewHolde(private val itemRingchat:ItemContactsBinding):
            RecyclerView.ViewHolder(itemRingchat.root){
        fun onBind(position: Int){
            with(itemRingchat){
                data = filterContactList[position]
                executePendingBindings()
            }
        }
    }

    inner class NonRingChatViewHolder(private val nonItemRingChat: ItemInviteContactBinding):
            RecyclerView.ViewHolder(nonItemRingChat.root){
        fun onBind(position:Int){
            with(nonItemRingChat){
                data = filterContactList[position]

                root.setOnClickListener {
                    contactViewModel.inviteForRingChat(data!!)
                }
                executePendingBindings()
            }
        }
    }

    override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                filterContactList = if (charString.isEmpty()) {
                    listContacts
                } else {
                    val filteredList: ArrayList<ContactsModel> = arrayListOf()
                    for (row in listContacts) {
                        // name match condition. this might differ depending on your requirement
                        if (row.name?.toLowerCase()!!.contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }

                    filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filterContactList
                return filterResults

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterContactList = results?.values as MutableList<ContactsModel>
                notifyDataSetChanged()
            }

        }
    }

    fun setContactList(list: List<ContactsModel>?) {
        list?.let {
            listContacts.clear()
            listContacts.addAll(it)
            this.filterContactList.clear()
            this.filterContactList.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun refreshViews(value: Boolean){
        for(contacts in filterContactList){
            if(!contacts.user.isNullOrEmpty()){
                contacts.showHide = value
            }
        }
        notifyDataSetChanged()
    }
}