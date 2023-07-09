package com.example.inventorytracking.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.inventorytracking.databinding.FragmentAddItemBinding
import com.example.inventorytracking.ui.base.BaseFragment
import com.example.inventorytracking.ui.viewmodels.InventoryViewModel
import com.example.inventorytracking.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

@AndroidEntryPoint
class AddItemFragment : BaseFragment<FragmentAddItemBinding, InventoryViewModel>() {

    override fun bind() = FragmentAddItemBinding.inflate(layoutInflater)
    override val vm: InventoryViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            buttonAddItem.setOnClickListener {
                hideKeyboard()
                vm.addItem(
                    name = editTextName.editText?.text.toString().trim(),
                    quantity = editTextQuantity.editText?.text.toString().toIntOrNull(),
                    price = editTextPrice.editText?.text.toString().toDoubleOrNull()
                )
            }
        }
    }
}
