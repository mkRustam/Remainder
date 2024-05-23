package com.projects.remainder.ui.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import javax.inject.Inject

open class Router @Inject constructor() {
    lateinit var controller: NavController

    fun injectController(controller: NavController) {
        this.controller = controller
    }

    fun navigate(@IdRes action: Int) {
        controller.navigate(action)
    }

    fun navigate(@IdRes action: Int, args: Bundle) {
        controller.navigate(action, args)
    }

    fun back() {
        controller.popBackStack()
    }

    fun backTo(@IdRes destination: Int) {
        controller.popBackStack(destination, false)
    }

    fun resetNavigation() {
        while(controller.popBackStack()){}
    }
}