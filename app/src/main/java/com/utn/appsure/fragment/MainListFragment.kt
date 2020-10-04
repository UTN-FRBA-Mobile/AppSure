package com.utn.appsure.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utn.appsure.activity.PolicyActivity
import com.utn.appsure.databinding.FragmentMainListBinding
import com.utn.appsure.viewmodel.MainListViewModel
import kotlinx.android.synthetic.main.fragment_main_list.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        create_policy_fab.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    PolicyActivity::class.java
                )
            )
        }
    }
}