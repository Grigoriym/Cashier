package di

import com.grappim.auth.AuthFragment
import com.grappim.core.di.components_deps.ComponentDeps
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import com.grappim.domain.repository.AuthRepository
import com.grappim.domain.repository.GeneralRepository
import com.grappim.navigation.Navigator
import com.grappim.workers.WorkerHelper
import dagger.Component

@[FeatureScope Component(
    modules = [
        AuthBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        AuthDeps::class
    ]
)]
internal interface AuthComponent {

    @Component.Builder
    interface Builder {
        fun deps(authDeps: AuthDeps): Builder

        fun build(): AuthComponent
    }

    fun inject(authFragment: AuthFragment)

}

interface AuthDeps : ComponentDeps {
    fun navigator(): Navigator
    fun workerHelper(): WorkerHelper
    fun generalRepository(): GeneralRepository
    fun authRepository(): AuthRepository
}