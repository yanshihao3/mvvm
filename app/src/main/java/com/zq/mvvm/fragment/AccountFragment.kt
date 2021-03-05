package com.zq.mvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zq.mvvm.R
import com.zq.mvvm.databinding.FragmentAccountBinding

/**
 * Created by Vishal Patolia on 18-Feb-18.
 */
class AccountFragment : Fragment() {
    lateinit var mBinding: FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        mBinding.login.setOnClickListener { }
        return mBinding.root
    }
}