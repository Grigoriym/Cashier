package com.grappim.cashier.ui.paymentmethod

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.databinding.FragmentPaymentMethodBinding
import com.grappim.cashier.di.modules.DecimalFormatSimple
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class PaymentMethodFragment : Fragment(R.layout.fragment_payment_method),
    PaymentMethodClickListener {

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    private val viewBinding: FragmentPaymentMethodBinding by viewBinding(
        FragmentPaymentMethodBinding::bind
    )
    private val viewModel: PaymentMethodViewModel by viewModels()
    private val paymentMethodAdapter by lazy {
        PaymentMethodAdapter(this)
    }
    private val loader: CashierLoaderDialog by lazy {
        CashierLoaderDialog(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.paymentItems.observe(viewLifecycleOwner) {
            paymentMethodAdapter.setItems(it)
        }
        viewModel.basketCount.observe(viewLifecycleOwner) {
            viewBinding.textItemsCount.text = dfSimple.format(it)
        }
        viewModel.basketSum.observe(viewLifecycleOwner) {
            viewBinding.textPrice.text = getString(
                R.string.title_price_with_currency,
                dfSimple.format(it)
            )
        }
        viewModel.paymentStatus.observe(viewLifecycleOwner) {
            loader.showOrHide(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    findNavController()
                        .navigate(R.id.action_paymentMethod_to_salesFragment)
                }
                is Resource.Error -> {
                    showToast(getErrorMessage(it.exception))
                }
            }
        }
    }

    private fun initViews() {
        with(viewBinding) {
            buttonBack.setSafeOnClickListener {
                findNavController().popBackStack()
            }
            recyclerPaymentMethods.adapter = paymentMethodAdapter
        }
    }

    override fun onClick(paymentMethod: PaymentMethod) {
        viewModel.makePayment(paymentMethod)
    }
}