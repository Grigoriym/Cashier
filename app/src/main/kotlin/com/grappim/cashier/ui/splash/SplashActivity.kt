package com.grappim.cashier.ui.splash

import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.databinding.ActivitySplashBinding
import com.grappim.core.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    companion object {
        private const val SCREEN_DELAY = 1_000L
    }

    private val viewBinding: ActivitySplashBinding by viewBinding(ActivitySplashBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding.imageLogo
            .animate()
            .alpha(1f)
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(SCREEN_DELAY)
            .interpolator = AccelerateDecelerateInterpolator()

        lifecycleScope.launch {
            delay(SCREEN_DELAY)
            MainActivity.start(this@SplashActivity)

            finish()
        }
    }
}