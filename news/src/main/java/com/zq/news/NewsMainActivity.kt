package com.zq.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.billy.cc.core.component.CC

class NewsMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_activity_main)
        findViewById<TextView>(R.id.text).setOnClickListener {
            val result = CC.obtainBuilder("ComponentB")
                .setActionName("showActivity")
                .build()
                .call()
        }
    }
}