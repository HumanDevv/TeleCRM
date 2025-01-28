package com.tele.crm.presentation.campaign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tele.crm.R
import com.tele.crm.databinding.FragmentCampaignBinding
import com.tele.crm.presentation.lead.LeadViewModel
import com.tele.crm.presentation.lead.LeadsAdapter
import com.tele.crm.utils.extension.setDebouncedOnClickListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CampaignFragment : Fragment() {
    private lateinit var binding: FragmentCampaignBinding
    private val viewModel: CampaignViewModel by viewModels()
    private lateinit var campaignAdapter : CampaignAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCampaignBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        campaignAdapter = CampaignAdapter {
            findNavController().navigate(R.id.action_campaignFragment_to_campaignDetailsFragment)
        }
        binding.rvCampaign.adapter = campaignAdapter
        binding.rvCampaign.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.leadEntries.collectLatest { calls ->
                campaignAdapter.submitList(calls)
            }
        }

        viewModel.loadMockData()

        handleClicks()
    }

    private fun handleClicks(){
        binding.apply {
            ivBack.setDebouncedOnClickListener {
                findNavController().popBackStack()
            }
            add.setDebouncedOnClickListener {
                findNavController().navigate(R.id.action_campaignFragment_to_addCampaign)
            }
        }
    }
}