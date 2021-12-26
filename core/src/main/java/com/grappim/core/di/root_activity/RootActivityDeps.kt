package com.grappim.core.di.root_activity

import com.grappim.di.deps.ComponentDeps
import com.grappim.workers.WorkerHelper

interface RootActivityDeps : ComponentDeps {
    fun workerHelper(): WorkerHelper
}
