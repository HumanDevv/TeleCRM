package com.tele.crm.presentation.addleads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.tele.crm.R
import com.tele.crm.databinding.FragmentAddLeadsBinding
import com.tele.crm.databinding.FragmentLoginBinding
import com.tele.crm.databinding.TabItemBinding

class AddLeadsFragment : Fragment() {

    private lateinit var binding: FragmentAddLeadsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentAddLeadsBinding.inflate(layoutInflater, container, false).apply {
        binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleClicks()

    }

    private fun handleClicks(){
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}
