package com.utn.appsure.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utn.appsure.R
import com.utn.appsure.activity.MainActivity
import com.utn.appsure.activity.PolicyActivity
import com.utn.appsure.adapter.PolicyAdapter
import com.utn.appsure.model.Policy
import com.utn.appsure.utils.TopSpacingItemDecoration
import com.utn.appsure.viewmodel.MainListViewModel
import com.utn.appsure.viewmodel.PolicyDetailViewModel
import kotlinx.android.synthetic.main.fragment_main_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainListFragment() : Fragment(), PolicyAdapter.OnPolicyItemClickListener {

    private val viewModel by viewModel<MainListViewModel>()
    private lateinit var recyclerView: RecyclerView
    private val policyDetailViewModel by viewModel<PolicyDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = PolicyAdapter(this)

        recyclerView = my_recycler_view.apply {
            layoutManager = viewManager
            val topSpacingItemDecoration = TopSpacingItemDecoration(20)
            addItemDecoration(topSpacingItemDecoration)
            adapter = viewAdapter
        }

        create_policy_fab.setOnClickListener {

//            activity?.let {
//                it as MainActivity
//                if (!it.checkPermissions())
//                    it.startLocationPermissionRequest()
//                else{
                    startActivity(
                        Intent(
                            activity,
                            PolicyActivity::class.java
                        )
                    )
//                }
//            }
        }
        viewModel.policies.observe(viewLifecycleOwner, Observer {
            viewAdapter.myDataset = it
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPolicies()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.menu_map) {
            findNavController(this).navigate(R.id.action_go_to_map)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(item: Policy) {
        val bundle = bundleOf("license" to item.license)
        findNavController(this).navigate(R.id.action_go_to_policy_detail, bundle)
    }
}