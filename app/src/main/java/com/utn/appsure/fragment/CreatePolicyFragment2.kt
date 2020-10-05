package com.utn.appsure.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utn.appsure.databinding.FragmentCreatePolicy2Binding
import com.utn.appsure.viewmodel.CreatePolicyViewModel2
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePolicyFragment2 : Fragment(){

    private val viewModel by viewModel<CreatePolicyViewModel2>()
    private lateinit var binding: FragmentCreatePolicy2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCreatePolicy2Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }
}