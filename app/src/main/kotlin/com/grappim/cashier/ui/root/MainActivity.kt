package com.grappim.cashier.ui.root

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.grappim.cashier.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {

        fun start(
            context: Context
        ) = context.startActivity(Intent(context, MainActivity::class.java))

    }

}