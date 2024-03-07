package com.example.proyectocei.animacion

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.proyectocei.R
import com.example.proyectocei.login.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                startActivity(
                    Intent(
                        this@SplashActivity,
                        MainActivity::class.java
                    )
                )
                finish()
            }, 2500
        )
    }
}