package com.example.jason_wu.timeout.base.timeout;

import android.app.Activity;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by jason_wu on 7/27/16.
 */
public class TimeOutProxy {

    private static final String TAG = TimeOutProxy.class.getSimpleName();

    private final HashMap<Integer, TimeOutDialog> mMap;

    private final TimeOutController mTimeOutController;

    public TimeOutProxy(final Activity activity) {
        mTimeOutController = new TimeOutController();
        mMap = new HashMap<>();
        addCode(activity);
    }

    public void addCode(Activity activity) {
        final Integer activityHashCode = activity.hashCode();

        if (!mMap.containsKey(activityHashCode)) {
            TimeOutDialog timeOutDialog = new TimeOutDialog(activity);
            Log.d(TAG, "addCode: " + activityHashCode);
            mMap.put(activityHashCode, timeOutDialog);
            Log.w(TAG, "addCode: activityHashCode " + activityHashCode);
        } else {
            Log.w(TAG, "addCode: activityHashCode " + activityHashCode + " is exist.");
        }
    }
//
    public boolean isTimeout(Activity activity) {
        Log.i(TAG, "isTimeout: " + activity.hashCode());
        return mTimeOutController.isTimeout();
    }
//
    public boolean isEmpty() {
        return mMap.isEmpty();
    }

    public String printAll() {
        StringBuilder sb = new StringBuilder();
        for (Integer val : mMap.keySet()) {
            sb.append(val).append(",");
        }
        return sb.toString();
    }


    public void removeCode(final Activity activity) {
        final Integer activityHashCode = activity.hashCode();
        if (mMap.containsKey(activityHashCode)) {
            mMap.remove(activityHashCode);
            Log.w(TAG, "removeCode: activityHashCode " + activityHashCode);
        } else {
            Log.w(TAG, "removeCode: activityHashCode " + activityHashCode + " not exist.");
        }
    }

    public void show(final Activity activity) {
        final Integer activityHashCode = activity.hashCode();
        if (mMap.containsKey(activityHashCode)) {
            TimeOutDialog timeOutDialog = mMap.get(activityHashCode);
            timeOutDialog.show();
        } else {
            Log.w(TAG, "show: activityHashCode " + activityHashCode + " not exist.");
        }
    }
//
    public void updateLastInteractionTime() {
        mTimeOutController.updateLastInteractionTime();
    }

    public boolean isShowingDialog(Activity activity) {
        final Integer activityHashCode = activity.hashCode();
        if (mMap.containsKey(activityHashCode)) {
            TimeOutDialog timeOutDialog = mMap.get(activityHashCode);
            return timeOutDialog.isShowing();
        } else {
            Log.w(TAG, "show: activityHashCode " + activityHashCode + " not exist.");
            return false;
        }
    }

    public void dismissDialog(final Activity activity) {
        final Integer activityHashCode = activity.hashCode();
        if (mMap.containsKey(activityHashCode)) {
            TimeOutDialog timeOutDialog = mMap.get(activityHashCode);
            timeOutDialog.dismiss();
        } else {
            Log.w(TAG, "show: activityHashCode " + activityHashCode + " not exist.");
        }
    }
}
