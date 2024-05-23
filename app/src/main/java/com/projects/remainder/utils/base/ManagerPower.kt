package com.projects.remainder.utils.base

import android.os.PowerManager
import javax.inject.Inject

class ManagerPower @Inject constructor(
    private var powerManager: PowerManager
) {

    fun isIgnoringBatteryOptimizations(packageName: String): Boolean {
        return powerManager.isIgnoringBatteryOptimizations(packageName)
    }
}