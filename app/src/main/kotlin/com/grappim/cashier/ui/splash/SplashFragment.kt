package com.grappim.cashier.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.grappim.cashier.R
import com.grappim.cashier.ui.root.MainActivity
import com.grappim.core.BaseFragment
import com.grappim.core.di.vm.MultiViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashFragment : BaseFragment<SplashViewModel>(R.layout.fragment_splash) {

    companion object {
        private const val SCREEN_DELAY = 1_000L
    }

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    private fun performInject(){
        (requireActivity() as MainActivity).activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.imageLogo)
            .animate()
            .alpha(1f)
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(SCREEN_DELAY)
            .interpolator = AccelerateDecelerateInterpolator()

        lifecycleScope.launch {
            delay(SCREEN_DELAY)
            viewModel.goToAuth()
        }
    }

}