package com.tele.crm.presentation.lead

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tele.crm.data.network.model.LeadsEntry
import com.tele.crm.databinding.RowLeadsBinding
import com.tele.crm.utils.extension.setDebouncedOnClickListener

class LeadsAdapter(private val listener: (LeadsEntry) -> Unit) : RecyclerView.Adapter<LeadsAdapter.LeadViewHolder>() {

    private val leadList = mutableListOf<LeadsEntry>()

    fun submitList(list: List<LeadsEntry>) {
        leadList.clear()
        leadList.addAll(list)
        notifyDataSetChanged()
    }

    class LeadViewHolder(
        private val binding: RowLeadsBinding,
        private val listener: (LeadsEntry) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lead: LeadsEntry) {
            binding.tvName.text = lead.name
            binding.tvPhoneNumber.text = lead.phoneNumber
            binding.tvStatus.text = lead.status
            binding.tvTime.text = lead.duration

            binding.ivStar.isSelected = lead.isStarred

            // Handle click for starring
            binding.ivStar.setOnClickListener {
                binding.ivStar.isSelected = !binding.ivStar.isSelected
            }

            // Handle item click
            itemView.setDebouncedOnClickListener {
                listener(lead)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadViewHolder {
        val binding = RowLeadsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeadViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: LeadViewHolder, position: Int) {
        holder.bind(leadList[position])
    }

    override fun getItemCount(): Int = leadList.size
}
