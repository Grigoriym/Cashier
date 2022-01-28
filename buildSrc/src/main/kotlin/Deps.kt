object Deps {
    object Kotlin {
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.serialization}"
        const val time = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.Kotlin.dateTime}"
        const val core = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.core}"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragment}"
        const val activity = "androidx.activity:activity:${Versions.AndroidX.activity}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraint}"
        const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.AndroidX.viewPager2}"
        const val swipeRefresh =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swipeRefresh}"
        const val startup = "androidx.startup:startup-runtime:${Versions.AndroidX.startup}"
        const val paging = "androidx.paging:paging-runtime:${Versions.AndroidX.paging}"
        const val pagingCommon = "androidx.paging:paging-common:${Versions.AndroidX.paging}"
        const val workManager = "androidx.work:work-runtime-ktx:${Versions.AndroidX.workManager}"

        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
        const val navigationUi =
            "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
        const val navigationRuntime =
            "androidx.navigation:navigation-runtime-ktx:${Versions.AndroidX.navigation}"

        const val lifecycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"

        const val roomCore = "androidx.room:room-ktx:${Versions.AndroidX.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"

        const val biometricKotlin =
            "androidx.biometric:biometric-ktx:${Versions.AndroidX.biometricKotlin}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.AndroidX.Compose.core}"
        const val material = "androidx.compose.material:material:${Versions.AndroidX.Compose.core}"
        const val toolingPreview =
            "androidx.compose.ui:ui-tooling-preview:${Versions.AndroidX.Compose.core}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.AndroidX.Compose.core}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.AndroidX.Compose.core}"
        const val runtimeLivedata =
            "androidx.compose.runtime:runtime-livedata:${Versions.AndroidX.Compose.core}"
        const val foundation =
            "androidx.compose.foundation:foundation:${Versions.AndroidX.Compose.core}"
        const val foundationLayout =
            "androidx.compose.foundation:foundation-layout:${Versions.AndroidX.Compose.core}"
        const val icons =
            "androidx.compose.material:material-icons-extended:${Versions.AndroidX.Compose.core}"

        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.Compose.lifecycle}"
        const val paging = "androidx.paging:paging-compose:${Versions.AndroidX.Compose.paging}"
        const val constraint =
            "androidx.constraintlayout:constraintlayout-compose:${Versions.AndroidX.Compose.constraint}"
        const val navigation =
            "androidx.navigation:navigation-compose:${Versions.AndroidX.Compose.navigation}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.Google.material}"
        const val zxingCore = "com.google.zxing:core:${Versions.Google.zxing}"

        const val dagger = "com.google.dagger:dagger:${Versions.Google.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.Google.dagger}"
    }

    object Testing {
        const val junit = "junit:junit:${Versions.Testing.junit}"
        const val androidJunit = "androidx.test.ext:junit:${Versions.Testing.androidJunit}"
        const val androidEspressoCore =
            "androidx.test.espresso:espresso-core:${Versions.Testing.androidEspressoCore}"

        const val assertJCore = "org.assertj:assertj-core:${Versions.Testing.assertJCore}"
        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.Kotlin.core}"
        const val kotlinCoroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
        const val androidCoreTesting =
            "android.arch.core:core-testing:${Versions.Testing.androidCoreTesting}"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
    }

    object Rx2 {
        const val android = "io.reactivex.rxjava2:rxandroid:${Versions.Rx2.android}"
        const val java = "io.reactivex.rxjava2:rxjava:${Versions.Rx2.java}"
        const val retrofitAdapter =
            "com.squareup.retrofit2:adapter-rxjava2:${Versions.Rx2.retrofitAdapter}"
    }

    const val lintApi = "com.android.tools.lint:lint-api:${Versions.lint}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val retrofitKotlinSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinSerialization}"

    const val desugar = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"
    const val debugDb =
        "com.github.amitshekhariitbhu.Android-Debug-Database:debug-db:${Versions.debugDb}"
    const val combineTupleLiveData =
        "com.github.Zhuinden:livedata-combinetuple-kt:${Versions.combineTupleLiveData}"
    const val combineTupleFlow =
        "com.github.Zhuinden:flow-combinetuple-kt:${Versions.combineTupleFlow}"
    const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chuckerNoOp = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
    const val zxing = "com.journeyapps:zxing-android-embedded:${Versions.zxing}"
    const val recyclerViewAnimators =
        "jp.wasabeef:recyclerview-animators:${Versions.recyclerAnimators}"
    const val circularProgressBar =
        "com.mikhaellopez:circularprogressbar:${Versions.circularProgressbar}"
    const val viewBinding =
        "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBinding}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
    const val accompanistSwipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
}