package com.nikitacherenkov.ecommerceapp.fragments.loginRegister

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.nikitacherenkov.ecommerceapp.R
import com.nikitacherenkov.ecommerceapp.activities.ShoppingActivity
import com.nikitacherenkov.ecommerceapp.databinding.FragmentLoginBinding
import com.nikitacherenkov.ecommerceapp.dialog.setupBottomSheetDialog
import com.nikitacherenkov.ecommerceapp.utils.Resource
import com.nikitacherenkov.ecommerceapp.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNoAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.apply {
            buttonLogin.setOnClickListener{
                val email = edEmailLogin.text.toString().trim()
                val password = edEmailPassword.text.toString()
                viewModel.login(email, password)
            }
            tvForgotPassword.setOnClickListener {
                setupBottomSheetDialog { email ->
                    viewModel.resetPassword(email)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.resetPassword.collect{
                when(it){
                    is Resource.Success ->{
                        Snackbar.make(requireView(), "Reset link was send to your email", Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Error->{
                        Snackbar.make(requireView(), "Error: ${it.message}", Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Loading->{

                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.login.collect{
                when(it){
                    is Resource.Success->{
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    is Resource.Error->{
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    else->Unit
                }
            }
        }

    }

}