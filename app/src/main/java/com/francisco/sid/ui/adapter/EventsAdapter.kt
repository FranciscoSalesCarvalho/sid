package com.francisco.sid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.francisco.sid.data.model.Event
import com.francisco.sid.databinding.EventItemBinding
import com.francisco.sid.ui.adapter.listener.EventsListener
import com.francisco.sid.ui.adapter.util.EventDiffCallback

class EventsAdapter(
    private val clickListener: EventsListener
): ListAdapter<Event, EventsAdapter.EventViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(layoutInflater)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class EventViewHolder(
        private val binding: EventItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Event) {
            binding.event = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}