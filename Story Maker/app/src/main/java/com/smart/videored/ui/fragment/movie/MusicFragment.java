package com.smart.videored.ui.fragment.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.videored.R;
import com.smart.videored.adaper.MusicAdapter;
import com.smart.videored.model.MusicType;

import java.util.ArrayList;

public class MusicFragment extends Fragment {

    public MusicAdapter adapter;
    private CardView btnMyMusic;

    public ImageView btnMyMusicCheck;

    public MusicFragmentListener listener;
    private RecyclerView recyclerMusic;

    public interface MusicFragmentListener {
        void onMyMusic();

        void onTypeMusic(int i);
    }

    public void setMusicFragmentListener(MusicFragmentListener musicFragmentListener) {
        this.listener = musicFragmentListener;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.frm_movie_music, viewGroup, false);
        btnMyMusic = (CardView) inflate.findViewById(R.id.btnMyMusic);
        btnMyMusicCheck = (ImageView) inflate.findViewById(R.id.imgUnChecked);
        recyclerMusic = (RecyclerView) inflate.findViewById(R.id.recyclerMusic);
        setRecyclerMusic();
        this.btnMyMusic.setOnClickListener(view -> {
            if (MusicFragment.this.listener != null) {
                MusicFragment.this.listener.onMyMusic();
                MusicFragment.this.adapter.setRowSelected(-1);
                MusicFragment.this.adapter.notifyDataSetChanged();
                MusicFragment.this.btnMyMusicCheck.setImageResource(R.drawable.ic_music_check_shape);
                return;
            }
            Toast.makeText(MusicFragment.this.getActivity(), MusicFragment.this.getString(R.string.try_again), Toast.LENGTH_SHORT).show();
        });
        return inflate;
    }

    private void setRecyclerMusic() {
        recyclerMusic.setHasFixedSize(true);
        recyclerMusic.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        ArrayList<MusicType> arrayList = new ArrayList<>();
        arrayList.add(new MusicType(R.drawable.sad, "SAD"));
        arrayList.add(new MusicType(R.drawable.romantic, "ROMANTIC"));
        arrayList.add(new MusicType(R.drawable.funny, "FUNNY"));
        arrayList.add(new MusicType(R.drawable.summer, "SUMMER"));
        arrayList.add(new MusicType(R.drawable.movie, "MOVIE"));
        arrayList.add(new MusicType(R.drawable.happy, "HAPPY"));
        arrayList.add(new MusicType(R.drawable.christmas, "CHRISTMAS"));
        arrayList.add(new MusicType(R.drawable.travel, "TRAVEL"));
        arrayList.add(new MusicType(R.drawable.beach, "BEACH"));
        arrayList.add(new MusicType(R.drawable.friend, "FRIEND"));
        arrayList.add(new MusicType(R.drawable.love, "LOVE"));
        MusicAdapter musicAdapter = new MusicAdapter(arrayList, getActivity(), new MusicAdapter.MusicAdapterListener() {
            public void onMusicSelected(int i) {
                if (MusicFragment.this.listener != null) {
                    MusicFragment.this.listener.onTypeMusic(i);
                    MusicFragment.this.btnMyMusicCheck.setImageResource(0);
                    return;
                }
                Toast.makeText(MusicFragment.this.getActivity(), MusicFragment.this.getString(R.string.try_again), 0).show();
            }
        });
        this.adapter = musicAdapter;
        this.recyclerMusic.setAdapter(musicAdapter);
    }
}
