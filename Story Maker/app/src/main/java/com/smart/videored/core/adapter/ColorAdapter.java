package com.smart.videored.core.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.smart.videored.R;
import com.smart.videored.view.NewCircleView;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
    List<Integer> colorList;
    Context context;
    ColorAdapterListener listener;
    int row_selected = -1;

    public interface ColorAdapterListener {
        void onColorItemSelected(int i);
    }

    public ColorAdapter(Context context2, ColorAdapterListener colorAdapterListener) {
        this.context = context2;
        this.colorList = genColorList();
        this.listener = colorAdapterListener;
    }

    public ColorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        return new ColorViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_color, viewGroup, false),i);
    }

    public void onBindViewHolder(ColorViewHolder colorViewHolder, int i) {
        if (i == 0)  {
            colorViewHolder.color_section.setBorderColor(this.context.getResources().getColor(R.color.black));
            colorViewHolder.color_section.setBorderWidth(1);
           // colorViewHolder.vSelected.setVisibility(View.VISIBLE);
        }else {
            colorViewHolder.color_section.setBorderColor(this.context.getResources().getColor(R.color.cl_trans));
            //colorViewHolder.vSelected.setVisibility(View.INVISIBLE);
        }

        if (row_selected == i) {
            colorViewHolder.vSelected.setVisibility(View.VISIBLE);
        } else {
            colorViewHolder.vSelected.setVisibility(View.INVISIBLE);
        }


        colorViewHolder.color_section.setCircleColor(this.colorList.get(i).intValue());
    }

    public int getItemCount() {
        return this.colorList.size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder {
        NewCircleView color_section;
        View vSelected;
        public ColorViewHolder(View view,int position) {
            super(view);
            this.color_section =  view.findViewById(R.id.color_section);
            this.vSelected =  view.findViewById(R.id.v_selected);

            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ColorViewHolder.this.getAdapterPosition() < ColorAdapter.this.colorList.size()) {
                        ColorAdapter.this.listener.onColorItemSelected(ColorAdapter.this.colorList.get(ColorViewHolder.this.getAdapterPosition()));
                        ColorAdapter.this.row_selected = ColorViewHolder.this.getAdapterPosition();
                        ColorAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    private List<Integer> genColorList() {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(Color.parseColor("#FFFFFF")));
        arrayList.add(Integer.valueOf(Color.parseColor("#000000")));
        arrayList.add(Color.parseColor("#0098F1"));
        arrayList.add(Integer.valueOf(Color.parseColor("#4CC259")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FFC859")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FF8523")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FF3A4A")));
        arrayList.add(Integer.valueOf(Color.parseColor("#E90060")));
        arrayList.add(Integer.valueOf(Color.parseColor("#B300B6")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FF0000")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FF7E88")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FFD0D1")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FFDAB2")));
        arrayList.add(Integer.valueOf(Color.parseColor("#FFC07E")));
        arrayList.add(Integer.valueOf(Color.parseColor("#E18B42")));
        arrayList.add(Integer.valueOf(Color.parseColor("#a36138")));
        arrayList.add(Integer.valueOf(Color.parseColor("#4A2829")));
        arrayList.add(Integer.valueOf(Color.parseColor("#004C30")));
        arrayList.add(Integer.valueOf(Color.parseColor("#2C2C2C")));
        arrayList.add(Integer.valueOf(Color.parseColor("#393939")));
        arrayList.add(Integer.valueOf(Color.parseColor("#555555")));
        arrayList.add(Integer.valueOf(Color.parseColor("#727272")));
        arrayList.add(Integer.valueOf(Color.parseColor("#989898")));
        arrayList.add(Integer.valueOf(Color.parseColor("#B1B1B1")));
        arrayList.add(Integer.valueOf(Color.parseColor("#C7C7C7")));
        arrayList.add(Integer.valueOf(Color.parseColor("#DBDBDB")));
        arrayList.add(Integer.valueOf(Color.parseColor("#F0F0F0")));
        return arrayList;
    }
}
