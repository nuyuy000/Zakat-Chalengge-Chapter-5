package com.example.myapplicationn

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationn.databinding.FragmentFormBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FormFragment (private val zakat: Zakat?) : DialogFragment() {
    var saveClick:(Zakat)->Unit = {}
    var editClick:(Zakat)->Unit = {}

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zakat?.let{
            editData(it)
        } ?: kotlin.run {
            addData()
        }

    }

    private fun editData(zakat: Zakat){
        binding.apply {

            etJenisZakat.setText(zakat.jenisZakat)
            etDeskripsi.setText(zakat.deskripsi)

            btnSave.setOnClickListener {
                val newZakat =
                    Zakat(
                        zakat.id,
                        binding.etJenisZakat.text.toString(),
                        binding.etDeskripsi.text.toString()
                    )
                editClick(newZakat)
                dismiss()
            }

        }
    }

    private fun addData(){

        binding.btnSave.setOnClickListener {
            val newZakat =
                Zakat(null,
                    binding.etJenisZakat.text.toString(),
                    binding.etDeskripsi.text.toString())
            saveClick(newZakat)
            dismiss()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}