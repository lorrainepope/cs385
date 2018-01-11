package com.wherecycle.smartrecycle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
/*
* created by Lorraine
* This is the splash screen activity. It will display an animated icon that will
* rotate when the app opens. Also tells the app where to go when the anumation is
* finsihed (ie the main activity)*/
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //below is the media player that will play a short sound of glass being dropped into a bottle bank
        final MediaPlayer mp1 = MediaPlayer.create(SplashScreenActivity.this, R.raw.glasssmash);
        mp1.start();
        final ImageView imageView = (ImageView) findViewById(R.id.splashImage);
        final Animation animation1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation animation2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.antirotate);
        final Animation animation3 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        imageView.startAnimation(animation2);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation3);
                finish();
                Intent i = new Intent (getBaseContext(),MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
