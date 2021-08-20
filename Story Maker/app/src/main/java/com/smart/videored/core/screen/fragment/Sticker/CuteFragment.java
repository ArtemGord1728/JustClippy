package com.smart.videored.core.screen.fragment.Sticker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.videored.R;
import com.smart.videored.listener.StickerListener;
import com.smart.videored.model.Sample;

import java.util.ArrayList;

public class CuteFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    ArrayList<Sample> lightArray;
    StickerListener listener;
    RecyclerView recyclerView;

    public void setStickerListener(StickerListener stickerListener) {
        this.listener = stickerListener;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.frm_sticker_recyclerview, viewGroup, false);
        RecyclerView findViewById = inflate.findViewById(R.id.recyclerSticker);
        this.recyclerView = findViewById;
        findViewById.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        this.lightArray = Light();
        this.recyclerView.setAdapter(new StickerAdapter(this.lightArray, getActivity(), this));
        return inflate;
    }

    public ArrayList<Sample> Light() {
        ArrayList<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.cute01));
        arrayList.add(new Sample(R.drawable.cute02));
        arrayList.add(new Sample(R.drawable.cute03));
        arrayList.add(new Sample(R.drawable.cute04));
        arrayList.add(new Sample(R.drawable.cute05));
        arrayList.add(new Sample(R.drawable.cute06));
        arrayList.add(new Sample(R.drawable.cute07));
        arrayList.add(new Sample(R.drawable.cute08));
        arrayList.add(new Sample(R.drawable.cute09));
        arrayList.add(new Sample(R.drawable.cute10));
        arrayList.add(new Sample(R.drawable.cute11));
        arrayList.add(new Sample(R.drawable.cute12));
        arrayList.add(new Sample(R.drawable.cute13));
        arrayList.add(new Sample(R.drawable.cute14));
        arrayList.add(new Sample(R.drawable.cute15));
        arrayList.add(new Sample(R.drawable.cute16));
        arrayList.add(new Sample(R.drawable.cute17));
        arrayList.add(new Sample(R.drawable.cute18));
        arrayList.add(new Sample(R.drawable.cute19));
        arrayList.add(new Sample(R.drawable.cute20));
        arrayList.add(new Sample(R.drawable.cute21));
        arrayList.add(new Sample(R.drawable.cute22));
        arrayList.add(new Sample(R.drawable.cute23));
        arrayList.add(new Sample(R.drawable.cute24));
        arrayList.add(new Sample(R.drawable.cute25));
        arrayList.add(new Sample(R.drawable.cute26));
        arrayList.add(new Sample(R.drawable.cute27));
        arrayList.add(new Sample(R.drawable.cute28));
        arrayList.add(new Sample(R.drawable.cute29));
        arrayList.add(new Sample(R.drawable.cute30));
        arrayList.add(new Sample(R.drawable.cute31));
        arrayList.add(new Sample(R.drawable.cute32));
        arrayList.add(new Sample(R.drawable.cute33));
        arrayList.add(new Sample(R.drawable.cute34));
        arrayList.add(new Sample(R.drawable.cute35));
        arrayList.add(new Sample(R.drawable.cute36));
        arrayList.add(new Sample(R.drawable.cute37));
        arrayList.add(new Sample(R.drawable.cute38));
        arrayList.add(new Sample(R.drawable.cute39));
        arrayList.add(new Sample(R.drawable.cute40));
        arrayList.add(new Sample(R.drawable.cute41));
        arrayList.add(new Sample(R.drawable.cute42));
        arrayList.add(new Sample(R.drawable.cute43));
        arrayList.add(new Sample(R.drawable.cute44));
        arrayList.add(new Sample(R.drawable.cute45));
        arrayList.add(new Sample(R.drawable.cute46));
        arrayList.add(new Sample(R.drawable.cute47));
        arrayList.add(new Sample(R.drawable.cute48));
        arrayList.add(new Sample(R.drawable.cute49));
        arrayList.add(new Sample(R.drawable.cute50));
        arrayList.add(new Sample(R.drawable.cute51));
        arrayList.add(new Sample(R.drawable.cute52));
        arrayList.add(new Sample(R.drawable.cute53));
        arrayList.add(new Sample(R.drawable.cute54));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        StickerListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
