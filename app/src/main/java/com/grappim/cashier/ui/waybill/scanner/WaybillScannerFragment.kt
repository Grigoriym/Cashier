package com.grappim.cashier.ui.waybill.scanner

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.eazypermissions.common.model.PermissionResult
import com.eazypermissions.dsl.extension.requestPermissions
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.databinding.FragmentScannerBinding
import com.grappim.cashier.ui.waybill.product.WaybillProductFragment
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WaybillScannerFragment : Fragment(R.layout.fragment_scanner) {

    private val viewBinding: FragmentScannerBinding by viewBinding(FragmentScannerBinding::bind)
    private val viewModel by viewModels<WaybillScannerViewModel>()

    companion object {
        private const val CAMERA_REQUEST_CODE = 2300
    }

    private val beepManager: BeepManager by lazy {
        BeepManager(requireActivity())
    }
    private val args by navArgs<WaybillScannerFragmentArgs>()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions(
            Manifest.permission.CAMERA
        ) {
            requestCode = CAMERA_REQUEST_CODE
            resultCallback = {
                when (this) {
                    is PermissionResult.PermissionGranted -> {
                        initScanner()
                    }
                    is PermissionResult.PermissionDenied -> {

                    }
                    is PermissionResult.ShowRational -> {
                        showToast("Need camera permissions")
                    }
                    is PermissionResult.PermissionDeniedPermanently -> {
                        showToast("Need camera permissions")
                    }
                }
            }
        }
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
                is Resource.Success -> {
                    findNavController().navigate(
                        R.id.action_scanner_to_waybillProduct,
                        bundleOf(
                            WaybillProductFragment.ARG_WAYBILL_ID to args.waybillId,
                            WaybillProductFragment.ARG_PRODUCT to it.data
                        )
                    )
                }
                is Resource.Error -> {
                    showToast(getString(R.string.waybill_error_no_product))
                }
            }
        }
        viewModel.waybillProduct.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    findNavController().navigate(
                        R.id.action_scanner_to_waybillProduct,
                        bundleOf(
                            WaybillProductFragment.ARG_WAYBILL_ID to args.waybillId,
                            WaybillProductFragment.ARG_WAYBILL_PRODUCT to it.data
                        )
                    )
                }
            }
        }
    }

    private fun handleSingleScan(result: String) {
        Timber.tag("cashier").d("scanned barcode $result")
        viewModel.checkProductInWaybill(
            barcode = result,
            waybillId = args.waybillId
        )
    }
}