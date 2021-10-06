package com.juliardi.messengger.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.juliardi.messengger.MainActivity;
import com.juliardi.messengger.R;
import com.juliardi.messengger.adapter.OnBoardingAdapter;

public class OnBoarding extends AppCompatActivity {
    //deklarasi variabel
    SessionManager sessionManager;
    ViewPager viewPager;
    LinearLayout dotsLayout;
    OnBoardingAdapter onBoardingAdapter;
    TextView[] dots;
    Button letsGetStarted;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //mengecek jika sudah login maka akan diarahkan ke mainActivity
        sessionManager = new SessionManager(OnBoarding.this);
        if(sessionManager.isLoggedIn()){
            moveMainActivity();
        }

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);

        onBoardingAdapter = new OnBoardingAdapter(this);
        viewPager.setAdapter(onBoardingAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    private void moveMainActivity() {
        Intent intent = new Intent(OnBoarding.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }


    public void skip(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position) {

        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("•"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.purple_500));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @SuppressLint("ResourceType")
        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if (position == 0) {
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                letsGetStarted.setVisibility(View.INVISIBLE);
            } else {
              //  animation = AnimationUtils.loadAnimation(OnBoarding.this, R.id.get_started_btn);
               // letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
