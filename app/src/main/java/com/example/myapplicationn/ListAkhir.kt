package com.example.myapplicationn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationn.databinding.FragmentListAkhirBinding
import com.example.myapplicationn.databinding.FragmentRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListAkhir : Fragment() {
    private var _binding : FragmentListAkhirBinding? = null
    private val binding get() = _binding!!
    private var DbZakat:ZakatDatabase?=null
    lateinit var zakatRepository: ZakatRepository
    lateinit var adapter: ZakatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListAkhirBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zakatRepository = ZakatRepository(requireContext())
        DbZakat = ZakatDatabase.getInstance(requireContext())
        adapter = ZakatAdapter({
            lifecycleScope.launch(Dispatchers.IO) {
                val deleteZakat = zakatRepository.deleteZakat(it)
                activity?.runOnUiThread {
                    if (deleteZakat==0) {
                        Toast.makeText(context, "Delete Gagal", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, "Delete Berhasil", Toast.LENGTH_SHORT).show()
                        fetchdata()
                    }
                }
            }
        },{
            val dialog = FormFragment(it)
            dialog.editClick = {
                lifecycleScope.launch(Dispatchers.IO) {
                    val updateZakat = zakatRepository.updateZakat(it)
                    activity?.runOnUiThread {
                        if (updateZakat==0) {
                            Toast.makeText(context, "Update Gagal", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT).show()
                            fetchdata()
                        }
                    }
                }
            }
            dialog.show(parentFragmentManager,"update")
        })
        binding.rvZakat.adapter = adapter
        fetchdata()
        binding.btnadd.setOnClickListener{
            val dialog = FormFragment(null)
            dialog.saveClick = {
                lifecycleScope.launch(Dispatchers.IO) {
                    val insertZakat =zakatRepository.addZakat(it)
                    activity?.runOnUiThread {
                        if (insertZakat==(0).toLong()) {
                            Toast.makeText(context, "Insert Gagal", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(context, "Insert Berhasil", Toast.LENGTH_SHORT).show()
                            fetchdata()
                        }
                    }
                }
            }
            dialog.show(parentFragmentManager,"insert")
        }
    }

    fun fetchdata (){

        lifecycleScope.launch(Dispatchers.IO) {
          val listZakat = zakatRepository.getAllZakat()
           activity?.runOnUiThread {
               listZakat.let {
                   adapter.submitList(it)
               }
           }
        }

    }

    }
