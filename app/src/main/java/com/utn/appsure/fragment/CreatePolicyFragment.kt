package com.utn.appsure.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utn.appsure.databinding.FragmentCreatePolicyBinding
import com.utn.appsure.viewmodel.CreatePolicyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePolicyFragment : Fragment(){

    private val viewModel by viewModel<CreatePolicyViewModel>()
    private lateinit var binding: FragmentCreatePolicyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCreatePolicyBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }
}