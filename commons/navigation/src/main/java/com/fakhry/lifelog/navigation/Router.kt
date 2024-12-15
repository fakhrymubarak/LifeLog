package com.fakhry.lifelog.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri

object Router {
    private const val APP_SCHEME = "lifelog"

    const val HOST_MAIN = "main"
    private const val HOST_ABOUT = "about"
    private const val HOST_SPLASH = "splash"
    private const val HOST_ONBOARDING = "onboarding"
    private const val HOST_READ = "read"
    private const val HOST_EDIT = "edit"

    /**
     * Navigate to the "About" activity.
     */
    fun navigateToAbout(context: Context) {
        navigateToDeepLink(context, HOST_ABOUT)
    }

    /**
     * Navigate to the "Splash" activity.
     */
    fun navigateToSplash(context: Context) {
        navigateToDeepLink(context, HOST_SPLASH)
    }

    /**
     * Navigate to the "Onboarding" activity.
     */
    fun navigateToOnboarding(context: Context) {
        navigateToDeepLink(context, HOST_ONBOARDING)
    }

    /**
     * Navigate to the "Main" activity.
     */
    fun navigateToMain(context: Context) {
        navigateToDeepLink(context, HOST_MAIN)
    }

    /**
     * Navigate to the "Read" activity.
     */
    fun navigateToRead(context: Context) {
        navigateToDeepLink(context, HOST_READ)
    }

    /**
     * Navigate to the "Edit" activity.
     */
    fun navigateToEdit(context: Context) {
        navigateToDeepLink(context, HOST_EDIT)
    }

    fun getIntent(host: String): Intent {
        val uri = Uri.Builder()
            .scheme(APP_SCHEME)
            .authority(host)
            .build()
        return Intent(Intent.ACTION_VIEW, uri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }

    /**
     * Generic method to navigate to a deep link.
     * @param context The context used to start the activity.
     * @param host The host of the deep link to navigate to.
     */
    private fun navigateToDeepLink(context: Context, host: String) {
        val uri = Uri.Builder()
            .scheme(APP_SCHEME)
            .authority(host)
            .build()
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}