package com.example.amir.testingfirebase.chatModules.base

import android.app.ProgressDialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem

/**
 * Created by Amir on 10/18/2018.
 */
abstract class BaseActivity<V: ViewDataBinding> : AppCompatActivity() {
    protected lateinit var dataBinding: V
    private lateinit var progressDialog: ProgressDialog
    private var toolbar: Toolbar? = null

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun initBinder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        progressDialog = ProgressDialog(this)
       initBinder()
    }


    private fun performDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, getLayout())
    }

    fun hideProgressDialog() {
        progressDialog.cancel()
    }


}