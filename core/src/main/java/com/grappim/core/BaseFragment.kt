package com.grappim.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

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