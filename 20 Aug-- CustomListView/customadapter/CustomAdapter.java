package com.bmpl.customadapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{

    private Context context;
    private int image[];
    private String value[];
    LayoutInflater layoutInflater;

    CustomAdapter(Context context, int image[], String value[])
    {
        this.context = context;
        this.image = image;
        this.value = value;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return image.length;// total length of data
    }

    @Override
    public Object getItem(int i) {
        return image[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        view = layoutInflater.inflate(R.layout.custom_layout, viewGroup, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView)view.findViewById(R.id.textView);

        imageView.setImageResource(image[position]);
        textView.setText(value[position]);

        return view;
    }
}
