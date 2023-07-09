package com.example.inventorytracking.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inventorytracking.ds.Message

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

abstract class BaseViewModel : ViewModel() {

    protected val msg = MutableLiveData<Message?>()
    val getMsg: LiveData<Message?> get() = msg

    fun msgShown() {
        msg.postValue(null)
    }
}
