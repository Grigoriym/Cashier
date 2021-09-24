package com.grappim.cashier.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment() {

    abstract val viewModel: ViewModel
}