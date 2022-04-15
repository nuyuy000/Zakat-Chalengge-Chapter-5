package com.example.myapplicationn

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationn.Zakat
import com.example.myapplicationn.databinding.ListZakatLayoutBinding
import java.time.Instant
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.util.*

class ZakatAdapter(private val delClick:(Zakat)->Unit,
                      private val editClick:(Zakat)-> Unit)
    : ListAdapter<Zakat, ZakatAdapter.ViewHolder>(ZakatComparator()) {


    class ViewHolder(private val binding: ListZakatLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(currentZakat: Zakat,
                 delClick: (Zakat) -> Unit,
                 editClick: (Zakat) -> Unit){
            binding.tvJenisZakat.text = currentZakat.jenisZakat
            binding.tvDesc.text = currentZakat.deskripsi
            binding.btnDelete.setOnClickListener{
                delClick(currentZakat)
            }
            binding.btnEdit.setOnClickListener{
                editClick(currentZakat)
            }
        }

    }

    class ZakatComparator : DiffUtil.ItemCallback<Zakat>() {
        override fun areItemsTheSame(oldItem: Zakat, newItem: Zakat): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Zakat, newItem: Zakat): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListZakatLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), delClick, editClick)
    }

}