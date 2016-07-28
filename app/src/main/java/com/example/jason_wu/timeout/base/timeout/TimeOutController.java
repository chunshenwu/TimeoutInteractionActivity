package com.example.jason_wu.timeout.base.timeout;

import android.text.format.DateUtils;
import android.util.Log;

/**
 * Created by jason_wu on 7/27/16.
 */
class TimeOutController {


    private static final String TAG = TimeOutController.class.getSimpleName();

    private static long sLastInteractionTime = -1;

    TimeOutController() {
        updateLastInteractionTime();
    }

    boolean isTimeout() {
        Log.d(TAG, "isTimeout:" + hashCode());
        final long interval = System.currentTimeMillis() - sLastInteractionTime;
        if (interval > getIntervalTime()) {
            return true;
        } else {
            return false;
        }
    }

    void updateLastInteractionTime() {
        Log.d(TAG, "updateLastInteractionTime:" + hashCode());
        sLastInteractionTime = System.currentTimeMillis();
    }

    private long getIntervalTime() {
//        return 3 * DateUtils.MINUTE_IN_MILLIS;
        return 1 * DateUtils.SECOND_IN_MILLIS;
    }

}
