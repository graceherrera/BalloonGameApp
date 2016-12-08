package com.example.grace.balloongameapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {

   ViewGroup mContentView;

    private int[] balloonColors = new int[4];
    private int nextColor, screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balloonColors[0] = Color.CYAN;
        balloonColors[1] = Color.DKGRAY;
        balloonColors[2] = Color.MAGENTA;
        balloonColors[3] = Color.WHITE;

       getWindow().setBackgroundDrawableResource(R.drawable.city);

        mContentView = (ViewGroup) findViewById(R.id.activity_main);
        setToFullScreen();

        ViewTreeObserver viewTreeObserver = mContentView.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
                @Override
                public void onGlobalLayout() {
                    mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    screenHeight = mContentView.getHeight();
                    screenWidth = mContentView.getWidth();
                }
            });
        }

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        mContentView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == motionEvent.ACTION_UP ){
                    Balloon b = new Balloon(MainActivity.this, balloonColors[nextColor], 100);
                    b.setX(motionEvent.getX());
                    b.setY(screenHeight);
                    mContentView.addView(b);
                    b.releaseBalloon(screenHeight,3000);
                    if(nextColor + 1 == balloonColors.length){
                       nextColor = 0;
                    } else {
                        nextColor++;
                    }
                }
                return false;
            }
        });

    }

    private void setToFullScreen() {
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
        | View.SYSTEM_UI_FLAG_FULLSCREEN
        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }
}
