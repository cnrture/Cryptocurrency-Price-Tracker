package com.canerture.cryptocurrencypricetracker.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.canerture.cryptocurrencypricetracker.R
import com.canerture.cryptocurrencypricetracker.common.*
import com.canerture.cryptocurrencypricetracker.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        collectData()
    }

    private fun initUI() {

        with(binding) {

            tvHaveAnAlreadyAccount.setOnClickListener {
                findNavController().navigate(R.id.signUpToSignIn)
            }

            btnSignUp.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                if (tilEmail.isValidEmail(email, getString(R.string.invalid_mail)) &&
                    tilPassword.isNullorEmpty(password, getString(R.string.invalid_password))
                ) {
                    signUpViewModel.signUp(email, password)
                }
            }
        }
    }

    private fun collectData() {

        with(binding) {

            with(signUpViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    isCurrentUserExist.collect {
                        if (it) findNavController().navigate(R.id.signUpToMain)
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    signUpFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                requireView().showSnackbar(getString(R.string.sign_up_successful))
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}