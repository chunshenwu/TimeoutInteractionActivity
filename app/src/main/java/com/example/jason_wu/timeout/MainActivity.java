package com.example.jason_wu.timeout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseInteractionActivity {

    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return startIntent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "jason-onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "jason-onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "jason-onPause");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.d(TAG, "jason-onUserLeaveHint");
    }
}
