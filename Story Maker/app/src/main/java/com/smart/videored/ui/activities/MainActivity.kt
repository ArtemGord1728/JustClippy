package com.smart.videored.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ads.control.AdmobHelp
import com.ads.control.Rate
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.smart.videored.R
import com.smart.videored.ui.dialog.SettingDialog

class MainActivity : BaseSplitActivity() {
    var doubleBackToExitPressedOnce = false
    private var mLastClickTime: Long = 0
    private lateinit var adView: AdView

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.act_main)
        Thread.setDefaultUncaughtExceptionHandler { thread, th -> Log.e("EXCEPTION_IN_THREAD", thread.name + " : " + th.message) }
        addControls()
        AdmobHelp.getInstance().loadBanner(this)
        loadBannerAd()
    }

    private fun addControls() {
        findViewById<View>(R.id.bt_new_project).setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime >= 1000) {
                mLastClickTime = SystemClock.elapsedRealtime()
                Dexter.withContext(this).withPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                           this@MainActivity.startActivity(Intent(this@MainActivity, SelectedPhotoActivity::class.java))
                        }
                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied) {
                            SettingDialog.showSettingDialog(this@MainActivity)
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(list: List<PermissionRequest>, permissionToken: PermissionToken) {
                        permissionToken.continuePermissionRequest()
                    }
                }).withErrorListener { Toast.makeText(this@MainActivity.applicationContext, "Error occurred! ", Toast.LENGTH_SHORT).show() }.onSameThread().check()
            }
        }
        findViewById<View>(R.id.bt_rate).setOnClickListener {
            Rate.Show(this) ;
        }
        findViewById<View>(R.id.bt_share).setOnClickListener {
            try {
                val intent = Intent("android.intent.action.SEND")
                intent.type = "text/plain"
                intent.putExtra("android.intent.extra.SUBJECT", "My application name")
                intent.putExtra("android.intent.extra.TEXT", """
     
     Let me recommend you this application
     
     "https://play.google.com/store/apps/details?id=$packageName
     """.trimIndent())
                startActivity(Intent.createChooser(intent, "Choose one"))
            } catch (unused: Exception) {
                Log.e("log",unused.message?:"")
            }

        }
        findViewById<View>(R.id.bt_policy).setOnClickListener {
            startActivity(Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.link_policy))))
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        Runtime.getRuntime().gc()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            showGoodBye()
        } else {
            doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

    fun showGoodBye() {
        Rate.Show(this);
    }

    private fun loadBannerAd() {
        adView = findViewById(R.id.rectangle_banner_adview)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
            }

            override fun onAdOpened() {
                super.onAdOpened()
            }

            override fun onAdClosed() {
                super.onAdClosed()
            }

            override fun onAdClicked() {
                super.onAdClicked()
            }
        }
    }
}