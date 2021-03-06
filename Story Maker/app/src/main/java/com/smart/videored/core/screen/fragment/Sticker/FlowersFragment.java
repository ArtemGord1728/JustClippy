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

public class FlowersFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    ArrayList<Sample> flowersList;
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
        this.flowersList = FlowerList();
        this.recyclerView.setAdapter(new StickerAdapter(this.flowersList, getActivity(), this));
        return inflate;
    }

    private ArrayList<Sample> FlowerList() {
        ArrayList<Sample> arrayList = new ArrayList<>();
//        arrayList.add(new Sample(R.drawable.default_sticker));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        this.listener.onStickerClick(i);
    }
}
