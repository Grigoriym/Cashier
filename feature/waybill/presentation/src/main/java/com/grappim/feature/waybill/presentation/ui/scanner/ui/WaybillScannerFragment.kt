package com.grappim.feature.waybill.presentation.ui.scanner.ui

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.grappim.cashier.core.base.BaseFragment
import com.grappim.cashier.core.di.componentsdeps.findComponentDependencies
import com.grappim.cashier.core.utils.BundleArgsHelper
import com.grappim.extensions.showToast
import com.grappim.feature.waybill.presentation.ui.scanner.di.DaggerWaybillScannerComponent
import com.grappim.feature.waybill.presentation.ui.scanner.di.WaybillScannerComponent
import com.grappim.logger.logD
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.R
import com.grappim.uikit.databinding.FragmentScannerBinding
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult

class WaybillScannerFragment : BaseFragment<WaybillScannerViewModel>(
    R.layout.fragment_scanner
) {

    private val component: WaybillScannerComponent by lazy {
        DaggerWaybillScannerComponent
            .builder()
            .waybillScannerDeps(findComponentDependencies())
            .build()
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val viewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    private val viewBinding: FragmentScannerBinding by viewBinding(FragmentScannerBinding::bind)
    override val viewModel by viewModels<WaybillScannerViewModel> {
        viewModelFactory
    }

    private val beepManager: BeepManager by lazy {
        BeepManager(requireActivity())
    }

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                initScanner()
            } else {
                showToast("Need camera permissions")
            }
        }

    private val barcodeCallbackSingle = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            result?.text?.let {
                beepManager.playBeepSoundAndVibrate()
                handleSingleScan(it)
            }
        }

        @Suppress("EmptyFunctionBlock")
        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions.launch(Manifest.permission.CAMERA)
        viewBinding.scannerView.setStatusText("")
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewBinding.scannerView.resume()
    }

    override fun onPause() {
        viewBinding.scannerView.pause()
        super.onPause()
    }

    private fun initScanner() {
        viewBinding.scannerView.decodeSingle(barcodeCallbackSingle)
    }

    private fun observeViewModel() {
        viewModel.product.observe(viewLifecycleOwner) {
            when (it) {
                is ProductByBarcodeResult.SuccessResult -> {
                    val args = bundleOf(
                        BundleArgsHelper.Waybill.ARG_KEY_PRODUCT to it.result
                    )
                    flowRouter.goToWaybillProduct(args)
                }
                is ProductByBarcodeResult.ErrorResult -> {
                    showToast(getString(R.string.waybill_error_no_product))
                }
            }
        }
        viewModel.waybillProduct.observe(viewLifecycleOwner) {
            when (it) {
                is WaybillProductByBarcodeResult.SuccessResult -> {
                    val args = bundleOf(
                        BundleArgsHelper.Waybill.ARG_KEY_WAYBILL_PRODUCT to it.result
                    )
                    flowRouter.goToWaybillProduct(args)
                }
            }
        }
    }

    private fun handleSingleScan(result: String) {
        logD("scanned barcode $result")
        viewModel.checkProductInWaybill(
            barcode = result
        )
    }
}
