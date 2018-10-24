package com.example.amir.testingfirebase.chatModules.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Amir on 10/18/2018.
 */


abstract class BaseFragment<V : ViewDataBinding> : Fragment() {
    //protected lateinit var baseActivity: BaseActivity<*>
    protected lateinit var dataBinding: V

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun initBinder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
       /* if (context is BaseActivity<*>) {
            baseActivity = context
        }*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        performViewBinding(inflater, container)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinder()

    }

    private fun performViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
    }
}