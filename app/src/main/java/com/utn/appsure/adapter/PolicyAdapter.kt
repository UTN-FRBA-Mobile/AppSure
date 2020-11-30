package com.utn.appsure.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utn.appsure.R
import com.utn.appsure.model.Policy
import kotlinx.android.synthetic.main.view_listitem_policy.view.*

class PolicyAdapter(var clickListener: OnPolicyItemClickListener) :
    RecyclerView.Adapter<PolicyAdapter.MyViewHolder>() {


    var myDataset: List<Policy> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val VIEWTYPE_POLICY: Int = 1

    override fun getItemCount() = myDataset.size

    override fun getItemViewType(position: Int): Int {
        return VIEWTYPE_POLICY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = if (viewType == VIEWTYPE_POLICY)
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_listitem_policy, parent, false)
        else
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_listitem_policy, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindPolicy(myDataset[position])
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewPolicyLicense: TextView = itemView.policy_license
        private val viewPolicyPoster: ImageView = itemView.policy_poster

        fun bindPolicy(policy: Policy) {
            viewPolicyLicense.text = policy.license
            if (policy.poster != 0) {
                policy.poster?.let { viewPolicyPoster.setImageResource(it) }
            }
            itemView.setOnClickListener { clickListener.onItemClick(policy) }
        }
    }

    interface OnPolicyItemClickListener {
        fun onItemClick(item: Policy)
    }
}