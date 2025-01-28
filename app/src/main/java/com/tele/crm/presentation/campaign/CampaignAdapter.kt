package com.tele.crm.presentation.campaign

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tele.crm.data.network.model.LeadsEntry
import com.tele.crm.data.network.model.campaign.Campaign
import com.tele.crm.databinding.ItemCampaignBinding
import com.tele.crm.databinding.RowCampaignBinding
import com.tele.crm.utils.extension.setDebouncedOnClickListener

class CampaignAdapter(private val listener: (Campaign) -> Unit) : RecyclerView.Adapter<CampaignAdapter.LeadViewHolder>() {

    private val callList = mutableListOf<Campaign>()

    fun submitList(list: List<Campaign>) {
        callList.clear()
        callList.addAll(list)
        notifyDataSetChanged()
    }

    class LeadViewHolder(private val binding: ItemCampaignBinding,
                         private val listener: (Campaign) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(campaign: Campaign) {
            binding.tvName.text = campaign.name

            itemView.setDebouncedOnClickListener {
                listener.invoke(campaign)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadViewHolder {
        val binding = ItemCampaignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeadViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: LeadViewHolder, position: Int) {
        holder.bind(callList[position])
    }

    override fun getItemCount(): Int = callList.size
}
