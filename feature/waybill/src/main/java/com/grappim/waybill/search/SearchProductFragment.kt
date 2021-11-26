package com.grappim.waybill.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.grappim.domain.base.Result
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.R
import com.grappim.waybill.WaybillSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchProductFragment : Fragment() {

    private val viewModel by viewModels<SearchProductViewModel>()
    private val sharedViewModel: WaybillSharedViewModel by hiltNavGraphViewModels(R.id.waybill_flow)

    private val args by navArgs<SearchProductFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                SearchProductFragmentScreen()
            }
        }
    }

    @Composable
    private fun SearchProductFragmentScreen() {
        val searchText by viewModel.searchText.collectAsState()
        val products by viewModel.productsFlow.collectAsState()
        val productState by viewModel.product.collectAsState()
        val waybillProductState by viewModel.waybillProduct.collectAsState()

        LoaderDialogCompose(
            show = productState is Result.Loading ||
              waybillProductState is Result.Loading
        )

        LaunchedEffect(key1 = productState, block = {})
        LaunchedEffect(key1 = waybillProductState, block = {})

        SearchProductScreen(
            onBackClick = sharedViewModel::onBackPressed,
            searchText = searchText,
            setSearchText = viewModel::setSearchText,
            products = products,
            onProductClick = viewModel::checkProductInWaybill
        )
    }

//    private fun observeViewModel() {
//        viewModel.product.observe(viewLifecycleOwner) {
//            loader.showOrHide(it is Result.Loading)
//            when (it) {
//                is Result.Success -> {
//                    findNavController().navigate(
//                        R.id.action_search_to_waybillProduct,
//                        bundleOf(
//                            WaybillProductFragment.ARG_WAYBILL_ID to args.waybillId,
//                            WaybillProductFragment.ARG_PRODUCT to it.data
//                        )
//                    )
//                }
//                is Result.Error -> {
//                    showToast(getString(R.string.waybill_error_no_product))
//                }
//            }
//        }
//        viewModel.waybillProduct.observe(viewLifecycleOwner) {
//            loader.showOrHide(it is Result.Loading)
//            when (it) {
//                is Result.Success -> {
//                    findNavController()
//                        .navigate(
//                            R.id.action_search_to_waybillProduct,
//                            bundleOf(
//                                WaybillProductFragment.ARG_WAYBILL_ID to args.waybillId,
//                                WaybillProductFragment.ARG_WAYBILL_PRODUCT to it.data
//                            )
//                        )
//                }
//            }
//        }
//    }
//
//    override fun onProductClick(product: Product) {
//        viewModel.checkProductInWaybill(
//            product.barcode,
//            args.waybillId
//        )
//    }
}