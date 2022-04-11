package com.example.myapplicationn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplicationn.databinding.FragmentRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FragmentRegister : Fragment() {
    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var DbZakat:ZakatDatabase?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DbZakat = ZakatDatabase.getInstance(requireContext())
        binding.btnRegister.setOnClickListener{
            if (binding.etUsername.text.toString().isEmpty() || binding.etPassword.text.isEmpty() || binding.etConfirmpassword.text.isEmpty()) {
                Toast.makeText(requireContext(), "Form Tidak Boleh Kosong ", Toast.LENGTH_SHORT).show()
            }
            else if (binding.etPassword.text.toString() != binding.etConfirmpassword.text.toString()){
                Toast.makeText(requireContext(),      "Password Harus Sama", Toast.LENGTH_SHORT).show()
            }
            else {
                val akun = Akun (null,binding.etUsername.text.toString(), binding.etPassword.text.toString())
                lifecycleScope.launch(Dispatchers.IO){
                    val regis = DbZakat?. akunDao()?. addUser(akun)
                    runBlocking(Dispatchers.Main) {
                        if (regis!=0.toLong()){
                            Toast.makeText(requireContext(), "Berhasil Registrasi", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_fragmentRegister_to_fragmentLogin)
                        }
                        else {
                            Toast.makeText(requireContext(), "Gagal Registrasi", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        }


    }
}