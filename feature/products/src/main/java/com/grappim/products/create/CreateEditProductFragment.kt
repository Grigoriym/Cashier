package com.grappim.products.create

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
import androidx.navigation.fragment.navArgs
import com.grappim.domain.base.Try
import com.grappim.extensions.assistedViewModel
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import javax.inject.Inject

class CreateEditProductFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: CreateEditProductViewModel.Factory

    private val args by navArgs<CreateEditProductFragmentArgs>()
    private val createEditFlow: CreateEditFlow by lazy {
        args.flowType
    }

    private val viewModel by assistedViewModel {
        viewModelFactory.create(
            createEditFlow = args.flowType,
            productToEdit = args.product,
            scannedBarcode = args.barcode
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                CreateEditProductFragmentScreen()
            }
        }
    }

    private fun showCreateEditResult(data: Try<Unit>) {
        when (data) {
            is Try.Error -> {
                showToast(getErrorMessage(data.exception))
            }
        }
    }

    @Composable
    private fun CreateEditProductFragmentScreen() {
        val categoryList by viewModel.categoriesFlow.collectAsState()
        val selectedCategory by viewModel.selectedCategory.collectAsState()
        val productName by viewModel.productName.collectAsState()
        val productUnits by viewModel.productUnits.collectAsState()
        val selectedUnit by viewModel.selectedUnit.collectAsState()
        val barcode by viewModel.barcode.collectAsState()
        val purchasePrice by viewModel.purchasePrice.collectAsState()
        val sellingPrice by viewModel.sellingPrice.collectAsState()
        val markup by viewModel.markup.collectAsState()
        val amountAndUnit by viewModel.amountAndUnit.collectAsState()
        val dropDownExpanded by viewModel.dropDownExpanded.collectAsState()
        val createEditProductResult by viewModel.createProduct.collectAsState()

        LoaderDialogCompose(show = createEditProductResult is Try.Loading)

        LaunchedEffect(key1 = createEditProductResult) {
            showCreateEditResult(createEditProductResult)
        }

        CreateEditProductScreen(
            onBackPressed = viewModel::onBackPressed,
            onCreateProductClick = viewModel::createEditProduct,
            productName = productName,
            setProductName = viewModel::setProductName,
            units = productUnits,
            selectedUnit = selectedUnit,
            onUnitChanged = viewModel::setProductUnit,
            onMinusClick = viewModel::subtractQuantity,
            onPlusClick = viewModel::addQuantity,
            quantityAndUnitText = amountAndUnit,
            purchasePrice = purchasePrice,
            setPurchasePrice = viewModel::setPurchasePrice,
            extraPrice = markup,
            setExtraPrice = viewModel::setMarkup,
            sellingPrice = sellingPrice,
            setSellingPrice = viewModel::setSellingPrice,
            barcode = barcode,
            setBarcode = viewModel::setBarcode,
            dropDownExpanded = dropDownExpanded,
            onDismissDropDown = viewModel::dismissDropDown,
            onDropDownClick = viewModel::onDropDownExpand,
            categoryItems = categoryList,
            onCategoryClick = viewModel::selectCategory,
            createEditFlow = createEditFlow,
            selectedCategory = selectedCategory,
            onScanClick = {
//                findNavController().navigate(
//                    CreateEditProductFragmentDirections.actionCreateProductToScanner()
//                )
            }
        )
    }
}