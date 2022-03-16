# A Demo Cashier App using compose, partially (for now) backed up by [my own backend](https://github.com/Grigoriym/cashier-api)

## This project is under active development and refactoring. And this is a sandbox for me as well. This is why some parts of code may look as overengineered, bad, strange, etc.

### "Wtf is this app about?" - you'll ask.
Imagine your local groceries where sellers at the cash register scan your products and eventually ask you to pay.
Well, this app actually does this. You can choose products, you can sell them, you can scan a product by a barcode.
There is also such a feature as Waybill, this is needed for sellers when some new products are shipped to this grocery and a seller has to add them to the database of products and to their grocery.
I do this because I had an experience with an app of such functionality.

### Project structure
[Here you can see the app flow](https://drive.google.com/file/d/1pnAnRmdSb6lBLid0wilV6QIFNJxLDUOW/view?usp=sharing)
![](https://github.com/Grigoriym/Cashier/blob/master/art/app_flow.png)

### Refactoring History
1. Hilt -> Dagger
2. Gson -> Kotlin Serializable
3. Android Navigation Component -> Cicerone

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
- [Scabbard](https://arunkumar9t2.github.io/scabbard/) - A tool to visualize and understand your Dagger 2 dependency graph.

### Roadmap
- [x] Move all UI to compose, (only scanner is left without any changes)
- [ ] Google Sign In, Firebase Authentication
- [ ] Add tests and all that stuff
- [ ] Make full integration with [my own backend solution](https://github.com/Grigoriym/cashier-api)
- [ ] Learn proguard and somehow do something with it here
- [x] Enhance navigation, abstract navigation (kinda made it with the help of Cicerone)
- [ ] Some modules are kinda useless or a bit messy: core, extensions, app
- [ ] Add CI and all that cool automated features
- [ ] I don't know the right way to integrate communication between composeScreen and viewModel, especially when there are many data to send
- [ ] Make usable caching of data
- [ ] Is SingleLiveData a good choice? But I have no other options with compose, at least for now.
- [ ] Compose has problems with the soft keyboard.
- [ ] I have not a general ui kit, And I need a designer
- [ ] [Structural and navigation anti-patterns in multi-module and modularized applications](https://proandroiddev.com/structural-and-navigation-anti-patterns-in-modularized-android-applications-a7d667e35cd6)
- [x] In perspective, Dagger 2 is better for multi module app.
- [x] Using Dagger2 try to remove all inject lateinit
- [ ] Refactor repositories, they are all app-scoped
- [ ] Feature flags
- [ ] Guest Mode
- [ ] Show sth if there are no products/categories etc.
- [ ] Settings screen
- [ ] android.nonTransitiveRClass=true
- [ ] some gradle build enhancement, like in aag 2, also there dagger2 tips on scope lifecycle and their holders
- [ ] what is api and impl modules and how do they work
- [ ] abstract image loader (coil)
- [ ] refactor all stuff connected to workManager
- [ ] add rx as an alternative
- [ ] make it more convenient RequestWithAuthToken
- [ ] make workers' logic on some timestamp or sth like that
- [ ] Debug panel

### Thanks to
1. [Dmitry Akishin - Logging in a multi-module Android project](https://proandroiddev.com/logging-in-a-multi-module-android-project-7294382e59fa) - for implementing logging in android modules and pure koltin/java modules
2. [Dimitar Dihanov](https://itnext.io/android-multimodule-navigation-with-the-navigation-component-99f265de24) Android Multimodule Navigation with the Navigation Component
3. Vladimir Tagakov for his videos on Dagger2: [this one](https://www.youtube.com/watch?v=pMEAD6jjbaI), a video No 8 from Podlodka Android Crew#1 (you can buy it [here](https://podlodka.io/crew-records))
4. [Android Broadcast - Dagger2 course](https://www.youtube.com/watch?v=G5P_vDL1ZLg&list=PL0SwNXKJbuNkYFUda5rlA-odAVyWItRCP)
5. Android Academy Global

### Some duct tapes
1. rememberLazyListState() in some compose functions where we have LazyList() and Scaffold's bottomBar. It is done so that list's bottom has been constrained to the top of Scaffold's bottomBar.

Login | Select stock
--- | --- |  
![](https://github.com/Grigoriym/Cashier/blob/master/art/auth.jpg) | ![](https://github.com/Grigoriym/Cashier/blob/master/art/select_stock.jpg)

Edit Product | Sales
--- | --- |  
![](https://github.com/Grigoriym/Cashier/blob/master/art/edit_product.jpg) | ![](https://github.com/Grigoriym/Cashier/blob/master/art/sales.jpg)
