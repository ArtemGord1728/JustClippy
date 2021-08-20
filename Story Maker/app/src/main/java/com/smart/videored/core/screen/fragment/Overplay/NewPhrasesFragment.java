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

public class NewPhrasesFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        arrayList.add(new Sample(R.drawable.phrases01));
        arrayList.add(new Sample(R.drawable.phrases02));
        arrayList.add(new Sample(R.drawable.phrases03));
        arrayList.add(new Sample(R.drawable.phrases04));
        arrayList.add(new Sample(R.drawable.phrases05));
        arrayList.add(new Sample(R.drawable.phrases06));
        arrayList.add(new Sample(R.drawable.phrases07));
        arrayList.add(new Sample(R.drawable.phrases08));
        arrayList.add(new Sample(R.drawable.phrases09));
        arrayList.add(new Sample(R.drawable.phrases10));
        arrayList.add(new Sample(R.drawable.phrases11));
        arrayList.add(new Sample(R.drawable.phrases12));
        arrayList.add(new Sample(R.drawable.phrases13));
        arrayList.add(new Sample(R.drawable.phrases14));
        arrayList.add(new Sample(R.drawable.phrases15));
        arrayList.add(new Sample(R.drawable.phrases16));
        arrayList.add(new Sample(R.drawable.phrases17));
        arrayList.add(new Sample(R.drawable.phrases18));
        arrayList.add(new Sample(R.drawable.phrases20));
        arrayList.add(new Sample(R.drawable.phrases21));
        arrayList.add(new Sample(R.drawable.phrases22));
        arrayList.add(new Sample(R.drawable.phrases23));
        arrayList.add(new Sample(R.drawable.phrases24));
        arrayList.add(new Sample(R.drawable.phrases25));
        arrayList.add(new Sample(R.drawable.phrases26));
        arrayList.add(new Sample(R.drawable.phrases27));
        arrayList.add(new Sample(R.drawable.phrases28));
        arrayList.add(new Sample(R.drawable.phrases29));
        arrayList.add(new Sample(R.drawable.phrases30));
        arrayList.add(new Sample(R.drawable.phrases31));
        arrayList.add(new Sample(R.drawable.phrases32));
        arrayList.add(new Sample(R.drawable.phrases33));
        arrayList.add(new Sample(R.drawable.phrases34));
        arrayList.add(new Sample(R.drawable.phrases35));
        arrayList.add(new Sample(R.drawable.phrases36));
        arrayList.add(new Sample(R.drawable.phrases37));
        arrayList.add(new Sample(R.drawable.phrases38));
        arrayList.add(new Sample(R.drawable.phrases39));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        OverlaysListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onOverlaysClick(i);
        }
    }
}
