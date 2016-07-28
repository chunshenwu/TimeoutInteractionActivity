package com.example.jason_wu.timeout.base.timeout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.example.jason_wu.timeout.activity.SplashingActivity;

class TimeOutDialog {

    private static final String TAG = TimeOutDialog.class.getSimpleName();
    private final Activity mActivity;
    private final AlertDialog mAlertDialog;
    private int hashCode;
    TimeOutDialog(final Activity activity) {
        mActivity = activity;
        hashCode = mActivity.hashCode();
        mAlertDialog = getAlertDialog("時間截止視窗", "您已太久沒有使用,按下OK按鈕轉回登入頁面");
    }

    private AlertDialog getAlertDialog(String title, String message) {
        //產生一個Builder物件
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
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
                mActivity.startActivity(SplashingActivity.getStartIntent(mActivity.getApplicationContext()));
                mActivity.finish();
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

    void show() {
        Log.d(TAG, "show: " + mActivity.hashCode());
        mAlertDialog.setMessage(String.valueOf(hashCode));
        mAlertDialog.show();
    }

    void dismiss() {
        Log.d(TAG, "dismiss: " + mActivity.hashCode());
        mAlertDialog.dismiss();
    }


    boolean isShowing() {
        return mAlertDialog.isShowing();
    }
}
