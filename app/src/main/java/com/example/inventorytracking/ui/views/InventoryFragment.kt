package com.example.inventorytracking.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.inventorytracking.R.id.action_InventoryFragment_to_AddItemFragment
import com.example.inventorytracking.R.id.action_InventoryFragment_to_EditItemFragment
import com.example.inventorytracking.databinding.FragmentInventoryBinding
import com.example.inventorytracking.ui.adapters.InventoryAdapter
import com.example.inventorytracking.ui.base.BaseFragment
import com.example.inventorytracking.ui.viewmodels.InventoryViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

@AndroidEntryPoint
class InventoryFragment : BaseFragment<FragmentInventoryBinding, InventoryViewModel>() {

    override fun bind() = FragmentInventoryBinding.inflate(layoutInflater)
    override val vm: InventoryViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            fab.setOnClickListener {
                safeNav(action_InventoryFragment_to_AddItemFragment)
            }

            vm.totalValue.observe(viewLifecycleOwner) {
                totalValue.text = it
            }

            val inventoryAdapter = InventoryAdapter()
            inventoryAdapter.setOnEditClickListener {
                vm.setEditItem(it)
                safeNav(action_InventoryFragment_to_EditItemFragment)
            }

            recycler.adapter = inventoryAdapter
            vm.items.observe(viewLifecycleOwner) {
                inventoryAdapter.submitList(it)
            }
        }
    }
}
