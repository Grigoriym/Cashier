package com.grappim.cashier.core.platform

import android.app.Activity
import android.app.Application
import android.os.Bundle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FocusedActivityHolder @Inject constructor() {

    @Volatile
    private var currentActivity: Activity? = null

    @Volatile
    private var activityLifeCycle: ActivityLifeCycle? = null

    private val lifecycleCallbacks: Application.ActivityLifecycleCallbacks by lazy {
        object : Application.ActivityLifecycleCallbacks {

            override fun onActivityResumed(activity: Activity) {
                currentActivity = activity
                activityLifeCycle = ActivityLifeCycle.RESUME
            }

            override fun onActivityPaused(activity: Activity) {
                if (currentActivity == activity) {
                    activityLifeCycle = ActivityLifeCycle.PAUSE
                }
            }

            override fun onActivityStarted(activity: Activity) {
                activityLifeCycle = ActivityLifeCycle.START
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (currentActivity == activity) {
                    activityLifeCycle = ActivityLifeCycle.DESTROY
                }
            }

            override fun onActivityStopped(activity: Activity) {
                if (currentActivity == activity) {
                    activityLifeCycle = ActivityLifeCycle.STOP
                }
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activityLifeCycle = ActivityLifeCycle.CREATE
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

        }
    }

    fun getCurrentActivity(): Activity? = currentActivity

    fun startListen(application: Application) {
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
    }

    fun isActivityAvailable(): Boolean {
        return activityLifeCycle == ActivityLifeCycle.START ||
            activityLifeCycle == ActivityLifeCycle.RESUME
    }

    private enum class ActivityLifeCycle {
        CREATE,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY
    }
}