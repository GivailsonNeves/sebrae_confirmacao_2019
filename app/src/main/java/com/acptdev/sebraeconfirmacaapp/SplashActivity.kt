package com.acptdev.sebraeconfirmacaapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Timer().schedule(4000) {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
    }


}
