# A Demo Cashier App using compose, partially (for now) backed up by [my own backend](https://github.com/Grigoriym/cashier-api)

## This project is under active development and refactoring. And this is a sandbox for me as well. This is why some parts of code may look as overengineered, bad, strange, etc.

### There are different modules in this app:
- app
- core
- domain
- navigation
- uikit
- data
  - db
  - network
  - repository
  - workers
- utils
  - date-time
  - logger
  - calculations
  - extensions
- features
  - auth
  - waybill
  - bag
  - menu
  - payment-method
  - products
  - sales
  - scanner
  - select-cashbox
  - select-stock
  - sign-up

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
- [ ] Add tests
- [ ] Make full integration with [my own backend solution](https://github.com/Grigoriym/cashier-api)
- [ ] Learn proguard and somehow do something with it here
- [ ] Enhance navigation

Login | Select stock
--- | --- |  
![](https://github.com/Grigoriym/Cashier/blob/master/art/auth.jpg) | ![](https://github.com/Grigoriym/Cashier/blob/master/art/select_stock.jpg)

Edit Product | Sales
--- | --- |  
![](https://github.com/Grigoriym/Cashier/blob/master/art/edit_product.jpg) | ![](https://github.com/Grigoriym/Cashier/blob/master/art/sales.jpg)

### Thanks to
1. [Dmitry Akishin - Logging in a multi-module Android project](https://proandroiddev.com/logging-in-a-multi-module-android-project-7294382e59fa) - for implementing logging in android modules and pure koltin/java modules
2. [Dimitar Dihanov](https://itnext.io/android-multimodule-navigation-with-the-navigation-component-99f265de24) Android Multimodule Navigation with the Navigation Component