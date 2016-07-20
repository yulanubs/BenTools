package com.bentools.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/** 
 * @author 作者名 易皇星
 * @email  邮箱名 15095887072@163.com
 * @time   2016-5-27 2016-05
 * @TODO 缘分是本书，翻得不经意会错过，读得太认真会流泪！
 */

public class BitmapUtils {

	/**
     * 转换图片转换成圆角.
     * 
     * @param bitmap
     *            传入Bitmap对象
     * @return the bitmap
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

//    /**
//     * 将图片圆形化
//     * 
//     * @param bitmap
//     * @return
//     */
//    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
//
//        Bitmap output = null;
//        if (bitmap != null) {
//
//            int width = bitmap.getWidth();
//            int height = bitmap.getHeight();
//            float roundPx;
//            float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
//            if (width <= height) {
//                roundPx = width / 2;
//                top = 0;
//                bottom = width;
//                left = 0;
//                right = width;
//                height = width;
//                dst_left = 0;
//                dst_top = 0;
//                dst_right = width;
//                dst_bottom = width;
//            } else {
//                roundPx = height / 2;
//                float clip = (width - height) / 2;
//                left = clip;
//                right = width - clip;
//                top = 0;
//                bottom = height;
//                width = height;
//                dst_left = 0;
//                dst_top = 0;
//                dst_right = height;
//                dst_bottom = height;
//            }
//            output = Bitmap.createBitmap(width, height,
//                    android.graphics.Bitmap.Config.ARGB_4444);
//            Canvas canvas = new Canvas(output);
//            canvas.setDrawFilter(new PaintFlagsDrawFilter(0,
//                    Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
//            final int color = 0xff424242;
//            final Paint paint = new Paint();
//            final Rect src = new Rect((int) left, (int) top, (int) right,
//                    (int) bottom);
//            final Rect dst = new Rect((int) dst_left, (int) dst_top,
//                    (int) dst_right, (int) dst_bottom);
//            final RectF rectF = new RectF(dst);
//            paint.setAntiAlias(true);
//            canvas.drawARGB(0, 0, 0, 0);
//            paint.setColor(color);
//            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//            canvas.drawBitmap(bitmap, src, dst, paint);
//
//            bitmap = output;
//            return bitmap;
//        }
//        output = null;
//        return null;
//    }
}
