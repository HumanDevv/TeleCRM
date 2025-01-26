package com.tele.crm.presentation.call

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tele.crm.R
import com.tele.crm.databinding.FragmentCallBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CallFragment : Fragment() {

    private lateinit var binding: FragmentCallBinding
    private val viewModel: CallViewModel by viewModels()
    private val callsAdapter = CallsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCalls.adapter = callsAdapter
        binding.rvCalls.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.callEntries.collectLatest { calls ->
                callsAdapter.submitList(calls)
            }
        }

        viewModel.loadMockData()
    }
}
