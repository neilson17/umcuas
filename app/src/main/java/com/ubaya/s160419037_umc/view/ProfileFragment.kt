package com.ubaya.s160419037_umc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.FragmentProfileBinding
import com.ubaya.s160419037_umc.model.User
import com.ubaya.s160419037_umc.util.loadImage
import com.ubaya.s160419037_umc.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ButtonUpdateProfile {
    private lateinit var viewModel: LoginViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        dataBinding.user = GlobalData.activeUser
        dataBinding.buttonUpdateProfile = this
    }

    override fun onButtonUpdateProfile(v: View, obj: User) {
        viewModel.update(obj.username, obj.name, obj.password, obj.email, obj.phone, obj.address)
        GlobalData.activeUser = obj
        Toast.makeText(v.context, "Profile updated!", Toast.LENGTH_SHORT).show()
    }
}