package com.grappim.navigation

interface CommonScreenNavigator {

    /**
     * This method is used for navComponent popBackStack
     */
    fun goBack()

    /**
     * This method is used for activity.onBackPressed().
     * This is useful if we want to handle backPresses in fragment, using
     * onBackPressedDispatcher
     */
    fun activityOnBackPressed()

}