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

public class ChistmasFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        arrayList.add(new Sample(R.drawable.christ01));
        arrayList.add(new Sample(R.drawable.christ02));
        arrayList.add(new Sample(R.drawable.christ03));
        arrayList.add(new Sample(R.drawable.christ04));
        arrayList.add(new Sample(R.drawable.christ05));
        arrayList.add(new Sample(R.drawable.christ06));
        arrayList.add(new Sample(R.drawable.christ07));
        arrayList.add(new Sample(R.drawable.christ08));
        arrayList.add(new Sample(R.drawable.christ09));
        arrayList.add(new Sample(R.drawable.christ10));
        arrayList.add(new Sample(R.drawable.christ11));
        arrayList.add(new Sample(R.drawable.christ12));
        arrayList.add(new Sample(R.drawable.christ13));
        arrayList.add(new Sample(R.drawable.christ14));
        arrayList.add(new Sample(R.drawable.christ15));
        arrayList.add(new Sample(R.drawable.christ16));
        arrayList.add(new Sample(R.drawable.christ17));
        arrayList.add(new Sample(R.drawable.christ18));
        arrayList.add(new Sample(R.drawable.christ19));
        arrayList.add(new Sample(R.drawable.christ20));
        arrayList.add(new Sample(R.drawable.christ21));
        arrayList.add(new Sample(R.drawable.christ22));
        arrayList.add(new Sample(R.drawable.christ23));
        arrayList.add(new Sample(R.drawable.christ24));
        arrayList.add(new Sample(R.drawable.christ25));
        arrayList.add(new Sample(R.drawable.christ26));
        arrayList.add(new Sample(R.drawable.christ27));
        arrayList.add(new Sample(R.drawable.christ28));
        arrayList.add(new Sample(R.drawable.christ29));
        arrayList.add(new Sample(R.drawable.christ30));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        StickerListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
