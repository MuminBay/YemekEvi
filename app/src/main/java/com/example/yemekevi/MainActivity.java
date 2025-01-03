package com.example.yemekevi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.BitmapShader;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView logoImageView;
    private TextView loadingText;
    private TextView yemekEviText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupLogo();
        startAnimation();
        checkInternetConnection();
        scheduleNextActivity();
    }

    private void initializeViews() {
        logoImageView = findViewById(R.id.logoImageView);
        loadingText = findViewById(R.id.loadingText);
        yemekEviText = findViewById(R.id.yemekEviText);

        logoImageView.setVisibility(View.VISIBLE);
        loadingText.setVisibility(View.VISIBLE);
        yemekEviText.setVisibility(View.VISIBLE);
    }

    private void setupLogo() {
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.yemek1);
        Bitmap circularBitmap = createCircularBitmap(originalBitmap);
        logoImageView.setImageBitmap(circularBitmap);
    }

    private Bitmap createCircularBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Paint paint = new Paint();
        paint.setShader(shader);
        paint.setAntiAlias(true);

        Canvas canvas = new Canvas(output);
        float radius = Math.min(bitmap.getWidth(), bitmap.getHeight()) / 2.0f;
        canvas.drawRoundRect(new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight()), radius, radius, paint);

        return output;
    }

    private void startAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(2500);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        logoImageView.startAnimation(rotateAnimation);
    }

    private void checkInternetConnection() {
        boolean isConnected = isInternetAvailable();
        Toast.makeText(this, String.valueOf(isConnected), Toast.LENGTH_LONG).show();
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void scheduleNextActivity() {
        new Handler().postDelayed(() -> {
            logoImageView.clearAnimation();
            hideViews();
            navigateToNextScreen();
        }, 3500);
    }

    private void hideViews() {
        logoImageView.setVisibility(View.GONE);
        loadingText.setVisibility(View.GONE);
        yemekEviText.setVisibility(View.GONE);
    }

    private void navigateToNextScreen() {
        Intent intent = new Intent(MainActivity.this, YemekDetayActivity.class);
        startActivity(intent);
    }
}