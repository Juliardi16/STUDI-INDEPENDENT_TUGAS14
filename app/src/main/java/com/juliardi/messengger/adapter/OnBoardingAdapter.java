package com.juliardi.messengger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.juliardi.messengger.R;

public class OnBoardingAdapter extends PagerAdapter {

    Context context;

    public OnBoardingAdapter(Context context) {
        this.context = context;
    }
    int images []={
                   R.drawable.gambar_kapal_1,
                   R.drawable.gambar_kapal_2,
                   R.drawable.gambar_kapal_3
                  };

    int headings []={
                      R.string.title_kapal1,
                      R.string.title_kapal2,
                      R.string.title_kapal3
                    };

    int desc []={
                 R.string.kapal1_desc,
                 R.string.title_kapal2,
                 R.string.kapal3_desc
                };


    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);

        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView detail = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        detail.setText(desc[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
