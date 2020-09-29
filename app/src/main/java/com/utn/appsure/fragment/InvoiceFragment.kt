package com.utn.appsure.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utn.appsure.databinding.FragmentInvoiceBinding
import com.utn.appsure.viewmodel.InvoiceViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoiceFragment : Fragment() {
    private val viewModel by viewModel<InvoiceViewModel>()
    private lateinit var binding: FragmentInvoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentInvoiceBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }
}