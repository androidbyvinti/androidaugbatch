package com.bmpl.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

class ImageAdapter extends BaseAdapter {

    Context context;

    LayoutInflater layoutInflater;

    int images[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    ImageAdapter(Context context)
    {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = layoutInflater.inflate(R.layout.custom_view, viewGroup, false);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(images[i]);

        return view;

        /*ImageView imageView = new ImageView(context);

        imageView.setImageResource(images[i]);

        return imageView;*/
    }
}
