package com.nikitacherenkov.ecommerceapp.fragments.loginRegister

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nikitacherenkov.ecommerceapp.R
import com.nikitacherenkov.ecommerceapp.data.User
import com.nikitacherenkov.ecommerceapp.databinding.FragmentRegisterBinding
import com.nikitacherenkov.ecommerceapp.utils.Resource
import com.nikitacherenkov.ecommerceapp.viewmodels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

val TAG = "RegisterFragment"
@AndroidEntryPoint
class RegisterFragment: Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonRegister.setOnClickListener {
                val user = User(
                    edFirstNameRegister.text.toString().trim(),
                    edLastNameRegister.text.toString().trim(),
                    edEmailLogin.text.toString().trim()
                )
                val password = edEmailPassword.text.toString()
                viewModel.createAccountWithEmail(user,password)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect{
                when(it){
                    is Resource.Success->{
                        Log.d("test", it.message.toString())
                    }
                    is Resource.Error->{
                        Log.e(TAG, it.message.toString())
                    }
                    else -> Unit
                }
            }
        }

    }

}