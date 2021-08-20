package com.smart.videored.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.smart.videored.R;

public class DynamicSeekBarView extends LinearLayout {
    private CustomSeekBar seekBar;
    private View arrow;
    private TextView tvInfo;
    private LinearLayout llInfo;
    private String seekBarTextInfo = "";

    public DynamicSeekBarView(Context context) {
        super(context);
        init(context, null);
    }

    public DynamicSeekBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DynamicSeekBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dynamic_seek_bar_view, this, false);
        seekBar = view.findViewById(R.id.seekBar);
        arrow = view.findViewById(R.id.arrow);
        tvInfo = view.findViewById(R.id.tvInfo);
        llInfo = view.findViewById(R.id.llInfo);
        tvInfo.setText("" + seekBar.getProgress());


        llInfo.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setInfoPosition(50);
            }
        }, 500);
        tvInfo.setText("0");
        this.addView(view);
    }

    public void showInfo(){
        llInfo.setVisibility(VISIBLE);
    }

    public void hideInfo(){
        llInfo.setVisibility(INVISIBLE);
    }

    public void setProgress(int value) {
        seekBar.setProgress(value);
    }


    public void setMax(int max) {
        seekBar.setMax(max);
    }

    private int getColorValue(int resId) {
        return ResourcesCompat.getColor(getResources(), resId, null);
    }

    public void setInfoPosition(int progress) {
        int value = getStepValue(progress);
        seekBar.setProgress(value);
        arrow.setX(getSeekBarThumbPosX(seekBar) - arrow.getWidth() / 2);
        tvInfo.setX(getTimePosition(seekBar, tvInfo));
        tvInfo.setText("" + (progress - 50));
    }

    private int getTimePosition(SeekBar seekBar, View viewInfo) {
        int thumbPos = getSeekBarThumbPosX(seekBar);
        int seekBarWidth = seekBar.getWidth();
        if (thumbPos + viewInfo.getWidth() / 2 >= seekBarWidth) {
            return seekBarWidth - viewInfo.getWidth();
        } else if (thumbPos - viewInfo.getWidth() / 2 <= seekBar.getPaddingLeft()) {
            return (int) seekBar.getX();
        } else {
            return thumbPos - viewInfo.getWidth() / 2;
        }
    }

    private int getStepValue(int progress) {
        int stepSize = 1;
        return (Math.round(progress / stepSize)) * stepSize;
    }

    private int getSeekBarThumbPosX(SeekBar seekBar) {
        int width = seekBar.getWidth() - seekBar.getPaddingLeft() - seekBar.getPaddingRight();
        int thumbPosX = seekBar.getPaddingLeft() + width * seekBar.getProgress() / seekBar.getMax();
        return thumbPosX;
    }

    public void setSeekBarChangeListener(SeekBar.OnSeekBarChangeListener seekBarChangeListener) {
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    public void setInfoText(String text, int progress) {
        tvInfo.setText(text);
        setInfoPosition(progress);
    }
}
