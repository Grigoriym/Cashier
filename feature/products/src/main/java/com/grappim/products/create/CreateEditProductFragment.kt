package com.grappim.products.create

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.Chip
import com.grappim.calculations.asBigDecimal
import com.grappim.domain.base.Result
import com.grappim.domain.model.base.ProductUnit
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.setSafeOnClickListener
import com.grappim.extensions.showToast
import com.grappim.products.R
import com.grappim.products.databinding.FragmentCreateEditProductBinding
import com.grappim.uikit.view.CashierLoaderDialog
import com.zhuinden.livedatacombinetuplekt.combineTupleNonNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateEditProductFragment : Fragment(R.layout.fragment_create_edit_product) {

    private val viewModel: CreateEditProductViewModel by viewModels()
    private val viewBinding: FragmentCreateEditProductBinding by viewBinding(
        FragmentCreateEditProductBinding::bind
    )
    private val args by navArgs<CreateEditProductFragmentArgs>()
    private val loader: CashierLoaderDialog by lazy {
        CashierLoaderDialog(requireContext())
    }
    private val createEditFlow: CreateEditFlow by lazy {
        args.flowType
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        with(viewBinding) {
            when (createEditFlow) {
                CreateEditFlow.CREATE -> {
                    textLabel.text = getString(R.string.title_create_product)
                    buttonEditCreateProduct.text = getString(R.string.action_create_product)
                }
                CreateEditFlow.EDIT -> {
                    textLabel.text = getString(R.string.title_edit_product)
                    buttonEditCreateProduct.text = getString(R.string.action_edit_product)

                    viewModel.setQuantity(args.product!!.amount)
                    viewModel.setUnit(args.product!!.unit)

                    editName.setText(args.product!!.name)
                    editBarcode.setText(args.product!!.barcode)

                    viewModel.setPurchasePrice(args.product!!.purchasePrice)
                    viewModel.setSellingPrice(args.product!!.sellingPrice)
                    viewModel.setExtraPrice()
                }
            }

            buttonEditCreateProduct.setSafeOnClickListener {
                viewModel.editCreateProduct(
                    createEditFlow = createEditFlow,
                    name = editName.text.toString(),
                    barcode = editBarcode.text.toString(),
                    sellingPrice = editSellingPrice.text.toString().asBigDecimal(),
                    purchasePrice = editPurchasePrice.text.toString().asBigDecimal(),
                    amount = viewModel.quantity.value!!.asBigDecimal(),
                    unit = viewModel.unit.value!!,
                    product = args.product
                )
            }
            buttonBack.setSafeOnClickListener {
                findNavController().popBackStack()
            }
            buttonScan.setSafeOnClickListener {
//                findNavController().navigate(
//                    CreateEditProductFragmentDirections.actionCreateProductToScanner()
//                )
            }
            buttonMinus.setOnClickListener {
                viewModel.minusQuantity()
            }
            buttonPlus.setOnClickListener {
                viewModel.plusQuantity()
            }

            if (args.barcode?.isNotEmpty() == true) {
                viewBinding.editBarcode.setText(args.barcode)
            }
            chipGroupUnits.setOnCheckedChangeListener { group, checkedId ->
                val selectedChip = group.children
                    .toList()
                    .filter { (it as Chip).isChecked }
                    .joinToString {
                        (it as Chip).text.toString()
                    }
                viewModel.setUnit(ProductUnit.getProductUnitByValue(selectedChip))
            }

            ProductUnit.values().forEach {
                val chip = layoutInflater.inflate(
                    R.layout.layout_chip_unit,
                    null
                ) as Chip
                chip.text = it.value
                chip.tag = it
                chip.id = View.generateViewId()
                chipGroupUnits.addView(chip)
            }

            chipGroupUnits.check(chipGroupUnits[0].id)

            editSellingPrice.doAfterTextChanged {
                if (editSellingPrice.isFocused) {
                    viewModel.onSalesPriceChanged(it.toString())
                }
            }
            editPurchasePrice.doAfterTextChanged {
                if (editPurchasePrice.isFocused) {
                    viewModel.onCostPriceChanged(it.toString())
                }
            }
            editExtraPrice.doAfterTextChanged {
                if (editExtraPrice.isFocused) {
                    viewModel.onExtraPriceChanged(it.toString())
                }
            }
        }
    }

    private fun createProductResult(data: Result<Unit>) {
        loader.showOrHide(data is Result.Loading)
        when (data) {
            is Result.Success -> {
                findNavController().popBackStack()
            }
            is Result.Error -> {
                showToast(getErrorMessage(data.exception))
            }
        }
    }

    private fun observeViewModel() {
        viewModel.createProduct.observe(viewLifecycleOwner, ::createProductResult)

        viewModel.sellingPrice.observe(viewLifecycleOwner) {
            if (!viewBinding.editSellingPrice.isFocused) {
                viewBinding.editSellingPrice.setText(it)
            }
        }
        viewModel.purchasePrice.observe(viewLifecycleOwner) {
            if (!viewBinding.editPurchasePrice.isFocused) {
                viewBinding.editPurchasePrice.setText(it)
            }
        }
        viewModel.markup.observe(viewLifecycleOwner) {
            if (!viewBinding.editExtraPrice.isFocused) {
                viewBinding.editExtraPrice.setText(it)
            }
        }
        combineTupleNonNull(
            viewModel.quantity,
            viewModel.unit
        ).observe(viewLifecycleOwner) { (quantity: String, unit: ProductUnit) ->
            viewBinding.textQuantity.text = getString(
                R.string.title_quantity_and_unit,
                quantity,
                unit.value
            )
        }

        viewModel.categories.observe(viewLifecycleOwner) { list ->
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.item_drop_down_item,
                list.map {
                    it.name
                }
            )

            viewBinding.textDropDown.setAdapter(adapter)
            viewBinding.textDropDown.setOnItemClickListener { parent, view, position, id ->
                viewModel.setCategory(position)
            }
            if (createEditFlow == CreateEditFlow.EDIT) {
                var categoryIndex: Int? = null
                list.mapIndexed { index, categoryEntity ->
                    if (categoryEntity.id == args.product!!.categoryId) {
                        categoryIndex = index
                    }
                }
                categoryIndex?.let {
                    viewBinding.textDropDown.setText(list[it].name, false)
                }
            }
        }
    }
}