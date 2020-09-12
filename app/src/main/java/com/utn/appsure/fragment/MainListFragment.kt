package com.utn.appsure.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utn.appsure.databinding.FragmentMainListBinding
import com.utn.appsure.viewmodel.MainListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainListFragment : Fragment() {

    private val viewModel by viewModel<MainListViewModel>()
    private lateinit var binding: FragmentMainListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }
}