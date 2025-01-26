package com.tele.crm.presentation.lead

import androidx.lifecycle.ViewModel
import com.tele.crm.data.network.model.CallEntry
import com.tele.crm.data.network.model.LeadsEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LeadViewModel : ViewModel() {
    private val _leadEntries = MutableStateFlow<List<LeadsEntry>>(emptyList())
    val leadEntries: StateFlow<List<LeadsEntry>> get() = _leadEntries.asStateFlow()

    // Mock data for testing
    fun loadMockData() {
        _leadEntries.update {
            listOf(
                LeadsEntry("Muskan Sharma", "917014845849", "LOST", "12:16 PM Sat, 11 Jan 25", "1d Ago", false),
                LeadsEntry("Muskan Sharma", "917014845849", "Connected", "1:05 PM Sat, 11 Jan 25", "1d Ago", true),
                LeadsEntry("Muskan Sharma", "917014845849", "Connected", "2:16 PM Sat, 11 Jan 25", "1d Ago", true),
                LeadsEntry("Muskan Sharma", "917014845849", "Connected", "5:26 PM Sat, 11 Jan 25", "1d Ago", true),
                LeadsEntry("Muskan Sharma", "917014845849", "Connected", "7:48 PM Sat, 11 Jan 25", "1d Ago", true)
                // Add more entries as needed
            )
        }
    }
}