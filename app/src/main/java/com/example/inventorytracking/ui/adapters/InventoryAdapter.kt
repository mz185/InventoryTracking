package com.example.inventorytracking.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorytracking.databinding.ListItemViewBinding
import com.example.inventorytracking.models.Item

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

class InventoryAdapter : ListAdapter<Item, InventoryAdapter.InventoryViewHolder>(DiffCallback()) {

    private var onEditClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = InventoryViewHolder(
        ListItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnEditClickListener(action: (String) -> Unit) {
        onEditClick = action
    }

    private class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(old: Item, new: Item) = old.name == new.name
        override fun areContentsTheSame(old: Item, new: Item) = old.quantity == new.quantity
    }

    inner class InventoryViewHolder(
        private val binding: ListItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.apply {
                name.text = item.name
                quantity.text = item.quantity.toString()
                price.text = item.price.toString()
                edit.setOnClickListener {
                    onEditClick(item.name)
                }
            }
        }
    }
}
