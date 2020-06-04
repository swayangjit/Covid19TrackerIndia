package com.github.googleassignment.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.github.googleassignment.R
import com.github.googleassignment.ui.main.MainActivity


/**
 * Created by swayangjit on 29/5/20.
 */
class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this,
                MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}