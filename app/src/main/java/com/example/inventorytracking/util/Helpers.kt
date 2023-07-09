package com.example.inventorytracking.util

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

fun <F : Fragment> F.hideKeyboard() {
    activity?.let { activity ->
        view?.let {
            WindowCompat.getInsetsController(activity.window, it)
                .hide(WindowInsetsCompat.Type.ime())
        }
    }
}
