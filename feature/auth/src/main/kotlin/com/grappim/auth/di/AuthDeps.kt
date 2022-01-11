package com.grappim.auth.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.AuthRepository
import com.grappim.domain.repository.GeneralRepository
import com.grappim.workers.WorkerHelper

interface AuthDeps : ComponentDeps {

    fun workerHelper(): WorkerHelper
    fun generalRepository(): GeneralRepository
    fun authRepository(): AuthRepository

    fun authScreenNavigator(): AuthScreenNavigator
}