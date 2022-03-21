package com.grappim.scanner.ui

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.core.utils.BundleArgsHelper
import com.grappim.extensions.showToast
import com.grappim.logger.logD
import com.grappim.navigation.router.FlowRouter
import com.grappim.scanner.R
import com.grappim.scanner.di.DaggerScannerComponent
import com.grappim.scanner.di.ScannerComponent
import com.grappim.uikit.databinding.FragmentScannerBinding
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult

class ScannerFragment : BaseFlowFragment<ScannerViewModel>(
    R.layout.fragment_scanner
) {

    private val component: ScannerComponent by lazy {
        DaggerScannerComponent
            .factory()
            .create(
                scannerDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    override val viewModel: ScannerViewModel by viewModels {
        viewModelFactory
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val viewBinding: FragmentScannerBinding by viewBinding(FragmentScannerBinding::bind)

    private var lastTimeStamp: Long = 0
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

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
        }
    }

    private val barcodeCallbackContinuous = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            result?.text?.let {
                if (System.currentTimeMillis() - lastTimeStamp < CONTINUOUS_SCAN_DELAY) {
                    return
                }
                beepManager.playBeepSoundAndVibrate()
                handleContinuousScan(it)
                lastTimeStamp = System.currentTimeMillis()
            }
        }

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions.launch(Manifest.permission.CAMERA)
        viewBinding.scannerView.setStatusText("")
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
//        when (args.scanType) {
//            ScanType.CONTINUOUS -> {
//                viewBinding.scannerView.decodeContinuous(barcodeCallbackContinuous)
//            }
//            ScanType.SINGLE -> {
        viewBinding.scannerView.decodeSingle(barcodeCallbackSingle)
//            }
//        }
    }

    private fun handleContinuousScan(result: String) {
        logD("scanned barcode $result")
    }

    private fun handleSingleScan(result: String) {
        logD("asdf scanned barcode $result")
        setFragmentResult(
            BundleArgsHelper.ProductScannerArgs.PRODUCT_SCANNER_REQUEST_KEY,
            bundleOf(
                BundleArgsHelper.ProductScannerArgs.ARG_KEY_SCANNED_BARCODE to result
            )
        )

        viewModel.onBackPressed()
    }

    companion object {
        private const val CONTINUOUS_SCAN_DELAY = 1000
        private const val CAMERA_REQUEST_CODE = 2300

        fun newInstance(args: Bundle?) =
            ScannerFragment().apply {
                arguments = args
            }
    }
}