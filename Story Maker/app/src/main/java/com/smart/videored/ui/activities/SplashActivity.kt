package com.smart.videored.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.ads.control.AdmobHelp
import com.smart.videored.R

class SplashActivity : BaseSplitActivity()
{
    private val SPLASH_DISPLAY_LENGTH = 3700

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.act_splash)
        AdmobHelp.getInstance().init(this)
        Handler().postDelayed({
            startToMainActivity()
            AdmobHelp.getInstance().showInterstitialAd(this@SplashActivity, {})
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    fun startToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    public override fun onDestroy() {
        super.onDestroy()
    }
}