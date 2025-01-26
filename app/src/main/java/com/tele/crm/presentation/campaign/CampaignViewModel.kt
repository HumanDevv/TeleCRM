package com.tele.crm.presentation.campaign

import androidx.lifecycle.ViewModel
import com.tele.crm.data.network.model.LeadsEntry
import com.tele.crm.data.network.model.campaign.Campaign
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CampaignViewModel : ViewModel() {
    private val _leadEntries = MutableStateFlow<List<Campaign>>(emptyList())
    val leadEntries: StateFlow<List<Campaign>> get() = _leadEntries.asStateFlow()

    // Mock data for testing
    fun loadMockData() {
        _leadEntries.update {
            listOf(
                Campaign("Muskan Sharma", "2", "2", "10"),
                Campaign("Muskan Sharma", "4", "5", "25"),
                Campaign("Muskan Sharma", "2", "3", "55"),
                Campaign("Muskan Sharma", "5", "8", "78"),
                // Add more entries as needed
            )
        }
    }
}