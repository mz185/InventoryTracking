package com.example.inventorytracking.ds

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

sealed class Message {
    abstract val msg: String

    data class Success(override val msg: String) : Message()
    data class Failure(override val msg: String) : Message()

    companion object {
        fun success(msg: String): Message = Success(msg)
        fun failure(msg: String): Message = Failure(msg)
    }
}
