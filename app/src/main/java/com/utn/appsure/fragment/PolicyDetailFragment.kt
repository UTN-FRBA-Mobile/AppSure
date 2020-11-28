package com.utn.appsure.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.utn.appsure.R
import com.utn.appsure.databinding.FragmentPolicyDetailBinding
import com.utn.appsure.viewmodel.MainListViewModel
import com.utn.appsure.viewmodel.PolicyDetailViewModel
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_policy_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PolicyDetailFragment : Fragment() {
    private val viewModel by viewModel<PolicyDetailViewModel>()
    lateinit var binding: FragmentPolicyDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPolicyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


        ""
    }

}
