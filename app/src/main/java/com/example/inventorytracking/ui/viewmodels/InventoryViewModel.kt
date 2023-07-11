package com.example.inventorytracking.ui.viewmodels

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.inventorytracking.data.repos.InventoryRepository
import com.example.inventorytracking.ds.Message
import com.example.inventorytracking.models.Item
import com.example.inventorytracking.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val inventoryRepository: InventoryRepository
) : BaseViewModel() {

    private val _items = MutableLiveData<Map<String, Item>>(emptyMap())
    val items: LiveData<List<Item>> get() = _items.map { it.values.toList() }

    private val _totalValue = MutableLiveData<String>()
    val totalValue: LiveData<String> get() = _totalValue

    private val _editItem = MutableLiveData<Item>()
    val editItem: LiveData<Item> get() = _editItem

    fun setEditItem(itemName: String) {
        _editItem.value = _items.value?.get(itemName)
    }

    init {
        fetchItemsAndValue()
    }

    private fun fetchItemsAndValue() {
        viewModelScope.launch {
            inventoryRepository.getInventory().onSuccess { itemList ->
                _items.postValue(itemList.associateBy { it.name })
            }.onFailure { e ->
                msg.postValue(Message.failure(e.message ?: ""))
            }

            inventoryRepository.calculateTotalValue().onSuccess { totalValue ->
                _totalValue.postValue(totalValue?.toString() ?: "0")
            }.onFailure { e ->
                msg.postValue(Message.failure(e.message ?: ""))
            }
        }
    }

    fun addItem(name: String, quantity: Int?, price: Double?) {
        if (name.isNotEmpty() && quantity != null && price != null) {
            viewModelScope.launch {
                inventoryRepository.addItem(Item(name, quantity, price)).onSuccess {
                    fetchItemsAndValue()
                    msg.postValue(
                        if (it) {
                            Message.success("Success! Item added.")
                        } else {
                            Message.failure("Failure. Item not added.")
                        }
                    )
                }.onFailure {
                    if (it is SQLiteConstraintException) {
                        msg.postValue(Message.failure("Item already exists."))
                    } else {
                        it.message?.let { e ->
                            msg.postValue(Message.failure(e))
                        }
                    }
                }
            }
        } else {
            msg.postValue(Message.failure("Invalid field data."))
        }
    }

    fun deleteItem(name: String) = viewModelScope.launch {
        inventoryRepository.deleteItem(name).onSuccess {
            fetchItemsAndValue()
            msg.postValue(
                if (it) {
                    Message.success("Success! Item deleted.")
                } else {
                    Message.failure("Failure. Item not deleted.")
                }
            )
        }.onFailure {
            it.message?.let { e ->
                msg.postValue(Message.failure(e))
            }
        }
    }

    fun updateItem(name: String, newQuantity: Int?) {
        if (newQuantity != null) {
            _items.value?.get(name)?.quantity?.let { oldQuantity ->
                if (newQuantity != oldQuantity) {
                    viewModelScope.launch {
                        inventoryRepository.updateItem(name, newQuantity).onSuccess {
                            fetchItemsAndValue()
                            msg.postValue(
                                if (it) {
                                    Message.success("Success! Item updated.")
                                } else {
                                    Message.failure("Failure. Item not updated.")
                                }
                            )
                        }.onFailure {
                            it.message?.let { e ->
                                msg.postValue(Message.failure(e))
                            }
                        }
                    }
                } else {
                    msg.postValue(Message.failure("Nothing to update!"))
                }
            }
        } else {
            msg.postValue(Message.failure("Invalid quantity format."))
        }
    }
}
