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

public class LoveFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
    ArrayList<Sample> heartsList;
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
        this.heartsList = HeartList();
        this.recyclerView.setAdapter(new StickerAdapter(this.heartsList, getActivity(), this));
        return inflate;
    }

    private ArrayList<Sample> HeartList() {
        ArrayList<Sample> arrayList = new ArrayList<>();
        arrayList.add(new Sample(R.drawable.sicker_love01));
        arrayList.add(new Sample(R.drawable.sicker_love02));
        arrayList.add(new Sample(R.drawable.sicker_love03));
        arrayList.add(new Sample(R.drawable.sicker_love04));
        arrayList.add(new Sample(R.drawable.sicker_love05));
        arrayList.add(new Sample(R.drawable.sicker_love06));
        arrayList.add(new Sample(R.drawable.sicker_love07));
        arrayList.add(new Sample(R.drawable.sicker_love08));
        arrayList.add(new Sample(R.drawable.sicker_love09));
        arrayList.add(new Sample(R.drawable.sicker_love10));
        arrayList.add(new Sample(R.drawable.sicker_love11));
        arrayList.add(new Sample(R.drawable.sicker_love12));
        arrayList.add(new Sample(R.drawable.sicker_love13));
        arrayList.add(new Sample(R.drawable.sicker_love14));
        arrayList.add(new Sample(R.drawable.sicker_love15));
        arrayList.add(new Sample(R.drawable.sicker_love16));
        arrayList.add(new Sample(R.drawable.sicker_love17));
        arrayList.add(new Sample(R.drawable.sicker_love18));
        arrayList.add(new Sample(R.drawable.sicker_love19));
        arrayList.add(new Sample(R.drawable.sicker_love20));
        arrayList.add(new Sample(R.drawable.sicker_love21));
        arrayList.add(new Sample(R.drawable.sicker_love22));
        arrayList.add(new Sample(R.drawable.sicker_love23));
        arrayList.add(new Sample(R.drawable.sicker_love24));
        arrayList.add(new Sample(R.drawable.sicker_love25));
        arrayList.add(new Sample(R.drawable.sicker_love26));
        arrayList.add(new Sample(R.drawable.sicker_love27));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        StickerListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onStickerClick(i);
        }
    }
}
