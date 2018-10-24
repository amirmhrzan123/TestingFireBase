package com.example.amir.testingfirebase.chatModules.contacts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.amir.testingfirebase.databinding.ItemCheckBinding
import com.example.amir.testingfirebase.databinding.ItemContactsBinding

/**
 * Created by Amir on 10/21/2018.
 */

class ContactAdapter(private val contactViewModel: ContactsViewModel,
                     private val listContacts: MutableList<ContactsModel> = arrayListOf()):
        RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var filterContactList : MutableList<ContactsModel> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
       return  ViewHolder(ItemCheckBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int = filterContactList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(position)
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

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                filterContactList = filterResults.values as MutableList<ContactsModel>
                notifyDataSetChanged()
            }

        }    }


    inner class ViewHolder(private val itemRingchat:ItemCheckBinding):
            RecyclerView.ViewHolder(itemRingchat.root){
        fun onBind(position: Int){
            with(itemRingchat){
                data = filterContactList[position]

                executePendingBindings()
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

}