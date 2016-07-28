package com.example.jason_wu.timeout.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.jason_wu.timeout.R;
import com.example.jason_wu.timeout.base.timeout.TimeOutProxy;

/**
 * Created by jason_wu on 7/27/16.
 */
public abstract class BaseInteractionActivity extends Activity {

    private final String TAG = "BaseInteractionActivity";
    private static TimeOutProxy mTimeOutProxy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + hashCode());
        setContentView(R.layout.main_activity);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: " + hashCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: " + hashCode());
        initProxy();
        checkTimeOrUpdateTime();
    }

    @Override
    final public void onUserInteraction() {
        super.onUserInteraction();
        Log.i(TAG, "onUserInteraction: " + hashCode());
        checkTimeOrUpdateTime();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: " + hashCode());
        if (mTimeOutProxy.isShowingDialog(this)) {
            mTimeOutProxy.dismissDialog(this);
            Log.i(TAG, "onPause: isShowingDialog is true.");
        } else {
            Log.i(TAG, "onPause: isShowingDialog is false, remove dialog.");
        }

        mTimeOutProxy.removeCode(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mTimeOutProxy == null) {
            Log.e(TAG, "onStop: Check mTimeOutProxy life cycle.");
            return;
        }

        Log.i(TAG, "onStop: " + hashCode());
        if (mTimeOutProxy.isShowingDialog(this)) {
            mTimeOutProxy.dismissDialog(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimeOutProxy == null) {
            Log.e(TAG, "onDestroy: Check mTimeOutProxy life cycle.");
            return;
        }
        Log.i(TAG, "onDestroy: " + hashCode());

        if (mTimeOutProxy.isShowingDialog(this)) {
            mTimeOutProxy.dismissDialog(this);
        }

        mTimeOutProxy.removeCode(this);
        if (mTimeOutProxy.isEmpty()) {
            Log.d(TAG, "onDestroy: isEmpty , " + hashCode());
            mTimeOutProxy = null;
        } else {
            Log.d(TAG, "onDestroy: list = " + mTimeOutProxy.printAll());
        }
    }

    private void initProxy() {
        if (mTimeOutProxy == null) {
            mTimeOutProxy = new TimeOutProxy(this);
        } else {
            mTimeOutProxy.addCode(this);
        }
    }

    private void checkTimeOrUpdateTime() {
        if (mTimeOutProxy == null) {
            Log.e(TAG, "onDestroy: Check mTimeOutProxy life cycle.");
            return;
        }

        Log.d(TAG, "checkTimeOrUpdateTime:" + hashCode());
        if (mTimeOutProxy.isTimeout(this)) {
            Log.d(TAG, "checkTimeOrUpdateTime: isTimeout , " + hashCode());
            mTimeOutProxy.show(this);
        } else {
            mTimeOutProxy.updateLastInteractionTime();
        }
    }


}
