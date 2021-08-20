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

public class BirthdayFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    ArrayList<Sample> chistmasArrays;
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
        this.chistmasArrays = ChistmasList();
        this.recyclerView.setAdapter(new StickerAdapter(this.chistmasArrays, getActivity(), this));
        return inflate;
    }

    public ArrayList<Sample> ChistmasList() {
        ArrayList<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.birth01));
        arrayList.add(new Sample(R.drawable.birth02));
        arrayList.add(new Sample(R.drawable.birth03));
        arrayList.add(new Sample(R.drawable.birth04));
        arrayList.add(new Sample(R.drawable.birth05));
        arrayList.add(new Sample(R.drawable.birth06));
        arrayList.add(new Sample(R.drawable.birth07));
        arrayList.add(new Sample(R.drawable.birth08));
        arrayList.add(new Sample(R.drawable.birth09));
        arrayList.add(new Sample(R.drawable.birth10));
        arrayList.add(new Sample(R.drawable.birth11));
        arrayList.add(new Sample(R.drawable.birth12));
        arrayList.add(new Sample(R.drawable.birth13));
        arrayList.add(new Sample(R.drawable.birth14));
        arrayList.add(new Sample(R.drawable.birth15));
        arrayList.add(new Sample(R.drawable.birth16));
        arrayList.add(new Sample(R.drawable.birth17));
        arrayList.add(new Sample(R.drawable.birth18));
        arrayList.add(new Sample(R.drawable.birth19));
        arrayList.add(new Sample(R.drawable.birth20));
        arrayList.add(new Sample(R.drawable.birth21));
        arrayList.add(new Sample(R.drawable.birth22));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        StickerListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
