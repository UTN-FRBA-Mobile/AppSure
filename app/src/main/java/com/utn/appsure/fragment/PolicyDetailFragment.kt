package com.utn.appsure.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.utn.appsure.R
import com.utn.appsure.adapter.PolicyAdapter
import com.utn.appsure.databinding.FragmentPolicyDetailBinding
import com.utn.appsure.viewmodel.PolicyDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PolicyDetailFragment : Fragment() {
    private val viewModel by viewModel<PolicyDetailViewModel>()
    lateinit var binding: FragmentPolicyDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_policy_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val viewAdapter = PolicyAdapter(this)
//        viewModel.policies.observe(viewLifecycleOwner, Observer {
//           viewAdapter.myDataset = it
//        })
//        viewModel.getPolicies()
    }

}