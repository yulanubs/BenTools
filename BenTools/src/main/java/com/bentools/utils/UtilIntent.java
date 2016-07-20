package com.bentools.utils;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * intent请求工具，提供了默认的进出动画及自定义动画等
 */
public class UtilIntent {

    /**
     * IntentUtil默认的跳转动画 使用前需要预先设置
     */
    public static int INTENT_DEFAULT_ENTER_ANIM;
    public static int INTENT_DEFAULT_EXIT_ANIM;

    /**
     * 设置返回键触发时的动画效果 使用前需要预先设置
     */
    public static int FINISH_DEFAULT_ENTER_ANIM;
    public static int FINISH_DEFAULT_EXIT_ANIM;

    private static Intent intent;

    /**
     * 自定义动画 使用 DEFAULT_ENTER_ANIM 和 DEFAULT_EXIT_ANIM 作为动画效果
     *
     * @param activity Activity
     * @param classes  目标类
     */
    public static void intentDIY(Activity activity, Class<?> classes) {
        intentDIY(activity, classes, null);
    }

    /**
     * 自定义动画 使用 DEFAULT_ENTER_ANIM 和 DEFAULT_EXIT_ANIM 作为动画效果
     *
     * @param activity Activity
     * @param classes  目标类
     * @param paramMap 传入参数
     */
    public static void intentDIY(Activity activity, Class<?> classes, Bundle bundle) {
        intentDIY(activity, classes, bundle, INTENT_DEFAULT_ENTER_ANIM, INTENT_DEFAULT_EXIT_ANIM);
    }

    /**
     * 自定义动画
     *
     * @param activity  Activity
     * @param classes   目标类
     * @param enterAnim enter资源ID
     * @param exitAnim  exit资源ID
     */
    public static void intentDIY(Activity activity, Class<?> classes, int enterAnim, int exitAnim) {
        intentDIY(activity, classes, null, enterAnim, exitAnim);
    }

    /**
     * 自定义动画
     *
     * @param activity  Activity
     * @param classes   目标类
     * @param paramMap  传入参数
     * @param enterAnim enter资源ID
     * @param exitAnim  exit资源ID
     */
    public static void intentDIY(final Activity activity, final Class<?> classes, final Bundle bundle, final int enterAnim,
                                 final int exitAnim) {
        intent = new Intent(activity, classes);
        organize(intent, bundle);
        start(activity);
        if (enterAnim != 0 && exitAnim != 0) {
            activity.overridePendingTransition(enterAnim, exitAnim);
        }
    }

    /**
     * 关闭Activity时产生的动画
     *
     * @param activity Activity
     */
    public static void finishDIY(final Activity activity) {
        finishDIY(activity, FINISH_DEFAULT_ENTER_ANIM, FINISH_DEFAULT_EXIT_ANIM);
    }

    /**
     * 关闭Activity时产生的动画
     *
     * @param activity  Activity
     * @param enterAnim 进入动画
     * @param exitAnim  退出动画
     */
    public static void finishDIY(final Activity activity, final int enterAnim, final int exitAnim) {
        intent = activity.getIntent();
        activity.finish();
        if (enterAnim != 0 && exitAnim != 0) {
            activity.overridePendingTransition(enterAnim, exitAnim);
        }
    }

    /**
     * 系统默认
     *
     * @param activity Activity
     * @param classes  目标
     * @param paramMap 参数
     */
    public static void intentSysDefault(final Activity activity, final Class<?> classes, final Bundle bundle) {
        intent = new Intent(activity, classes);
        organize(intent, bundle);
        start(activity);
    }

    /**
     * 整理参数
     *
     * @param intent Intent
     * @param pm     参数
     */
    private static void organize(final Intent intent, final Bundle bundle) {
        if (bundle == null)
            return;
        intent.putExtras(bundle);
    }

    private static void start(final Activity activity) {
        activity.startActivity(intent);
    }
}
