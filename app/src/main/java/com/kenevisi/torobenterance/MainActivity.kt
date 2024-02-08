package com.kenevisi.torobenterance

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kenevisi.core.exceptions.AppLanguages
import com.kenevisi.core.exceptions.setAppLocale
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase.setAppLocale(AppLanguages.FARSI))
    }
}