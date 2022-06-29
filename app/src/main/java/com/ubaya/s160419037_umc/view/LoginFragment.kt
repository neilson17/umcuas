package com.ubaya.s160419037_umc.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.s160419037_umc.GlobalData
import com.ubaya.s160419037_umc.R
import com.ubaya.s160419037_umc.databinding.FragmentLoginBinding
import com.ubaya.s160419037_umc.model.User
import com.ubaya.s160419037_umc.util.loadImage
import com.ubaya.s160419037_umc.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), ButtonLogin {
    private lateinit var viewModel : LoginViewModel
    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.hide()
        (activity as MainActivity).bottomNav.visibility = View.GONE

        GlobalData.loggedIn = false
        var shared = (activity as MainActivity).getSharedPreferences((activity as MainActivity).packageName, Context.MODE_PRIVATE)
        var editor = shared.edit()
        editor.putString("USERNAME", null).apply()
        editor.putString("PASSWORD", null).apply()
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        dataBinding.users = User("","", "", "", "", "", "")
        dataBinding.buttonLoginListener = this

//        buttonLogin.setOnClickListener {
//            var username = editUsernameLogin.text.toString()
//            var password = editPasswordLogin.text.toString()
//
//            if (username != "" && password != "") {
//                viewModel.fetch(username, password)
//                observeViewModel(view)
//            } else Toast.makeText(context, "Please fill in your Username or Password first!", Toast.LENGTH_SHORT).show()
//        }

//        buttonSignup.setOnClickListener {
//            val builder = AlertDialog.Builder(context!!)
//            with (builder) {
//                setMessage("Contact administrator to have your account added! :)")
//                setTitle("Registration Failed")
//                setPositiveButton("OK", null)
//                create().show()
//            }
//        }
    }

    private fun observeViewModel(view: View) {
        viewModel.loginLiveData.observe(viewLifecycleOwner, Observer {
//            dataBinding.users = it
            if (it != null) {
                GlobalData.activeUser = it
                GlobalData.loggedIn = true

                var header = (activity as MainActivity).navView.getHeaderView(0)
                (header.textUsernameDrawer as TextView).text = it.username
                (header.imageDrawer as ImageView).loadImage(it.photo_url, (header.progressLoadDrawer as ProgressBar))

                var shared = (activity as MainActivity).getSharedPreferences((activity as MainActivity).packageName, Context.MODE_PRIVATE)
                var editor = shared.edit()
                editor.putString("USERNAME", it.username).apply()
                editor.putString("PASSWORD", it.password).apply()

                (activity as MainActivity).supportActionBar?.show()
                (activity as MainActivity).bottomNav.visibility = View.VISIBLE

                val action = LoginFragmentDirections.actionHomeFragment()
                Navigation.findNavController(view).navigate(action)
            }
            else {
                Toast.makeText(context, "Your Username or Password is wrong!", Toast.LENGTH_SHORT).show()
            }

        })
//        viewModel.loginLiveData.observe(viewLifecycleOwner){
//            if(it.status == "success"){
//                GlobalData.activeUser = it.user!!
//                GlobalData.loggedIn = true
//
//                var header = (activity as MainActivity).navView.getHeaderView(0)
//                (header.textUsernameDrawer as TextView).text = it.user!!.username
//                (header.imageDrawer as ImageView).loadImage(it.user!!.photo_url, (header.progressLoadDrawer as ProgressBar))
//
//                var shared = (activity as MainActivity).getSharedPreferences((activity as MainActivity).packageName, Context.MODE_PRIVATE)
//                var editor = shared.edit()
//                editor.putString("USERNAME", it.user!!.username).apply()
//                editor.putString("PASSWORD", it.user!!.password).apply()
//
//                (activity as MainActivity).supportActionBar?.show()
//                (activity as MainActivity).bottomNav.visibility = View.VISIBLE
//
//                val action = LoginFragmentDirections.actionHomeFragment()
//                Navigation.findNavController(v).navigate(action)
//            }else{
//                Toast.makeText(context, "Your Username or Password is wrong!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    override fun onButtonLogin(v: View) {
        dataBinding.users?.let {
//            var username = editUsernameLogin.text.toString()
//            var password = editPasswordLogin.text.toString()

            if (it.username != "" && it.password != "") {
                viewModel.fetch(it.username, it.password)
                observeViewModel(v)
            } else Toast.makeText(context, "Please fill in your Username or Password first!", Toast.LENGTH_SHORT).show()
        }
    }
}