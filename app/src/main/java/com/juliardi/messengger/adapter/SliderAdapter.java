package com.juliardi.messengger.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.juliardi.messengger.R;
import com.juliardi.messengger.model.SliderItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderVH> {
    private Context context;
    private List<SliderItem> sliderItemList = new ArrayList<>();

    public SliderAdapter(Context context, List<SliderItem> sliderItemList) {
        this.context = context;
        this.sliderItemList = sliderItemList;
    }

    public void renewItems(List<SliderItem> sliderItems) {
        this.sliderItemList = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.sliderItemList.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SliderItem sliderItem) {
        this.sliderItemList.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider, null);
        return new SliderVH(view);
    }

    @Override
    public void onBindViewHolder(SliderVH viewHolder, int position) {
        SliderItem sliderItem = sliderItemList.get(position);

        viewHolder.tV_Description.setText(sliderItem.getDesc());
        viewHolder.tV_Description.setTextSize(16);
        viewHolder.tV_Description.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImageUrl())
                .fitCenter()
                .into(viewHolder.iv_Background);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getCount() {
        return sliderItemList.size();
    }

    public class SliderVH extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView iv_Background;
        ImageView iv_Container;
        TextView tV_Description;
        public SliderVH(View itemView) {
            super(itemView);
            iv_Background = itemView.findViewById(R.id.iv_auto_image_slider);
            iv_Container = itemView.findViewById(R.id.iv_gif_container);
            tV_Description =itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView =itemView;
        }
    }
}
