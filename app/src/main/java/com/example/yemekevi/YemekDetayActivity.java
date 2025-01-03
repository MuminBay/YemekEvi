package com.example.yemekevi;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class YemekDetayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek_detay);

        setupClickListeners();
    }

    private void setupClickListeners() {
        int[] layoutIds = {
            R.id.yemekLayout1, R.id.yemekLayout2, R.id.yemekLayout3,
            R.id.yemekLayout4, R.id.yemekLayout7, R.id.yemekLayout8
        };

        String[] yemekTurleri = {
            "kebap", "sushi", "tacos", "pizza", "doner", "tokat_kebabi"
        };

        for (int i = 0; i < layoutIds.length; i++) {
            final int index = i;
            LinearLayout layout = findViewById(layoutIds[i]);
            layout.setOnClickListener(v -> handleLayoutClick(v, yemekTurleri[index]));
        }
    }

    private void handleLayoutClick(View view, String yemekTuru) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.5f);
        fadeOut.setDuration(100);
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0.5f, 1f);
                fadeIn.setDuration(100);
                fadeIn.start();
                navigateToDetailPage(yemekTuru);
            }
        });
        fadeOut.start();
    }

    private void navigateToDetailPage(String yemekTuru) {
        Intent intent = new Intent(YemekDetayActivity.this, YeniBosSayfaActivity.class);
        intent.putExtra("yemek_turu", yemekTuru);
        startActivity(intent);
    }
} 