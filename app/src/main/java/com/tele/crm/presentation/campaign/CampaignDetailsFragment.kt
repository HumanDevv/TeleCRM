package com.tele.crm.presentation.campaign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tele.crm.R
import com.tele.crm.data.network.model.campaign.ParentItem
import com.tele.crm.databinding.FragmentCampaignDetailsBinding
import com.tele.crm.databinding.FragmentLeadDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CampaignDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CampaignDetailsFragment : Fragment() {
    lateinit var binding: FragmentCampaignDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentCampaignDetailsBinding.inflate(layoutInflater, container, false).apply {
        binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // Example Data
        val items = listOf(
            ParentItem(
                title = "Campaign Assignee Report",
                chartValues = listOf(60f, 40f),
                labels = listOf("Manjeet Singh", "Others")
            ),
            ParentItem(
                title = "Campaign Calling Report",
                chartValues = listOf(50f, 30f, 20f),
                labels = listOf("Connected", "Pending", "Not Interested")
            ),
            ParentItem(
                title = "Leads Status Report",
                chartValues = listOf(30f, 50f, 20f),
                labels = listOf("Fresh", "Won", "Lost")
            )
        )

        binding.recyclerView.adapter = ExpandableAdapter(items)
    }
}