package com.ingenieriiajhr.gridview;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingenieriiajhr.electric.R;

import java.util.ArrayList;

public class OptAdapter extends BaseAdapter{

    ArrayList<String> optText;
    ArrayList<Integer> optImage;
    Context context;

    LayoutInflater layoutInflater;


    public OptAdapter(Context context,ArrayList<String> optText,ArrayList<Integer> optImage){
        this.context = context;
        this.optText = optText;
        this.optImage = optImage;
    }



    @Override
    public int getCount() {
        return optText.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;

        if (layoutInflater==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null){
            view1 = layoutInflater.inflate(R.layout.view_options,null);
        }

        TextView txtOption = view1.findViewById(R.id.txtOptions);
        ImageView imgOption = view1.findViewById(R.id.imgOption);

        txtOption.setText(optText.get(i));
        imgOption.setBackgroundResource(optImage.get(i));

        return view1;
    }
}
