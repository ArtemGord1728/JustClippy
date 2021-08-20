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

public class NewWinterFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        arrayList.add(new Sample(R.drawable.quote01));
        arrayList.add(new Sample(R.drawable.quote02));
        arrayList.add(new Sample(R.drawable.quote03));
        arrayList.add(new Sample(R.drawable.quote04));
        arrayList.add(new Sample(R.drawable.quote05));
        arrayList.add(new Sample(R.drawable.quote06));
        arrayList.add(new Sample(R.drawable.quote07));
        arrayList.add(new Sample(R.drawable.quote08));
        arrayList.add(new Sample(R.drawable.quote09));
        arrayList.add(new Sample(R.drawable.quote10));
        arrayList.add(new Sample(R.drawable.quote11));
        arrayList.add(new Sample(R.drawable.quote12));
        arrayList.add(new Sample(R.drawable.quote13));
        arrayList.add(new Sample(R.drawable.quote14));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        OverlaysListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onOverlaysClick(i);
        }
    }
}
