package com.example.demo_view_app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btGestureKeyGuard.setOnClickListener {
            startActivity(Intent(this, GestureKeyGuardActivty::class.java))
        }
        btItemDecoration.setOnClickListener {
            startActivity(Intent(this, ItemDecorationActivity::class.java))
        }
    }

}
