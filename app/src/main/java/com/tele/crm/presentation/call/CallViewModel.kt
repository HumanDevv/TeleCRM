package com.tele.crm.presentation.call

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tele.crm.data.network.model.CallEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CallViewModel : ViewModel() {
    private val _callEntries = MutableStateFlow<List<CallEntry>>(emptyList())
    val callEntries: StateFlow<List<CallEntry>> get() = _callEntries.asStateFlow()

    // Mock data for testing
    fun loadMockData() {
        _callEntries.update {
            listOf(
                CallEntry("Muskan Sharma", "917014845849", "LOST", "1m 8s", "1d Ago", false),
                CallEntry("Muskan Sharma", "917014845849", "Connected", "1m 8s", "1d Ago", true)
                // Add more entries as needed
            )
        }
    }
}