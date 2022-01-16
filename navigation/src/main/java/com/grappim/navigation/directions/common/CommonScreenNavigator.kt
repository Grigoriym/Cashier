package com.grappim.navigation.directions.common

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
    fun onBackPressed()

}