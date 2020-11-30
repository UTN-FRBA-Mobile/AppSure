package com.utn.appsure.fragment

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.utn.appsure.R
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
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPolicyDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.menu_map) {
            val bundle = bundleOf("license" to viewModel.license)
            NavHostFragment.findNavController(this).navigate(R.id.action_go_to_map, bundle)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        license?.let { viewModel.getPolicy(it) }
    }

}
