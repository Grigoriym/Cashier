package com.grappim.cashier.ui.auth

import android.os.Bundle
import android.text.SpannableString
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.*
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.utils.PHONE_NUMBER_FORMAT
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.databinding.FragmentAuthBinding
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.zhuinden.livedatacombinetuplekt.combineTuple
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import reactivecircus.flowbinding.android.widget.textChanges
import timber.log.Timber

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val binding: FragmentAuthBinding by viewBinding(FragmentAuthBinding::bind)

    private val loader: CashierLoaderDialog by lazy {
        CashierLoaderDialog(requireContext())
    }
    private val viewModel: AuthViewModel by viewModels()

    private lateinit var phoneMaskedTextChangedListener: MaskedTextChangedListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        combineTuple(viewModel.isPasswordNotBlank, viewModel.isFullPhoneNumberEntered)
            .observe(viewLifecycleOwner) { (isPasswordNotBlank, phoneFullyEntered) ->
                if (phoneFullyEntered == true) {
                    binding.tilPhoneNumber.endIconMode = TextInputLayout.END_ICON_CUSTOM
                    binding.tilPhoneNumber.endIconDrawable = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_check_circle_green
                    )
                } else {
                    binding.tilPhoneNumber.endIconMode = TextInputLayout.END_ICON_NONE
                }

                binding.buttonSignIn.isEnabled = isPasswordNotBlank == true &&
                        phoneFullyEntered == true
            }

        lifecycleScope.launch {
            viewModel.loginStatus.observe(viewLifecycleOwner, ::showLoginStatus)
        }
    }

    private fun showLoginStatus(data: Resource<Unit>) {
        loader.showOrHide(data is Resource.Loading)
        when (data) {
            is Resource.Success -> {
                findNavController().navigate(R.id.action_authFragment_to_selectOutletFragment)
            }
            is Resource.Error -> {
                showToast(getErrorMessage(data.exception))
            }
        }
    }

    private fun initViews() {
        phoneMaskedTextChangedListener = MaskedTextChangedListener.installOn(
            binding.editPhoneNumber,
            PHONE_NUMBER_FORMAT,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    Timber.d("masked: $maskFilled, $extractedValue | $formattedValue")

                    viewModel.onPhoneNumberEntered(maskFilled)
                    viewModel.setPhoneNumber(extractedValue)
                }
            }
        )
        binding.editPhoneNumber.hint = phoneMaskedTextChangedListener.placeholder()

        val textForForgotPass = SpannableString(
            getString(R.string.auth_forgot_password)
        )
            .color(ContextCompat.getColor(requireContext(), R.color.cashier_blue))
            .underline()
//        binding.buttonForgotPassword.text = textForForgotPass
//        binding.buttonForgotPassword.setSafeOnClickListener {
//
//        }

        binding.buttonSignIn.setSafeOnClickListener {
            hideKeyboard2()
            viewModel.login(
                mobile = viewModel.phoneNumber.value!!,
                password = binding.editPassword.text.toString()
            )
        }

        binding.editPassword.textChanges().onEach {
            viewModel.onPasswordEntered(it.toString())
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.imageView.setOnClickListener { v ->
            v.animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .withEndAction {
                    v.animate()
                        .scaleY(1f)
                        .scaleX(1f)
                }
        }
    }

}