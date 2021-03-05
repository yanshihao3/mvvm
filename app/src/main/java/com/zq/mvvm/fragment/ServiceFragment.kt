package com.zq.mvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zq.mvvm.R
import com.zq.mvvm.databinding.FragmentOthersBinding

/**
 * Created by Vishal Patolia on 18-Feb-18.
 */
class ServiceFragment : Fragment() {
    private lateinit var mBinding: FragmentOthersBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_others, container, false)
        mBinding.homeTxtTitle.text = getString(R.string.menu_services)
        return mBinding.root
    }
}