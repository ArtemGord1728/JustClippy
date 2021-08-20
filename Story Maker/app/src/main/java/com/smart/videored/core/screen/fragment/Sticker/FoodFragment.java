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

public class FoodFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        arrayList.add(new Sample(R.drawable.food01));
        arrayList.add(new Sample(R.drawable.food02));
        arrayList.add(new Sample(R.drawable.food03));
        arrayList.add(new Sample(R.drawable.food04));
        arrayList.add(new Sample(R.drawable.food05));
        arrayList.add(new Sample(R.drawable.food06));
        arrayList.add(new Sample(R.drawable.food07));
        arrayList.add(new Sample(R.drawable.food08));
        arrayList.add(new Sample(R.drawable.food09));
        arrayList.add(new Sample(R.drawable.food10));
        arrayList.add(new Sample(R.drawable.food11));
        arrayList.add(new Sample(R.drawable.food12));
        arrayList.add(new Sample(R.drawable.food13));
        arrayList.add(new Sample(R.drawable.food14));
        arrayList.add(new Sample(R.drawable.food15));
        arrayList.add(new Sample(R.drawable.food16));
        arrayList.add(new Sample(R.drawable.food17));
        arrayList.add(new Sample(R.drawable.food18));
        arrayList.add(new Sample(R.drawable.food19));
        arrayList.add(new Sample(R.drawable.food20));
        arrayList.add(new Sample(R.drawable.food21));
        arrayList.add(new Sample(R.drawable.food22));
        arrayList.add(new Sample(R.drawable.food23));
        arrayList.add(new Sample(R.drawable.food24));
        arrayList.add(new Sample(R.drawable.food25));
        arrayList.add(new Sample(R.drawable.food26));
        arrayList.add(new Sample(R.drawable.food27));
        arrayList.add(new Sample(R.drawable.food28));
        arrayList.add(new Sample(R.drawable.food29));
        arrayList.add(new Sample(R.drawable.food30));
        arrayList.add(new Sample(R.drawable.food31));
        arrayList.add(new Sample(R.drawable.food32));
        arrayList.add(new Sample(R.drawable.food33));
        arrayList.add(new Sample(R.drawable.food34));
        arrayList.add(new Sample(R.drawable.food35));
        arrayList.add(new Sample(R.drawable.food36));
        arrayList.add(new Sample(R.drawable.food37));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        StickerListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
