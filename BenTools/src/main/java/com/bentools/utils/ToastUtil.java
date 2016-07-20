package com.bentools.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @Title: ToastUtil --
 * @Desc:
 * @CreatDate: 2016-03-17 13:43
 * @Author liyan
 * @Version 1.0
 */
public class ToastUtil{


	public static final long HALF_SECOND_LONG = 500L;
    public static final long ONE_SECONE_LONG = 1000L;
    public static final long TWO_SECONE_LONG = 2000L;


    /**
     * @Title: showMsgByToastR
     * @Description:
     * @param @param context
     * @param @param strId
     * @return void
     * @throws
     */
    public static void showMsgByToastR(Context context, int strId) {
        Toast toast = Toast.makeText(context, context.getText(strId),//AppUtil.getStrValue(context, strId),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * @Title: showMsgByToast
     * @Description:
     * @param @param context
     * @param @param mesStr
     * @return void
     * @throws
     */
    public static void showMsgByToast(Context context, String mesStr) {
        Toast toast = Toast.makeText(context, mesStr, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showMsgByToastR(final Activity activity,final int strId,final long time){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Toast toast = Toast.makeText(activity, activity.getText(strId), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }

    public static void showMsgByToast(final Activity activity,final String mesStr,final long time){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Toast toast = Toast.makeText(activity, mesStr, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }
}
