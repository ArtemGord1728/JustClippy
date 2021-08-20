package com.smart.videored.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ads.control.AdmobHelp;
import com.ads.control.Rate;
import com.smart.videored.R;
import com.smart.videored.utils.Config;
import com.smart.videored.model.MySharedPreferences;

public class FinalActivity extends BaseSplitActivity implements View.OnClickListener {
    private static final String TAG = FinalActivity.class.getSimpleName();
    private int countAd = 1;
    private int countRate = 3;
    private Boolean firstTime = null;
    Uri uri = null;
    private ImageView videoThumbnail;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.act_final);
        addControls();
        addAds();
        getDataShowVideo();
    }

    private boolean isFirstTime() {
        if (this.firstTime == null) {
            Boolean valueOf = Boolean.valueOf(MySharedPreferences.getInstance(this).getBoolean("firstTime", true));
            this.firstTime = valueOf;
            if (valueOf.booleanValue()) {
                MySharedPreferences.getInstance(this).putBoolean("firstTime", false);
            }
        }
        return this.firstTime.booleanValue();
    }

    private void getDataShowVideo() {
        Uri uri2 = (Uri) getIntent().getParcelableExtra("VIDEO");
        this.uri = uri2;
        this.videoThumbnail.setImageBitmap(ThumbnailUtils.createVideoThumbnail(uri2.getPath(), 1));
    }

    private void addAds() {
        AdmobHelp.getInstance().loadNative(this);
    }

    private void addControls() {
        ImageView imageView = (ImageView) findViewById(R.id.videoView_thumbnail);
        this.videoThumbnail = imageView;
        imageView.setOnClickListener(this);
        findViewById(R.id.btnShareMore).setOnClickListener(this);
        findViewById(R.id.btnInstagram).setOnClickListener(this);
        findViewById(R.id.btnFacebook).setOnClickListener(this);
        findViewById(R.id.btnMessenger).setOnClickListener(this);
        findViewById(R.id.btnYoutube).setOnClickListener(this);
        findViewById(R.id.btnGmail).setOnClickListener(this);
        findViewById(R.id.btnWhatsApp).setOnClickListener(this);
        findViewById(R.id.btnTwitter).setOnClickListener(this);
        findViewById(R.id.btnRate).setOnClickListener(this);
        findViewById(R.id.btn_new).setOnClickListener(this);
        findViewById(R.id.btnBackFinal).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBackFinal:
                super.onBackPressed();
                return;
            case R.id.btnFacebook:
                shareVideo(getString(R.string.app_name), this.uri.getPath(), Config.FACE);
                return;
            case R.id.btnGmail:
                shareVideo(getString(R.string.app_name), this.uri.getPath(), Config.GMAIL);
                return;
            case R.id.btnInstagram:
                shareVideo(getString(R.string.app_name), this.uri.getPath(), Config.INSTA);
                return;
            case R.id.btnMessenger:
                shareVideo(getString(R.string.app_name), this.uri.getPath(), Config.MESSEGER);
                return;
            case R.id.btnRate:
                Rate.Show(this,() -> {

                });
                return;
            case R.id.btnShareMore:
                shareMore(getString(R.string.app_name), this.uri.getPath());
                return;
            case R.id.btnTwitter:
                shareVideo(getString(R.string.app_name), this.uri.getPath(), Config.TWITTER);
                return;
            case R.id.btnWhatsApp:
                shareVideo(getString(R.string.app_name), this.uri.getPath(), Config.WHATSAPP);
                return;
            case R.id.btnYoutube:
                shareVideo(getString(R.string.app_name), this.uri.getPath(), Config.YOUTU);
                return;
            case R.id.btn_new:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return;
            case R.id.videoView_thumbnail:
                Intent intent2 = new Intent(this, PlayVideoActivity.class);
                intent2.putExtra("VIDEO", this.uri);
                startActivity(intent2);
                return;
            default:
                return;
        }
    }

    public void shareMore(final String str, String str2) {
        MediaScannerConnection.scanFile(this, new String[]{str2}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
            public void onScanCompleted(String str, Uri uri) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("video/*");
                intent.putExtra("android.intent.extra.SUBJECT", str);
                intent.putExtra("android.intent.extra.TITLE", str);
                intent.putExtra("android.intent.extra.STREAM", uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                FinalActivity finalActivity = FinalActivity.this;
                finalActivity.startActivity(Intent.createChooser(intent, finalActivity.getString(R.string.share_this)));
            }
        });
    }

    public void shareVideo(final String str, String str2, final String str3) {
        if (isPackageInstalled(this, str3)) {
            MediaScannerConnection.scanFile(this, new String[]{str2}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String str, Uri uri) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("video/*");
                    intent.putExtra("android.intent.extra.SUBJECT", str);
                    intent.putExtra("android.intent.extra.TITLE", str);
                    intent.putExtra("android.intent.extra.STREAM", uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                    intent.setPackage(str3);
                    FinalActivity.this.startActivity(intent);
                }
            });
            return;
        }
        Toast.makeText(this, getString(R.string.app_not_install), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + str3));
        startActivity(intent);
    }

    @SuppressLint("WrongConstant")
    public static boolean isPackageInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
