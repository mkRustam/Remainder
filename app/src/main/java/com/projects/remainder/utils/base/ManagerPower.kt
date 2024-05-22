package com.projects.remainder.utils.base

import android.os.Build
import android.os.PowerManager
import javax.inject.Inject

class ManagerPower @Inject constructor(
    private var powerManager: PowerManager
) {

    fun isIgnoringBatteryOptimizations(packageName: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return powerManager.isIgnoringBatteryOptimizations(packageName)
        }
        return true
    }
}