package com.example.dailyvideocalling

import android.Manifest
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts

object PermissionUtil {

    fun checkCallingPermissions(baseActivity: MainActivity, permissionGranted: () -> Unit, permissionDenied: () -> Unit) {
        if (Build.VERSION.SDK_INT > 22) {
            val activityResultLauncher =
                baseActivity.registerForActivityResult(
                    ActivityResultContracts.RequestMultiplePermissions()
                ) { permissions ->
                    permissions.entries.forEach {
                        val permissionName = it.key
                        val isGranted = it.value
                        if (isGranted) {
                            permissionGranted()
                        } else {
                            permissionDenied()
                        }
                    }
                }
            activityResultLauncher.launch(
                arrayOf(Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
            )
        } else {
            permissionGranted()
        }
    }

}