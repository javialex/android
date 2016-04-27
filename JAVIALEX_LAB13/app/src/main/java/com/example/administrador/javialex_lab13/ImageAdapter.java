package com.example.administrador.javialex_lab13;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Administrador on 07/04/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    public Integer[] vImages = {R.drawable.imagen1, R.drawable.imagen2, R.drawable.imagen3};

    /*,R.drawable.imagen4, R.drawable.imagen5, R.drawable.imagen6, R.drawable.imagen7,
            R.drawable.imagen8, R.drawable.imagen9, R.drawable.imagen10};
*/
    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return vImages.length;
    }

    @Override
    public Object getItem(int position) {
        return vImages[position];
    }

    @Override
    public long getItemId(int position) {
        return vImages[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(vImages[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, 200));
        return imageView;
    }
}
