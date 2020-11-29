package com.utn.appsure.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utn.appsure.databinding.FragmentPolicyDetailBinding
import com.utn.appsure.viewmodel.PolicyDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PolicyDetailFragment : Fragment() {
    private val viewModel by viewModel<PolicyDetailViewModel>()
    lateinit var binding: FragmentPolicyDetailBinding
    var license: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        license = arguments?.getString("license")
        Log.i("uesa", license)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPolicyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
