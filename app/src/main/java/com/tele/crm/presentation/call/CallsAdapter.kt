package com.tele.crm.presentation.call

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tele.crm.data.network.model.CallEntry
import com.tele.crm.databinding.RowCallsBinding

class CallsAdapter : RecyclerView.Adapter<CallsAdapter.CallViewHolder>() {

    private val callList = mutableListOf<CallEntry>()

    fun submitList(list: List<CallEntry>) {
        callList.clear()
        callList.addAll(list)
        notifyDataSetChanged()
    }

    class CallViewHolder(private val binding: RowCallsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(call: CallEntry) {
            binding.tvName.text = call.name
            binding.tvPhoneNumber.text = call.phoneNumber
            binding.tvStatus.text = call.status
            binding.tvDuration.text = call.duration
            binding.tvTimeAgo.text = call.timeAgo
            binding.ivStar.isSelected = call.isStarred

            // Optional: Handle click for starring
            binding.ivStar.setOnClickListener {
                binding.ivStar.isSelected = !binding.ivStar.isSelected
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        val binding = RowCallsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        holder.bind(callList[position])
    }

    override fun getItemCount(): Int = callList.size
}
