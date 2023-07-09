package com.example.inventorytracking.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.inventorytracking.ds.Message
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

abstract class BaseFragment<VB: ViewBinding, VM: BaseViewModel> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding

    protected abstract val vm: VM

    abstract fun bind(): VB?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind()
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getMsg.observe(viewLifecycleOwner) {
            it?.let {
                showMessage(it.msg)
                vm.msgShown()
                if (it is Message.Success) {
                    safeNav()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Navigates to the specified action or pops the back stack.
     * @param actionId The resource id of the action to navigate to.
     * If null, the back stack will be popped.
     */
    protected fun safeNav(actionId: Int? = null) {
        try {
            actionId?.let {
                findNavController().navigate(actionId)
            } ?: findNavController().popBackStack()
        } catch (e: Exception) {
            showMessage(e.message)
        }
    }

    private fun showMessage(msg: String?) {
        binding?.root?.let {
            Snackbar.make(it, msg.toString(), Snackbar.LENGTH_SHORT).show()
        }
    }
}
