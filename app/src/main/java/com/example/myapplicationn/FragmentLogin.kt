package com.example.myapplicationn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplicationn.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FragmentLogin : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var DbZakat:ZakatDatabase?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            if (binding.etPassword.text.isEmpty() || binding.etUsername.text.isEmpty()) {
                Toast.makeText(requireContext(), "Kolom Masih Kosong", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    val regis = DbZakat?. akunDao()?. login(String(),String())
                    runBlocking(Dispatchers.Main) {
                        if (regis==null) {
                            Toast.makeText(requireContext(), "Berhasil Login", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(requireContext(),
                                "Login Gagal, periksa kembali username dan password anda",
                                Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_fragmentRegister_to_fragmentLogin)
                        }
                    }
                }
            }
        }




                binding.tvKlik.setOnClickListener {
                    val action = FragmentLoginDirections.actionFragmentLoginToFragmentRegister()
                    it.findNavController().navigate(action)
                }
            }
        }

