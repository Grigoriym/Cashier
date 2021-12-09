# A Demo Cashier App using compose, partially (for now) backed up by [my own backend](https://github.com/Grigoriym/cashier-api)

## This project is under active development and refactoring. And this is a sandbox for me as well. This is why some parts of code may look as overengineered, bad, strange, etc.

### This project is multi module.

### Build With
- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - is Android’s modern toolkit for building native UI.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for android and Java.
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Hilt](https://dagger.dev/hilt/) - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
- [Navigation Component](https://developer.android.com/guide/navigation) - interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.
- [Pagination](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - helps you load and display pages of data from a larger dataset from local storage or over network.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=Cj0KCQjwqKuKBhCxARIsACf4XuF8OuNAkgHbABABjvBrDdeFkUtP3222N8A6eGgxazM5HVEy2zKdxU0aAjwVEALw_wcB&gclsrc=aw.ds) -  is designed to store and manage UI-related data in a lifecycle conscious way.
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - is an API that makes it easy to schedule reliable, asynchronous tasks that are expected to run even if the app exits or the device restarts.
- [Room](https://developer.android.com/training/data-storage/room) - provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite
- [StateFlow, SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - StateFlow and SharedFlow are Flow APIs that enable flows to optimally emit state updates and emit values to multiple consumers.
- [Chucker](https://github.com/ChuckerTeam/chucker) - A simple in-app HTTP inspector for Android OkHttp clients.
- [Coil](https://github.com/coil-kt/coil) - An image loading library for Android backed by Kotlin Coroutines.
- [Android Debug Database](https://github.com/amitshekhariitbhu/Android-Debug-Database) - A powerful library for debugging databases and shared preferences in Android applications

### Roadmap
- [ ] Move all UI to compose
- [ ] Navigation is done badly
- [ ] Google Sign In, Firebase Authentication
- [ ] Add tests and all that stuff
- [ ] Make full integration with [my own backend solution](https://github.com/Grigoriym/cashier-api)
- [ ] Learn proguard and somehow do something with it here
- [ ] Enhance navigation, abstract navigation
- [ ] Some modules are kinda useless or a bit messy: core, extensions, app
- [ ] Add CI and all that cool automated features
- [ ] I don't know the right way to integrate communication between composeScreen and viewModel, especially when there are many data to send
- [ ] Make usable caching of data
- [ ] Is SingleLiveData a good choice? But I have no other options with compose, at least for now.
- [ ] Compose has problems with the soft keyboard.
- [ ] I have not a general ui kit, And I need a designer
- [ ] [Structural and navigation anti-patterns in multi-module and modularized applications](https://proandroiddev.com/structural-and-navigation-anti-patterns-in-modularized-android-applications-a7d667e35cd6)
- [ ] In perspective, Dagger 2 is better for multi module app.
- [ ] Local Repos need to be custom scoped
- [ ] Feature flags
- [ ] Guest Mode

### Thanks to
1. [Dmitry Akishin - Logging in a multi-module Android project](https://proandroiddev.com/logging-in-a-multi-module-android-project-7294382e59fa) - for implementing logging in android modules and pure koltin/java modules
2. [Dimitar Dihanov](https://itnext.io/android-multimodule-navigation-with-the-navigation-component-99f265de24) Android Multimodule Navigation with the Navigation Component

### Some duct tapes
1. rememberLazyListState() in some compose functions where we have LazyList() and Scaffold's bottomBar. It is done so that list's bottom has been constrained to the top of Scaffold's bottomBar.

Login | Select stock
--- | --- |  
![](https://github.com/Grigoriym/Cashier/blob/master/art/auth.jpg) | ![](https://github.com/Grigoriym/Cashier/blob/master/art/select_stock.jpg)

Edit Product | Sales
--- | --- |  
![](https://github.com/Grigoriym/Cashier/blob/master/art/edit_product.jpg) | ![](https://github.com/Grigoriym/Cashier/blob/master/art/sales.jpg)