package com.grappim.cashier.core.view

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ProgressBar
import com.grappim.cashier.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class CashierLoaderDialog(
    context: Context,
    cancelable: Boolean = true
) {
    private var dialog: Dialog = Dialog(context, R.style.Dialog_Transparent)
    private var progressBar: CircularProgressBar

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_cashier_loader_dialog, null)
        progressBar = view.findViewById(R.id.circularProgressBar)
        dialog.setContentView(view)
        dialog.setCancelable(cancelable)
    }

    fun show() {
        dialog.show()
    }

    fun hide() {
        dialog.dismiss()
    }

    fun showOrHide(show: Boolean) {
        when {
            show -> show()
            else -> hide()
        }
    }
}