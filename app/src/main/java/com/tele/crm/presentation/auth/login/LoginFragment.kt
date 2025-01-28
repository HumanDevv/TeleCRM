package com.tele.crm.presentation.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tele.crm.R
import com.tele.crm.data.network.AppState
import com.tele.crm.data.network.model.auth.login.LoginResponse
import com.tele.crm.databinding.FragmentLoginBinding
import com.tele.crm.presentation.lead.LeadViewModel
import com.tele.crm.utils.extension.hideProgress
import com.tele.crm.utils.extension.isValidEmail
import com.tele.crm.utils.extension.isValidMobile
import com.tele.crm.utils.extension.setDebouncedOnClickListener
import com.tele.crm.utils.extension.showProgress
import com.tele.crm.utils.extension.showToast
import com.tele.crm.utils.extension.value
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentLoginBinding.inflate(layoutInflater, container, false).apply {
        binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  setUpViewModelObserver()
        handleClickEvents()
        setUpViewModelObserver()
    }

    private fun handleClickEvents() {
        binding.apply {

            loginBtn.setDebouncedOnClickListener {
               // validateFields()
                findNavController().navigate(R.id.action_loginFragment_to_leadFragment)
            }

          /*  createAccountText.setDebouncedOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }

            forgetPassword.setDebouncedOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_ForgotFragment)
            }*/


        }
    }

    private fun validateFields() {

        when {
            binding.etEmail.value.isEmpty() ->
                showToast(getString(R.string.mobile_number_cannot_be_empty))

            !binding.etEmail.value.isValidEmail() ->
                showToast(getString(R.string.please_enter_a_valid_mobile_number))

            else -> {
                viewModel.login(binding.etEmail.value,binding.etPassword.value)
            }

        }
    }
    private fun setUpViewModelObserver() {
        lifecycleScope.launch {
            viewModel.loginResult.observe(requireActivity()) { response ->
                when (response) {
                    is AppState.Loading -> {
                        showProgress() // Show progress while loading
                    }

                    is AppState.LoginSuccess -> {
                        hideProgress() // Hide progress when response is successful

                        val loginResponse = response.login
                        if (loginResponse != null) {
                            if (loginResponse.message == "Login Succesfully") {
                                findNavController().navigate(R.id.action_loginFragment_to_leadFragment)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    loginResponse.message ?: "An error occurred",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Invalid response format",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    is AppState.NoInternetConnection -> {
                        hideProgress() // Hide progress if no internet
                        Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }

                    is AppState.UnknownError -> {
                        hideProgress() // Hide progress on unknown error
                        Toast.makeText(requireContext(), "An unknown error occurred", Toast.LENGTH_SHORT).show()
                    }

                    is AppState.ServerError -> {
                        hideProgress() // Hide progress for server error
                        Toast.makeText(requireContext(), response.message ?: "Server error", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        hideProgress() // Handle any unexpected cases
                        Toast.makeText(requireContext(), "Unexpected error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}