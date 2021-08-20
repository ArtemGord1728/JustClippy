package com.smart.videored.core.screen.fragment.Sticker;

import android.content.Context;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.smart.videored.listener.StickerListener;

public class StickerViewPagerAdapter extends FragmentPagerAdapter implements StickerListener {
    private Fragment[] chilFragment;
    private Context mContext;
    StickerViewPagerAdapterListener mlistener;

    public interface StickerViewPagerAdapterListener {
        void onSticker(int i);
    }

    public CharSequence getPageTitle(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 6 ? i != 7 ? i != 8 ? i != 9
                ? "" : "Birthday" : "Christmas" : "Cute" : "Emotion" : "Food" : "Funny" : "Love" : "Summer" : "Travel" : "Women's Day";
    }

    public StickerViewPagerAdapter(FragmentManager fragmentManager, Context context, StickerViewPagerAdapterListener stickerViewPagerAdapterListener) {
        super(fragmentManager);
        BirthdayFragment birthdayFragment = new BirthdayFragment();
        birthdayFragment.setStickerListener(this);

        ChistmasFragment chistmasFragment = new ChistmasFragment();
        chistmasFragment.setStickerListener(this);

        CuteFragment cuteFragment = new CuteFragment();
        cuteFragment.setStickerListener(this);

        EmotionFragment emotionFragment = new EmotionFragment();
        emotionFragment.setStickerListener(this);

        FoodFragment foodFragment = new FoodFragment();
        foodFragment.setStickerListener(this);

        FunnyFragment funnyFragment = new FunnyFragment();
        funnyFragment.setStickerListener(this);

        LoveFragment loveFragment = new LoveFragment();
        loveFragment.setStickerListener(this);

        SummerFragment summerFragment = new SummerFragment();
        summerFragment.setStickerListener(this);

        TranvelFragment tranvelFragment = new TranvelFragment();
        tranvelFragment.setStickerListener(this);

        WomenDayFragment womenDayFragment = new WomenDayFragment();
        womenDayFragment.setStickerListener(this);

        this.chilFragment = new Fragment[]{womenDayFragment, tranvelFragment, summerFragment,
                loveFragment, funnyFragment, foodFragment, emotionFragment, cuteFragment,
                chistmasFragment, birthdayFragment};
        this.mContext = context;
        this.mlistener = stickerViewPagerAdapterListener;
    }

    public StickerViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int i) {
        return this.chilFragment[i];
    }

    public int getCount() {
        return this.chilFragment.length;
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        StickerViewPagerAdapter.super.setPrimaryItem(viewGroup, i, obj);
    }

    public void onStickerClick(int i) {
        this.mlistener.onSticker(i);
    }
}
