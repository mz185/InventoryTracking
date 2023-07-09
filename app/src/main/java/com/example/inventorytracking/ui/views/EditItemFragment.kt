package com.example.inventorytracking.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.inventorytracking.databinding.FragmentEditItemBinding
import com.example.inventorytracking.ui.base.BaseFragment
import com.example.inventorytracking.ui.viewmodels.InventoryViewModel
import com.example.inventorytracking.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

@AndroidEntryPoint
class EditItemFragment : BaseFragment<FragmentEditItemBinding, InventoryViewModel>() {

    override fun bind() = FragmentEditItemBinding.inflate(layoutInflater)
    override val vm: InventoryViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.editItem.observe(viewLifecycleOwner) {
            it?.let { item ->
                (activity as? AppCompatActivity)?.supportActionBar?.title = item.name

                binding?.run {
                    editTextQuantity.editText?.setText(it.quantity.toString())
                    editTextPrice.editText?.setText(it.price.toString())

                    buttonDeleteItem.setOnClickListener {
                        hideKeyboard()
                        vm.deleteItem(item.name)
                    }

                    buttonUpdateItem.setOnClickListener {
                        hideKeyboard()
                        vm.updateItem(
                            item.name,
                            editTextQuantity.editText?.text.toString().toIntOrNull()
                        )
                    }
                }
            }
        }
    }
}
