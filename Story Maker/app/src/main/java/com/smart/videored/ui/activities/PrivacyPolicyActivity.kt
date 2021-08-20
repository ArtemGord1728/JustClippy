package com.smart.videored.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.smart.videored.R
import com.smart.videored.model.MySharedPreferences

class PrivacyPolicyActivity : AppCompatActivity()
{
    private lateinit var checkBoxPolicy : CheckBox



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        checkBoxPolicy = findViewById(R.id.check_policy)
        checkBoxPolicy.isChecked = true
        checkBoxPolicy.isEnabled = false

        if(MySharedPreferences.getInstance(this).getBoolean("start_app", false)) {
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.btn_access_policy).setOnClickListener {
            MySharedPreferences.getInstance(this).putBoolean("start_app", true)
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        }
    }
}