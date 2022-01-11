package com.grappim.core

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.logger.logD
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logD("$this fragment onViewCreated")
        observeViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logD("$this fragment onAttach")
    }

    override fun onResume() {
        super.onResume()
        logD("$this fragment onResume")
    }

    override fun onStart() {
        super.onStart()
        logD("$this fragment onStart")
    }

    override fun onPause() {
        super.onPause()
        logD("$this fragment onPause")
    }

    override fun onStop() {
        super.onStop()
        logD("$this fragment onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logD("$this fragment onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logD("$this fragment onDestroyView")
    }

    private fun observeViewModel() {
        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                showToast(getErrorMessage(it))
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : ViewModel> Fragment.assistedViewModel(
        crossinline creator: () -> T
    ): Lazy<T> = createViewModelLazy(
        viewModelClass = T::class,
        storeProducer = { viewModelStore },
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    creator.invoke() as T
            }
        })
}