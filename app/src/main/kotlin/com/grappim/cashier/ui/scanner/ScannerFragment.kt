package com.grappim.cashier.ui.scanner

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.grappim.cashier.R
import com.grappim.extensions.showToast
import com.grappim.cashier.databinding.FragmentScannerBinding
import com.grappim.logger.logD
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScannerFragment : Fragment(R.layout.fragment_scanner) {

    private val viewBinding: FragmentScannerBinding by viewBinding(FragmentScannerBinding::bind)

    companion object {
        private const val CONTINUOUS_SCAN_DELAY = 1000
        private const val CAMERA_REQUEST_CODE = 2300
    }

    private var lastTimeStamp: Long = 0
    private val beepManager: BeepManager by lazy {
        BeepManager(requireActivity())
    }
    private val args by navArgs<ScannerFragmentArgs>()

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
        when (args.scanType) {
            ScanType.CONTINUOUS -> {
                viewBinding.scannerView.decodeContinuous(barcodeCallbackContinuous)
            }
            ScanType.SINGLE -> {
                viewBinding.scannerView.decodeSingle(barcodeCallbackSingle)
            }
        }
    }

    private fun handleContinuousScan(result: String) {
        logD("scanned barcode $result")
    }

    private fun handleSingleScan(result: String) {
        logD("scanned barcode $result")
        findNavController().navigate(
            ScannerFragmentDirections.actionScannerToCreateProduct(result)
        )
    }
}

enum class ScanType {
    SINGLE,
    CONTINUOUS
}