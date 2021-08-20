package com.smart.videored.ui.fragment.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.smart.videored.R;
import com.smart.videored.adaper.FilterAdapter;
import com.smart.videored.model.FilterItem;
import com.smart.videored.model.FilterType;
import java.util.ArrayList;

public class FilterFragment extends Fragment {
    FilterAdapter adapter;
    FilterFragmentListener mfilterFragmentListener;
    RecyclerView recyclerFilter;

    public interface FilterFragmentListener {
        void onFilter(FilterItem filterItem);
    }

    public void setFilterFragmentListener(FilterFragmentListener filterFragmentListener) {
        this.mfilterFragmentListener = filterFragmentListener;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.frm_movie_filter, viewGroup, false);
        recyclerFilter = (RecyclerView) inflate.findViewById(R.id.recyclerFilter);
        setRecyclerFilter();
        return inflate;
    }

    private void setRecyclerFilter() {
        recyclerFilter.setHasFixedSize(true);
        recyclerFilter.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        ArrayList<FilterItem> arrayList = new ArrayList();
        arrayList.add(new FilterItem(R.drawable.filter, "Normal", FilterType.NONE));
        arrayList.add(new FilterItem(R.drawable.filter_l5, "Dream", FilterType.LUT5));
        arrayList.add(new FilterItem(R.drawable.filter_snow, "Snow", FilterType.SNOW));
        arrayList.add(new FilterItem(R.drawable.cameo, "Art", FilterType.CAMEO));
        arrayList.add(new FilterItem(R.drawable.filter_l3, "Brilliant", FilterType.LUT3));


        arrayList.add(new FilterItem(R.drawable.filter_l1, "Light", FilterType.LUT1));
        arrayList.add(new FilterItem(R.drawable.filter_gray, "Black/White", FilterType.GRAY));
        arrayList.add(new FilterItem(R.drawable.filter_l4, "Smooth", FilterType.LUT4));
        arrayList.add(new FilterItem(R.drawable.filter_l2, "Mystery", FilterType.LUT2));




        FilterAdapter filterAdapter = new FilterAdapter(arrayList, getActivity(), new FilterAdapter.FilterAdapterListener() {
            public void onFilterSelected(FilterItem filterItem) {
                if (FilterFragment.this.mfilterFragmentListener != null) {
                    FilterFragment.this.mfilterFragmentListener.onFilter(filterItem);
                } else {
                    Toast.makeText(FilterFragment.this.getActivity(), FilterFragment.this.getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                }
            }
        });
        this.adapter = filterAdapter;
        this.recyclerFilter.setAdapter(filterAdapter);
    }
}
