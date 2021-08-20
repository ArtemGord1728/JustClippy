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

public class NewLoveFragment extends Fragment implements StickerAdapter.StickerAdaperListener {
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
        arrayList.add(new Sample(R.drawable.love01));
        arrayList.add(new Sample(R.drawable.love02));
        arrayList.add(new Sample(R.drawable.love03));
        arrayList.add(new Sample(R.drawable.love04));
        arrayList.add(new Sample(R.drawable.love05));
        arrayList.add(new Sample(R.drawable.love06));
        arrayList.add(new Sample(R.drawable.love07));
        arrayList.add(new Sample(R.drawable.love08));
        arrayList.add(new Sample(R.drawable.love09));
        arrayList.add(new Sample(R.drawable.love10));
        arrayList.add(new Sample(R.drawable.love11));
        arrayList.add(new Sample(R.drawable.love12));
        arrayList.add(new Sample(R.drawable.love13));
        arrayList.add(new Sample(R.drawable.love14));
        arrayList.add(new Sample(R.drawable.love15));
        arrayList.add(new Sample(R.drawable.love16));
        arrayList.add(new Sample(R.drawable.love17));
        arrayList.add(new Sample(R.drawable.love18));
        arrayList.add(new Sample(R.drawable.love19));
        arrayList.add(new Sample(R.drawable.love20));
        arrayList.add(new Sample(R.drawable.love21));
        arrayList.add(new Sample(R.drawable.love22));
        arrayList.add(new Sample(R.drawable.love23));
        arrayList.add(new Sample(R.drawable.love24));
        arrayList.add(new Sample(R.drawable.love25));
        arrayList.add(new Sample(R.drawable.love26));
        arrayList.add(new Sample(R.drawable.love27));
        arrayList.add(new Sample(R.drawable.love28));
        arrayList.add(new Sample(R.drawable.love29));
        arrayList.add(new Sample(R.drawable.love30));
        arrayList.add(new Sample(R.drawable.love31));
        arrayList.add(new Sample(R.drawable.love32));
        arrayList.add(new Sample(R.drawable.love33));
        arrayList.add(new Sample(R.drawable.love34));
        arrayList.add(new Sample(R.drawable.love35));
        arrayList.add(new Sample(R.drawable.love36));
        arrayList.add(new Sample(R.drawable.love37));
        arrayList.add(new Sample(R.drawable.love38));
        arrayList.add(new Sample(R.drawable.love39));
        arrayList.add(new Sample(R.drawable.love40));
        arrayList.add(new Sample(R.drawable.love41));
        arrayList.add(new Sample(R.drawable.love42));
        arrayList.add(new Sample(R.drawable.love43));
        arrayList.add(new Sample(R.drawable.love44));
        arrayList.add(new Sample(R.drawable.love45));
        arrayList.add(new Sample(R.drawable.love46));
        arrayList.add(new Sample(R.drawable.love47));
        arrayList.add(new Sample(R.drawable.love48));
        arrayList.add(new Sample(R.drawable.love49));
        arrayList.add(new Sample(R.drawable.love50));
        arrayList.add(new Sample(R.drawable.love51));
        arrayList.add(new Sample(R.drawable.love52));
        return arrayList;
    }

    public void onStickerSelected(int i) {
        OverlaysListener stickerListener = this.listener;
        if (stickerListener != null) {
            stickerListener.onOverlaysClick(i);
        }
    }
}
