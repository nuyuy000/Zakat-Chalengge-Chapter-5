package com.example.myapplicationn

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class SplashFragment : Fragment() {
    companion object{
        const val SHARED_FILE = "sharedfile"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreference = context?.getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE)

        val userShared = sharedPreference?.getString("email","")

        Handler(Looper.getMainLooper()).postDelayed({
            if (userShared == ""){
                val action = SplashFragmentDirections.actionSplashFragmentToFragmentLogin()
                findNavController().navigate(action)
            }else{
                val action = SplashFragmentDirections.actionSplashFragmentToFragmentLogin()
                findNavController().navigate(action)
            }
        },2000)


            }
    }
