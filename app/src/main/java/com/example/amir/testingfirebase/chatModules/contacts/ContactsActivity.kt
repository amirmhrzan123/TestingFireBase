package com.example.amir.testingfirebase.chatModules.contacts

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.telephony.SmsManager
import android.widget.Toast
import com.example.amir.testingfirebase.R
import com.example.amir.testingfirebase.chatModules.base.BaseActivity
import com.example.amir.testingfirebase.databinding.ActivityContactBinding
import com.example.amir.testingfirebase.databinding.ActivityContactsBinding
import kotlinx.android.synthetic.main.toolbar_contacts.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Amir on 10/20/2018.
 */
class ContactsActivity:BaseActivity<ActivityContactBinding>(){

    val contactsViewModel: ContactsViewModel by viewModel()


    companion object {

        const val PERMISSION_REQUEST_CODE = 1

        fun newInstance(activity: Context){
            val intent = Intent(activity,ContactsActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getLayout(): Int = R.layout.activity_contact

    override fun initBinder() {

        if(!checkPermission()){
            requestPermission()
        }
        dataBinding.viewModel = contactsViewModel.apply {
            getContactsList()
            listContactsEvent.observe(this@ContactsActivity, Observer {
                Toast.makeText(this@ContactsActivity,"Open Chat Activity",Toast.LENGTH_SHORT)
            })

            onInvitationEvent.observe(this@ContactsActivity, Observer {

                 val smsManager = SmsManager.getDefault()
                                    smsManager.sendTextMessage(it?.number, null,
                                            "Join me on RingChat", null, null)


            })

            onAddContactEvent.observe(this@ContactsActivity, Observer {
                val intent = Intent(Intent.ACTION_INSERT)
                intent.type = ContactsContract.Contacts.CONTENT_TYPE
                startActivityForResult(intent, 1)

            })
        }

        dataBinding.rvContacts.apply {
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            this.layoutManager = layoutManager
            var contactAdapter :ContactsAdapter?=null

            contactsViewModel.let {
                contactAdapter = ContactsAdapter(it)
            }
            adapter = contactAdapter
            tv_group.setOnClickListener {
               val values = contactsViewModel.onGroupClicked()
                if(values){
                    tv_group.text = "Done"
                }else{
                    tv_group.text = "Group"
                }
                contactAdapter?.refreshViews(values)
            }

        }

        iv_back.setOnClickListener {
            super.onBackPressed()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun checkPermission(): Boolean {
        val smsPermissionResult = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.SEND_SMS)

        return  smsPermissionResult == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), PERMISSION_REQUEST_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.size > 0) {

                    val messagePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED


                    if (messagePermission) {

                        Toast.makeText(this,
                                "Permission accepted", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this,
                                "Permission denied", Toast.LENGTH_LONG).show()



                    }
                }
            }
        }
    }
}