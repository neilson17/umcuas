package com.ubaya.s160419037_umc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.util.loadImage
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textUsernameProfile.text = GlobalData.activeUser.username
        editNameProfile.setText(GlobalData.activeUser.name)
        editPasswordProfile.setText(GlobalData.activeUser.password)
        editEmailProfile.setText(GlobalData.activeUser.email)
        editPhoneProfile.setText(GlobalData.activeUser.phone)
        editAddressProfile.setText(GlobalData.activeUser.address)
        imageProfile.loadImage(GlobalData.activeUser.photo_url, progressLoadProfile)

        buttonSaveProfile.setOnClickListener {
            Toast.makeText(context, "Profile saved successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}