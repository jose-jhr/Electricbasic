package com.ingenieriiajhr.animation;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ingenieriiajhr.electric.R;

public class RotateView {

    View view;
    Context context;

    public RotateView(Context context,View view){
        this.view = view;
        this.context = context;
    }

    public void rotateView(){
        Animation animationUtils = AnimationUtils.loadAnimation(context,R.anim.scale);
        view.startAnimation(animationUtils);
    }



}
