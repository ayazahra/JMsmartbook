package com.jasamarga.smartbook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.jasamarga.smartbook.utility.SharedPreferencesProvider;

/**
 * Created by apridosandyasa on 8/8/16.
 */
public class SplashActivity extends AppCompatActivity {

    private ImageView iv_logo_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.iv_logo_splash = (ImageView) findViewById(R.id.iv_logo_splash);

        startAnimationSplash();
    }

    private void startAnimationSplash() {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
        fadeIn.setDuration(1500);

        Animation fadeOut = new AlphaAnimation(1, 0.5f);
        fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1500);

        AnimationSet animation = new AnimationSet(false); // change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        animation.setRepeatCount(1);

        this.iv_logo_splash.startAnimation(animation);

        animation.setAnimationListener(new SplashAnimation());
    }

    class SplashAnimation implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            iv_logo_splash.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            iv_logo_splash.setVisibility(View.INVISIBLE);
            if (SharedPreferencesProvider.getInstance().getLogin(SplashActivity.this) == true) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        SplashActivity.this.finish();
                    }
                });
            }else{
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        SplashActivity.this.finish();
                    }
                });
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
