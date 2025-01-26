package com.tele.crm.presentation.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tele.crm.R
import com.tele.crm.databinding.FragmentLoginBinding
import com.tele.crm.utils.extension.isValidMobile
import com.tele.crm.utils.extension.setDebouncedOnClickListener
import com.tele.crm.utils.extension.showToast
import com.tele.crm.utils.extension.value

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentLoginBinding.inflate(layoutInflater, container, false).apply {
        binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleClickEvents()
    }

    private fun handleClickEvents() {
        binding.apply {

            btnLogin.setDebouncedOnClickListener {
                validateFields()
               // findNavController().navigate(R.id.action_loginFragment_to_leadFragment)
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
            binding.edtEmail.value.isEmpty() ->
                showToast(getString(R.string.mobile_number_cannot_be_empty))

            !binding.edtEmail.value.isValidMobile() ->
                showToast(getString(R.string.please_enter_a_valid_mobile_number))

            else -> {
                //login()
            }

        }
    }

}