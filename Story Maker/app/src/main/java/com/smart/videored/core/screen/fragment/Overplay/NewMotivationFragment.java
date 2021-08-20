package com.smart.videored.core.screen.fragment.Overplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.videored.core.screen.fragment.Sticker.StickerAdapter;
import com.smart.videored.R;
import com.smart.videored.listener.OverlaysListener;
import com.smart.videored.model.Sample;

import java.util.ArrayList;

public class NewMotivationFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    ArrayList<Sample> chistmasArrays;
    OverlaysListener listener;
    RecyclerView recyclerView;

    public void setOverlaysListener(OverlaysListener stickerListener) {
        this.listener = stickerListener;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.frm_sticker_recyclerview, viewGroup, false);
        RecyclerView findViewById = inflate.findViewById(R.id.recyclerSticker);
        this.recyclerView = findViewById;
        findViewById.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        this.chistmasArrays = ChistmasList();
        this.recyclerView.setAdapter(new StickerAdapter(this.chistmasArrays, getActivity(), this));
        return inflate;
    }

    public ArrayList<Sample> ChistmasList() {
        ArrayList<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.mov01));
        arrayList.add(new Sample(R.drawable.mov02));
        arrayList.add(new Sample(R.drawable.mov03));
        arrayList.add(new Sample(R.drawable.mov04));
        arrayList.add(new Sample(R.drawable.mov05));
        arrayList.add(new Sample(R.drawable.mov06));
        arrayList.add(new Sample(R.drawable.mov07));
        arrayList.add(new Sample(R.drawable.mov08));
        arrayList.add(new Sample(R.drawable.mov09));
        arrayList.add(new Sample(R.drawable.mov10));
        arrayList.add(new Sample(R.drawable.mov11));
        arrayList.add(new Sample(R.drawable.mov12));
        arrayList.add(new Sample(R.drawable.mov13));
        arrayList.add(new Sample(R.drawable.mov14));
        arrayList.add(new Sample(R.drawable.mov15));
        arrayList.add(new Sample(R.drawable.mov16));
        arrayList.add(new Sample(R.drawable.mov17));
        arrayList.add(new Sample(R.drawable.mov18));
        arrayList.add(new Sample(R.drawable.mov19));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        OverlaysListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onOverlaysClick(i);
        }
    }
}
