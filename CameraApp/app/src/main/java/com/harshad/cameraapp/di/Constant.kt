package com.harshad.cameraapp.di

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.harshad.cameraapp.R
import java.io.File


object Constant {
    const val FILE_NAME_FORMAT = "yy-MM-dd-HH-mm-ss-SSS"
    const val REQUEST_CODE_PERMISSIONS = 1122
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    const val FLAG_TIMEOUT = 500L
    const val KEY_EVENT_ACTION = "key_event_action"
    const val KEY_EVENT_EXTRA = "key_event_extra"

}