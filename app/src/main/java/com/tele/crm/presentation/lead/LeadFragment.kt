package com.tele.crm.presentation.lead

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
import com.tele.crm.databinding.FragmentCallBinding
import com.tele.crm.databinding.FragmentLeadBinding
import com.tele.crm.presentation.call.CallViewModel
import com.tele.crm.presentation.call.CallsAdapter
import com.tele.crm.utils.extension.setDebouncedOnClickListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LeadFragment : Fragment() {

    private lateinit var binding: FragmentLeadBinding
    private val viewModel: LeadViewModel by viewModels()
    private lateinit var leadsAdapter: LeadsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addLeadBtn.setDebouncedOnClickListener {
            findNavController().navigate(R.id.action_leadFragment_to_addLeadsFragment)
        }


        leadsAdapter = LeadsAdapter { leadEntry ->
           findNavController().navigate(R.id.leadDetailsFragment)
        }
        binding.rvLeads.adapter = leadsAdapter
        binding.rvLeads.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.leadEntries.collectLatest { calls ->
                leadsAdapter.submitList(calls)
            }
        }

        viewModel.loadMockData()
    }
}
