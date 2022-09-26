package com.example.lab8

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var buttonLogin : Button
    private lateinit var inputLayoutEmail: EditText
    private lateinit var inputLayoutPassword: EditText
    private lateinit var correo : String
    private lateinit var constraseña : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLogin = view.findViewById(R.id.button_login)
        inputLayoutEmail = view.findViewById(R.id.textInputEmail)
        inputLayoutPassword = view.findViewById(R.id.textInputPassword)
        setListeners()
    }

    private fun setListeners() {
        buttonLogin.setOnClickListener{
            correo=inputLayoutEmail.text.toString()
            constraseña=inputLayoutPassword.text.toString()
            if (correo=="mor21146@uvg.edu.gt" && constraseña=="mor21146@uvg.edu.gt"){
                requireView().findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToCharacterFragment()
                )
            }else{
                Toast.makeText(activity, "Las credenciales son incorrectas", Toast.LENGTH_LONG).show()
            }
        }
    }

}