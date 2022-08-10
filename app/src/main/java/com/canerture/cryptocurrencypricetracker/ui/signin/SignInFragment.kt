package com.canerture.cryptocurrencypricetracker.ui.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.canerture.cryptocurrencypricetracker.R
import com.canerture.cryptocurrencypricetracker.common.*
import com.canerture.cryptocurrencypricetracker.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)

    private val signInViewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        collectData()
    }

    private fun initUI() {

        with(binding) {

            imgBack.setOnClickListener {
                findNavController().navigate(R.id.signInToSignUp)
            }

            btnSignIn.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                if (tilEmail.isValidEmail(email, getString(R.string.invalid_mail)) &&
                    tilPassword.isNullorEmpty(password, getString(R.string.invalid_password))
                ) {
                    signInViewModel.signIn(email, password)
                }
            }
        }
    }

    private fun collectData() {

        with(binding) {

            with(signInViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    signInFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                requireView().showSnackbar(getString(R.string.sign_in_successful))
                                findNavController().navigate(R.id.signInToMain)
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