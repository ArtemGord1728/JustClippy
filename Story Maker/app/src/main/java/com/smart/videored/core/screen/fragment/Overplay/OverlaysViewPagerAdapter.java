package com.smart.videored.core.screen.fragment.Overplay;

import android.content.Context;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.smart.videored.listener.OverlaysListener;

public class OverlaysViewPagerAdapter extends FragmentPagerAdapter implements OverlaysListener {
    private Fragment[] chilFragment;
    private Context mContext;
    OverlaysViewPagerAdapterListener mlistener;

    @Override
    public void onOverlaysClick(int i) {
        this.mlistener.onOverlays(i);
    }

    public interface OverlaysViewPagerAdapterListener {
        void onOverlays(int i);
    }

    public CharSequence getPageTitle(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 6 ? "" : "Motivation" : "Travel" : "Quotes" : "Summer" : "Christmas" : "Love" : "Phrase";
    }

    public OverlaysViewPagerAdapter(FragmentManager fragmentManager, Context context, OverlaysViewPagerAdapterListener overlaysViewPagerAdapterListener) {
        super(fragmentManager);

        NewPhrasesFragment phrasesFragment = new NewPhrasesFragment();
        phrasesFragment.setOverlaysListener(this);

//        NewFoodFragment foodFragment = new NewFoodFragment();
//        foodFragment.setOverlaysListener(this);

        NewLoveFragment loveFragment = new NewLoveFragment();
        loveFragment.setOverlaysListener(this);

        NewChristmasFragment chistmasFragment = new NewChristmasFragment();
        chistmasFragment.setOverlaysListener(this);

//        NewSayingsFragment sayingsFragment = new NewSayingsFragment();
//        sayingsFragment.setOverlaysListener(this);

        NewSummerFragment summerFragment = new NewSummerFragment();
        summerFragment.setOverlaysListener(this);

        NewWinterFragment winterFragment = new NewWinterFragment();
        winterFragment.setOverlaysListener(this);

        NewTravelFragment travelFragment = new NewTravelFragment();
        travelFragment.setOverlaysListener(this);

        NewMotivationFragment motivationFragment = new NewMotivationFragment();
        motivationFragment.setOverlaysListener(this);

        this.chilFragment = new Fragment[]{phrasesFragment, loveFragment, chistmasFragment, summerFragment, winterFragment, travelFragment, motivationFragment};
        this.mContext = context;
        this.mlistener = overlaysViewPagerAdapterListener;
    }

    public OverlaysViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int i) {
        return this.chilFragment[i];
    }

    public int getCount() {
        return this.chilFragment.length;
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        OverlaysViewPagerAdapter.super.setPrimaryItem(viewGroup, i, obj);
    }

}
