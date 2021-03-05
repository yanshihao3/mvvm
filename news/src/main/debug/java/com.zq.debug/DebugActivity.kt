package com.zq.debug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zq.news.R

class DebugActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)
        Toast.makeText(this, "hello CC", Toast.LENGTH_SHORT).show()
        //需要单独安装运行，但不需要入口页面（只需要从主app中调用此组件）时，
        // 可直接finish当前activity
        finish()
    }
}