package com.example.quakeapplication.quakes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quakeapplication.databinding.QuakeItemBinding
import com.example.quakeapplication.data.Quake

class QuakesAdapter() : ListAdapter<Quake, QuakesAdapter.QuakesViewHolder>(QuakesDiffCallback()) {

    class QuakesViewHolder private constructor(val binding: QuakeItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): QuakesViewHolder {
                val binding = QuakeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return QuakesViewHolder(binding)
            }
        }

        fun bind(quake: Quake) {
            binding.quake = quake
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakesViewHolder {
        return QuakesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: QuakesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class QuakesDiffCallback : DiffUtil.ItemCallback<Quake>() {
    override fun areItemsTheSame(oldItem: Quake, newItem: Quake): Boolean {
        return oldItem.publicID == newItem.publicID
    }

    override fun areContentsTheSame(oldItem: Quake, newItem: Quake): Boolean {
        return oldItem == newItem
    }

}