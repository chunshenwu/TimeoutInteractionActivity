package com.example.jason_wu.timeout.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jason_wu.timeout.R;

public class SplashingActivity extends Activity {

    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashing_activity);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.getStartIntent(getApplicationContext()));
                finish();
            }
        });
    }

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, SplashingActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return startIntent;
    }
}
