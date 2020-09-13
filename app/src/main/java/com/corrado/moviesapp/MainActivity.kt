package com.corrado.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.corrado.moviesapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}