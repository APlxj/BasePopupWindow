package com.popupwindowtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * 类描述：dp与sp的转换
 * 创建人：evan.yang
 * 创建时间：2016/5/13 9:25
 * Email: william.wang@kemai.cn
 * 修改备注：
 */
public class DensityUtil {


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Activity context) {
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    /**
     * 状态dpi
     *
     * @return
     */
    public static int getDPI(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//		screenWidth = dm.widthPixels;
//		screenHeight = dm.heightPixels;
        return dm.densityDpi;
    }


}
