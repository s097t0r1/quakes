package com.example.quakeapplication.ui.quakes

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quakeapplication.convertMMItoColor
import com.example.quakeapplication.data.Quake
import com.example.quakeapplication.databinding.ItemQuakeBinding
import com.example.quakeapplication.formatDate
import kotlin.math.roundToInt

class QuakesAdapter(private val clickListener: QuakeClickListener) : ListAdapter<Quake, QuakesAdapter.QuakesViewHolder>(QuakesDiffCallback()) {

    class QuakesViewHolder private constructor(private val binding: ItemQuakeBinding) : RecyclerView.ViewHolder(binding.root) {

        private val mTextViewLocality = binding.textViewLocality
        private val mTextViewDateOfMeasure = binding.textViewTime
        private val mTextViewBubble = binding.textViewMMI
        private val mViewLabel = binding.viewLabel


        companion object {
            fun from(parent: ViewGroup): QuakesViewHolder {
                val binding = ItemQuakeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return QuakesViewHolder(binding)
            }
        }

        fun bind(quake: Quake, clickListener: QuakeClickListener/* clickListener: QuakeListener */, res: Resources) {

            binding.quake = quake
            binding.clickListener = clickListener

            mTextViewLocality.text = quake.locality
            mTextViewDateOfMeasure.text = formatDate(quake.time)

            val cardColor = convertMMItoColor(quake.magnitude.roundToInt(), res)

            mTextViewBubble.bubbleColor = cardColor
            mTextViewBubble.text = quake.magnitude.roundToInt().toString()


            mViewLabel.setBackgroundColor(cardColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakesViewHolder {
        return QuakesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: QuakesViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener, holder.itemView.context.resources)
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

fun interface QuakeClickListener {
    fun onClick(publicID: String)
}