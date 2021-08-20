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

public class NewTravelFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        arrayList.add(new Sample(R.drawable.travel01));
        arrayList.add(new Sample(R.drawable.travel02));
        arrayList.add(new Sample(R.drawable.travel03));
        arrayList.add(new Sample(R.drawable.travel04));
        arrayList.add(new Sample(R.drawable.travel05));
        arrayList.add(new Sample(R.drawable.travel06));
        arrayList.add(new Sample(R.drawable.travel07));
        arrayList.add(new Sample(R.drawable.travel08));
        arrayList.add(new Sample(R.drawable.travel09));
        arrayList.add(new Sample(R.drawable.travel10));
        arrayList.add(new Sample(R.drawable.travel11));
        arrayList.add(new Sample(R.drawable.travel12));
        arrayList.add(new Sample(R.drawable.travel13));
        arrayList.add(new Sample(R.drawable.travel14));
        arrayList.add(new Sample(R.drawable.travel15));
        arrayList.add(new Sample(R.drawable.travel16));
        arrayList.add(new Sample(R.drawable.travel17));
        arrayList.add(new Sample(R.drawable.travel18));
        arrayList.add(new Sample(R.drawable.travel19));
        arrayList.add(new Sample(R.drawable.travel20));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        OverlaysListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onOverlaysClick(i);
        }
    }
}
