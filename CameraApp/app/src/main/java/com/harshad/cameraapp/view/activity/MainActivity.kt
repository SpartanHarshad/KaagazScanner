package com.harshad.cameraapp.view.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.camera.core.*
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.harshad.cameraapp.R
import com.harshad.cameraapp.databinding.ActivityMainBinding
import com.harshad.cameraapp.di.Constant.FLAG_TIMEOUT
import com.harshad.cameraapp.di.Constant.KEY_EVENT_ACTION
import com.harshad.cameraapp.di.Constant.KEY_EVENT_EXTRA
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    /**
     *  initialize view binder
     */
    private fun initViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Before setting full screen flags, we must wait a bit to let UI settle; otherwise, we may
     * be trying to set app to immersive mode before it's ready and the flags do not stick
     */
    override fun onResume() {
        super.onResume()
        binding.flContainer.postDelayed({
            hideUISystem()
        }, FLAG_TIMEOUT)
    }

    private fun hideUISystem() {
        /**
         * WindowCompat : Helper for accessing features in Window.
         * setDecorFitsSystemWindows : Sets whether the decor view should fit root-level content views for
         * WindowInsetsCompat.
         * If set to code false, the framework will not fit the content view to the insets and will
         * just pass through the WindowInsetsCompat to the content view.
         * @param window                 The current window.
         * @param decorFitsSystemWindows Whether the decor view should fit root-level content views for
         *                               insets.
         * */
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.flContainer).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    /**
     * androidx.activity.ComponentActivity Called when the activity has detected the user's press
     * of the back key. The OnBackPressedDispatcher will be given a chance to handle the back button
     * before the default behavior of Activity.onBackPressed() is invoked.
     * Overrides:onBackPressed in class ComponentActivity
     * */
    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q){
            /**
             * Reverses the Activity Scene entry Transition and triggers the calling Activity
             * to reverse its exit Transition. When the exit Transition completes,
             * finish() is called. If no entry Transition was used, finish() is called
             * immediately and the Activity exit Transition is run.
             * */
            finishAfterTransition()
        }else{
            super.onBackPressed()
        }
    }

    /** When key down event is triggered, relay it via local broadcast so fragments can handle it */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when(keyCode){
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                val intent = Intent(KEY_EVENT_ACTION).apply { putExtra(KEY_EVENT_EXTRA, keyCode) }
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}