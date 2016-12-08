package com.example.grace.balloongameapp;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.renderscript.Sampler;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.example.grace.balloongameapp.utils.PixelHelper;


public class Balloon extends ImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    private ValueAnimator animator;

    public Balloon(Context context) {
        super(context);
    }

    public Balloon(Context context, int color, int rawHeight) {
        super(context);

        this.setImageResource(R.drawable.balloon);
        this.setColorFilter(color);

        int rawWidth = rawHeight / 2;

        int dpHeight = PixelHelper.pixelsToDp(rawHeight, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth, context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth, dpHeight);
        setLayoutParams(params);

    }

    public Balloon() {
        super();
    }

    public void releaseBalloon(int screenHeight, int duration){
        animator = new ValueAnimator();
        animator.setDuration(duration);
        animator.setFloatValues(screenHeight, 0f);
        animator.setInterpolator(new BounceInterpolator());
        animator.setTarget(this);
        animator.addListener(this);
        animator.addUpdateListener(this);
        animator.start();


    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {

    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {

        setY((float) valueAnimator.getAnimatedValue());

    }
}
