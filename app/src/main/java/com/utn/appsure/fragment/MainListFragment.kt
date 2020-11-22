package com.utn.appsure.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utn.appsure.R
import com.utn.appsure.activity.PolicyActivity
import com.utn.appsure.adapter.PolicyAdapter
import com.utn.appsure.model.PolicyApi
import com.utn.appsure.viewmodel.MainListViewModel
import kotlinx.android.synthetic.main.fragment_main_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainListFragment : Fragment() {

    private val viewModel by viewModel<MainListViewModel>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myDataset = PolicyApi().getPolicies()

        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = PolicyAdapter(myDataset)

        recyclerView = my_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        create_policy_fab.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    PolicyActivity::class.java
                )
            )
        }
        viewModel.getPruebaData()
    }
}