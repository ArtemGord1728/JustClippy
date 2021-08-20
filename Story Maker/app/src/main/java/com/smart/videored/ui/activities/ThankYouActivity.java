package com.smart.videored.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.smart.videored.R;

public class ThankYouActivity extends BaseSplitActivity {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.act_goodbye);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.enter_from_bottom);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.enter_from_bottom_delay);
        findViewById(R.id.tv_thankyou_container).startAnimation(loadAnimation);
        findViewById(R.id.tv_thankyou_2).startAnimation(loadAnimation2);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ThankYouActivity.this.finish();
            }
        }, 2000);
    }


    public void onDestroy() {

        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
