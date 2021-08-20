package com.smart.videored.ui.fragment.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.smart.videored.R;

public class RatioFragment extends Fragment implements View.OnClickListener {
    private RatioFragmentListener listener;
    private ImageView btn11;
    private ImageView btn45;
    private ImageView btn169;
    private ImageView btn916;
    private ImageView btn34;
    private ImageView btn43;


    public interface RatioFragmentListener {
        void onRatio(int i);
    }

    public void RatioFragmentListener(RatioFragmentListener ratioFragmentListener) {
        this.listener = ratioFragmentListener;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.frm_movie_ratio, viewGroup, false);
        inflate.findViewById(R.id.btn_square).setOnClickListener(this);
        inflate.findViewById(R.id.btn34).setOnClickListener(this);
        inflate.findViewById(R.id.btn43).setOnClickListener(this);
        inflate.findViewById(R.id.btn169).setOnClickListener(this);
        inflate.findViewById(R.id.btn916).setOnClickListener(this);
        btn11 = inflate.findViewById(R.id.btn_square);
        btn34 = inflate.findViewById(R.id.btn34);
        btn43 = inflate.findViewById(R.id.btn43);
        btn169 = inflate.findViewById(R.id.btn169);
        btn916 = inflate.findViewById(R.id.btn916);
        btn45 = inflate.findViewById(R.id.btn_45);
        btn45.setOnClickListener(this);

        return inflate;
    }

    public void onClick(View view) {
        if (this.listener != null) {
            int id = view.getId();
            if (id != R.id.btn_square) {
                switch (id) {
                    case R.id.btn169:
                        listener.onRatio(169);
                        btn11.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn34.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn43.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn169.setColorFilter(ContextCompat.getColor(getContext(),R.color.blue));
                        btn916.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn45.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));

                        return;
                    case R.id.btn_45:
                        listener.onRatio(45);
                        btn11.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn34.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn43.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn169.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn916.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn45.setColorFilter(ContextCompat.getColor(getContext(),R.color.blue));
                        return;
                    case R.id.btn34:
                        listener.onRatio(34);
                        btn11.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn34.setColorFilter(ContextCompat.getColor(getContext(),R.color.blue));
                        btn43.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn169.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn916.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn45.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        return;
                    case R.id.btn43:
                        listener.onRatio(43);
                        btn11.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn34.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn43.setColorFilter(ContextCompat.getColor(getContext(),R.color.blue));
                        btn169.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn916.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn45.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        return;
                    case R.id.btn916:
                        listener.onRatio(916);
                        btn11.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn34.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn43.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn169.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        btn916.setColorFilter(ContextCompat.getColor(getContext(),R.color.blue));
                        btn45.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                        return;
                    default:
                        return;
                }
            } else {
                listener.onRatio(11);
                btn11.setColorFilter(ContextCompat.getColor(getContext(),R.color.blue));
                btn34.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                btn43.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                btn169.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                btn916.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
                btn45.setColorFilter(ContextCompat.getColor(getContext(),R.color.black));
            }
        } else {
            Toast.makeText(getActivity(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
        }
    }

}
