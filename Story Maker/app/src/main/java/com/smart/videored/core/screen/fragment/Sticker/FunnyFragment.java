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

public class FunnyFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        arrayList.add(new Sample(R.drawable.fun01));
        arrayList.add(new Sample(R.drawable.fun02));
        arrayList.add(new Sample(R.drawable.fun03));
        arrayList.add(new Sample(R.drawable.fun04));
        arrayList.add(new Sample(R.drawable.fun05));
        arrayList.add(new Sample(R.drawable.fun06));
        arrayList.add(new Sample(R.drawable.fun07));
        arrayList.add(new Sample(R.drawable.fun08));
        arrayList.add(new Sample(R.drawable.fun09));
        arrayList.add(new Sample(R.drawable.fun10));
        arrayList.add(new Sample(R.drawable.fun11));
        arrayList.add(new Sample(R.drawable.fun12));
        arrayList.add(new Sample(R.drawable.fun13));
        arrayList.add(new Sample(R.drawable.fun14));
        arrayList.add(new Sample(R.drawable.fun15));
        arrayList.add(new Sample(R.drawable.fun16));
        arrayList.add(new Sample(R.drawable.fun17));
        arrayList.add(new Sample(R.drawable.fun18));
        arrayList.add(new Sample(R.drawable.fun19));
        arrayList.add(new Sample(R.drawable.fun20));
        arrayList.add(new Sample(R.drawable.fun21));
        arrayList.add(new Sample(R.drawable.fun22));
        arrayList.add(new Sample(R.drawable.fun23));
        arrayList.add(new Sample(R.drawable.fun24));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        StickerListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
