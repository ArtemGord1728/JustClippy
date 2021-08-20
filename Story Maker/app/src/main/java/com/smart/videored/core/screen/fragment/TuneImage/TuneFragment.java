package com.smart.videored.core.screen.fragment.TuneImage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.smart.videored.R;
import com.smart.videored.view.DynamicSeekBarView;
import com.google.android.material.tabs.TabLayout;

public class TuneFragment extends Fragment {
    TuneFragmentListener listener;
    TabLayout tabLayoutTune;
    ViewPager viewPagerTune;
    DynamicSeekBarView sbBrightness;
    DynamicSeekBarView sbContrast;
    DynamicSeekBarView sbHue;
    DynamicSeekBarView sbSaturation;

    TextView tvBrightness;
    TextView tvContrast;
    TextView tvHue;
    TextView tvSaturation;

    public interface TuneFragmentListener {
        void onBrightnessChosse(int i);

        void onConstrastChosse(int i);

        void onHueChosee(int i);

        void onSaturationChosse(int i);
    }

    public void setTuneFragmentListener(TuneFragmentListener tuneFragmentListener) {
        this.listener = tuneFragmentListener;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.frm_image_tune, viewGroup, false);
     /*   this.tabLayoutTune = inflate.findViewById(R.id.tablayoutTune);
        ViewPager findViewById = inflate.findViewById(R.id.viewpagerTune);*/
        this.sbBrightness = inflate.findViewById(R.id.sb_brightness);
        this.sbContrast = inflate.findViewById(R.id.sb_contrast);
        this.sbHue = inflate.findViewById(R.id.sb_hue);
        this.sbSaturation = inflate.findViewById(R.id.sb_saturation);

        this.tvBrightness = inflate.findViewById(R.id.tv_brightness);
        this.tvContrast = inflate.findViewById(R.id.tv_contrast);
        this.tvHue = inflate.findViewById(R.id.tv_hue);
        this.tvSaturation = inflate.findViewById(R.id.tv_saturation);

//        sbBrightness.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
//
//        sbBrightness.setProgressTintList(ColorStateList.valueOf(Color.RED));
        //  this.viewPagerTune = findViewById;
      /*  findViewById.setAdapter(new TuneViewPagerAdapter(getChildFragmentManager(), getActivity(), new TuneImageFragmentListener() {
            public void onBrightnessChanged(int i) {
                TuneFragment.this.listener.onBrightnessChosse(i - 50);
            }

            public void onSaturationChanged(int i) {
                TuneFragment.this.listener.onSaturationChosse(i);
            }

            public void onConstrantChanged(int i) {
                TuneFragment.this.listener.onConstrastChosse(i);
            }

            public void onHueChanged(int i) {
                TuneFragment.this.listener.onHueChosee(i);
            }
        }));*/
        //  this.viewPagerTune.setOffscreenPageLimit(4);
        //    this.tabLayoutTune.setupWithViewPager(this.viewPagerTune);

        //
        this.sbBrightness.setSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvBrightness.setText(String.valueOf(progress - 50));
                if (listener != null) {
                    listener.onBrightnessChosse(progress - 50);
                }
                sbBrightness.setInfoPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                sbBrightness.showInfo();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sbBrightness.hideInfo();
            }
        });


        this.sbContrast.setSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvContrast.setText(String.valueOf(progress - 50));
                if (listener != null) {
                    listener.onBrightnessChosse(progress);
                }
                sbContrast.setInfoPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                sbContrast.showInfo();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sbContrast.hideInfo();
            }
        });

        this.sbHue.setSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvHue.setText(String.valueOf(progress - 50));
                if (listener != null) {
                    listener.onBrightnessChosse(progress);
                }
                sbHue.setInfoPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                sbHue.showInfo();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sbHue.hideInfo();
            }
        });

        this.sbSaturation.setSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSaturation.setText(String.valueOf(progress - 50));
                if (listener != null) {
                    listener.onBrightnessChosse(progress);
                }
                sbSaturation.setInfoPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                sbSaturation.showInfo();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sbSaturation.hideInfo();
            }
        });
        return inflate;
    }


}


