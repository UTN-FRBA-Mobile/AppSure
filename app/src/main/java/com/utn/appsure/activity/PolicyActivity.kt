package com.utn.appsure.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utn.appsure.R

class PolicyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_policy)
        title = "Crear póliza"
    }
}