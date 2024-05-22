package com.projects.remainder.utils.activity_result_contracts

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import com.projects.remainder.utils.base.ManagerPower
import javax.inject.Inject

/**
 * 1st Boolean (Input) - Package name (violates Google Play rules)
 * 2st Boolean (Output) - result of intent: true - app was added to the whitelist
 * */
@RequiresApi(Build.VERSION_CODES.M)
class ContractBatteryOptimizationIgnore @Inject constructor(
    private var powerManager: ManagerPower,
    private var packageManager: PackageManager
): ActivityResultContract<String, Boolean>() {

    private var aggressiveMode: Boolean = false
    private var packageName: String = ""

    override fun createIntent(context: Context, input: String): Intent {
        this.packageName = input
        return if (aggressiveMode) {
            Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Uri.parse("package:$packageName"))
        } else {
            Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        return powerManager.isIgnoringBatteryOptimizations(packageName)
    }

    override fun getSynchronousResult(
        context: Context,
        input: String
    ): SynchronousResult<Boolean>? {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                createIntent(context, input).resolveActivity(packageManager) == null -> SynchronousResult(true)
                powerManager.isIgnoringBatteryOptimizations(packageName) -> SynchronousResult(true)
                else -> null
            }
        } else SynchronousResult(true)
    }
}