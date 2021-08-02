package com.harshad.cameraapp.di

import android.Manifest
import android.content.Context
import com.harshad.cameraapp.R
import java.io.File


object Constant {
    const val FILE_NAME_FORMAT = "yy-MM-dd-HH-mm-ss-SSS"
    const val REQUEST_CODE_PERMISSIONS = 1122
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    const val FLAG_TIMEOUT = 500L
    const val KEY_EVENT_ACTION = "key_event_action"
    const val KEY_EVENT_EXTRA = "key_event_extra"

    /** Use external media if it is available, our app's file directory otherwise */
    fun getOutputDirectory(context: Context): File {
        val appContext = context.applicationContext
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, appContext.resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else appContext.filesDir
    }
}