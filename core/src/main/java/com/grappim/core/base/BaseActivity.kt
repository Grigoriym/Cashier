package com.grappim.core.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.grappim.navigation.router.ActivityRouter

abstract class BaseActivity<VM : BaseActivityViewModel> : AppCompatActivity {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    abstract val viewModel: VM
    abstract val activityRouter: ActivityRouter
}