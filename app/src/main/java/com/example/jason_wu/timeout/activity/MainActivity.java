package com.example.jason_wu.timeout.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jason_wu.timeout.base.BaseInteractionActivity;
import com.example.jason_wu.timeout.R;

public class MainActivity extends BaseInteractionActivity {

    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ((TextView)findViewById(R.id.tv)).setText(String.valueOf(hashCode()));

        findViewById(R.id.endBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "jason-onClick");
                startActivity(MainActivity.getStartIntent(getApplicationContext()));
            }
        });
    }



    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return startIntent;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d(TAG, "jason-onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.d(TAG, "jason-onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.d(TAG, "jason-onPause");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
//        Log.d(TAG, "jason-onUserLeaveHint");
    }
}
