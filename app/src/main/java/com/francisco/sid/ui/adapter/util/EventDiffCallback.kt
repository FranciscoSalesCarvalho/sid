package com.francisco.sid.ui.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.francisco.sid.data.model.Event

class EventDiffCallback: DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }
}