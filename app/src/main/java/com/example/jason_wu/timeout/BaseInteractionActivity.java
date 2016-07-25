package com.example.jason_wu.timeout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;

public abstract class BaseInteractionActivity extends Activity {

    private final String TAG = getClass().getSimpleName();
    private static long sLastInteractionTime = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        updateLastInteractionTime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sLastInteractionTime != -1) {
            checkTimeout();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void updateLastInteractionTime() {
        sLastInteractionTime = System.currentTimeMillis();
    }

    private boolean checkTimeout() {
        final long interval = System.currentTimeMillis() - sLastInteractionTime;
        if (interval > getIntervalTime()) {
            finishSelfForTimeout();
            return true;
        } else {
            return false;
        }
    }

    private long getIntervalTime() {
//        return 3 * DateUtils.MINUTE_IN_MILLIS;
        return 3 * DateUtils.SECOND_IN_MILLIS;
    }

    private void finishSelfForTimeout() {
        getAlertDialog("時間截止視窗", "您已太久沒有使用,按下OK按鈕轉回登入頁面").show();
    }

    @Override
    final public void onUserInteraction() {
        super.onUserInteraction();
        if (sLastInteractionTime != -1) {
            if (checkTimeout()) {
                return;
            }
        }
        updateLastInteractionTime();
    }

    private AlertDialog getAlertDialog(String title, String message){
        //產生一個Builder物件
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //設定Dialog的標題
        builder.setTitle(title);
        //設定Dialog的內容
        builder.setMessage(message);
        //設定Positive按鈕資料
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //按下按鈕時顯示快顯
//                Toast.makeText(getApplicationContext(), "您按下OK按鈕", Toast.LENGTH_SHORT).show();
                startActivity(SplashingActivity.getStartIntent(getApplicationContext()));
                finish();
            }
        });

//        //設定Negative按鈕資料
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //按下按鈕時顯示快顯
//                Toast.makeText(getApplicationContext(), "您按下Cancel按鈕", Toast.LENGTH_SHORT).show();
//            }
//        });
        //利用Builder物件建立AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        return alertDialog;
    }
}
