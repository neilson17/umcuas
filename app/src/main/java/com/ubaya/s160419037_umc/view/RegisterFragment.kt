package com.ubaya.s160419037_umc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.FragmentRegisterBinding
import com.ubaya.s160419037_umc.model.User
import com.ubaya.s160419037_umc.viewmodel.LoginViewModel

class RegisterFragment : Fragment(), ButtonRegister, ButtonCancelRegister {
    private lateinit var dataBinding: FragmentRegisterBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        dataBinding.buttonRegisterListener = this
        dataBinding.buttonCancelRegisterListener = this
        dataBinding.user = User("","", "", "", "", "", "")
    }

    override fun onButtonRegister(v: View) {
        dataBinding.user?.let {
            if (it.username != "" &&  it.name != "" && it.password != "" && it.email != "" && it.phone != "" && it.address != "" && it.photo_url != "") {
                val list = listOf(it)
                viewModel.registerUser(list)
                val action = RegisterFragmentDirections.actionRegisterFragmentToItemLogout()
                Navigation.findNavController(v).navigate(action)
            }
            else {
                Toast.makeText(context, "Please fill all the inputs", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onButtonCancelRegister(v: View) {
        val action = RegisterFragmentDirections.actionRegisterFragmentToItemLogout()
        Navigation.findNavController(v).navigate(action)
    }
}