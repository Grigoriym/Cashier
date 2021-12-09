package com.grappim.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                showToast(getErrorMessage(it))
            }
        }
    }
}