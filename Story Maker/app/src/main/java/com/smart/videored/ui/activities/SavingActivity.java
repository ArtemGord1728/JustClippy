package com.smart.videored.ui.activities;

import android.os.Bundle;

import com.ads.control.AdmobHelp;
import com.smart.videored.R;

public class SavingActivity extends BaseSplitActivity {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.layout_saving);
        AdmobHelp.getInstance().loadNative(this);
    }
}
