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

public class EmotionFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        arrayList.add(new Sample(R.drawable.em01));
        arrayList.add(new Sample(R.drawable.em02));
        arrayList.add(new Sample(R.drawable.em03));
        arrayList.add(new Sample(R.drawable.em04));
        arrayList.add(new Sample(R.drawable.em05));
        arrayList.add(new Sample(R.drawable.em06));
        arrayList.add(new Sample(R.drawable.em07));
        arrayList.add(new Sample(R.drawable.em08));
        arrayList.add(new Sample(R.drawable.em09));
        arrayList.add(new Sample(R.drawable.em10));
        arrayList.add(new Sample(R.drawable.em11));
        arrayList.add(new Sample(R.drawable.em12));
        arrayList.add(new Sample(R.drawable.em13));
        arrayList.add(new Sample(R.drawable.em14));
        arrayList.add(new Sample(R.drawable.em15));
        arrayList.add(new Sample(R.drawable.em16));
        arrayList.add(new Sample(R.drawable.em17));
        arrayList.add(new Sample(R.drawable.em18));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        StickerListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
