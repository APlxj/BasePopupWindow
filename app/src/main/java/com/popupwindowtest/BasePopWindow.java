package com.popupwindowtest;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

public abstract class BasePopWindow extends PopupWindow {
    protected Activity mContext;
    public View contentView;

    public BasePopWindow(Activity context) {
        super(context);
        this.mContext = context;
        contentView = LayoutInflater.from(context).inflate(getLayout(), null);
        this.setContentView(contentView);

        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(new BitmapDrawable());
        setOnDismissListener(new DismissListener());
    }

    /**
     * pop xml布局
     */
    public abstract int getLayout();


    /**
     * 消失回调
     */
    public class DismissListener implements OnDismissListener {
        @Override
        public void onDismiss() {
            changBg(1.0f);
        }
    }

    /**
     * 改变透明度
     *
     * @param alpa
     */
    protected void changBg(float alpa) {
        WindowManager.LayoutParams params = mContext.getWindow().getAttributes();
        params.alpha = alpa;
        mContext.getWindow().setAttributes(params);
    }

    /**
     * 显示popupWindow ，位于view下方
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            changBg(0.5f);
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, Gravity.NO_GRAVITY, location[0] + parent.getWidth(), location[1] + DensityUtil.dip2px(mContext, 20));
        } else {
            changBg(1.0f);
            this.dismiss();
        }
    }

    /**
     * 显示popupWindow ，位于view下方
     *
     * @param parent
     */
    public void showPopupWindow_NoBg(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, Gravity.NO_GRAVITY, location[0] + parent.getWidth(), location[1] + DensityUtil.dip2px(mContext, 20));
        } else {
            this.dismiss();
        }
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView 呼出window的view
     * @return window显示的左上角的xOff, yOff坐标
     */
    public int[] calculatePopWindowPos(final View anchorView) {
        if (!this.isShowing()) {
            final int windowPos[] = new int[2];
            final int anchorLoc[] = new int[2];
            // 获取锚点View在屏幕上的左上角坐标位置
            anchorView.getLocationOnScreen(anchorLoc);
            final int anchorHeight = anchorView.getHeight();
            // 获取屏幕的高宽
            final int screenHeight = anchorView.getContext().getResources().getDisplayMetrics().heightPixels;
            final int screenWidth = anchorView.getContext().getResources().getDisplayMetrics().widthPixels;
            // 测量contentView
            contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            // 计算contentView的高宽
            final int windowHeight = contentView.getMeasuredHeight();
            final int windowWidth = contentView.getMeasuredWidth();
            // 判断需要向上弹出还是向下弹出显示
            final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
            if (isNeedShowUp) {
                windowPos[0] = screenWidth - windowWidth;
                windowPos[1] = anchorLoc[1] - windowHeight;
            } else {
                windowPos[0] = screenWidth - windowWidth;
                windowPos[1] = anchorLoc[1] + anchorHeight;
            }
            showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0] - 40/*偏移量*/, windowPos[1]);
            return windowPos;
        } else {
            changBg(1.0f);
            this.dismiss();
            return null;
        }
    }

    /**
     * 关闭Pop
     */
    public void closePopWindow() {
        changBg(1.0f);
        this.dismiss();
    }

    /**
     * 现在在右边
     *
     * @param parent
     */
    public void showRight(View parent) {
        if (!this.isShowing()) {
            changBg(0.5f);
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            showAtLocation(parent, Gravity.NO_GRAVITY, location[0] + parent.getWidth(), location[1]);
        } else {
            changBg(1.0f);
            this.dismiss();
        }

    }

    /**
     * 现在右下边
     *
     * @param parent
     */
    public void showDownRight(View parent) {
        if (!this.isShowing()) {
//            changBg(0.5f);
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            showAtLocation(parent, Gravity.NO_GRAVITY, location[0] - parent.getWidth() * 4, location[1]);
        } else {
            changBg(1.0f);
            this.dismiss();
        }

    }

    /**
     * 现在在中间
     *
     * @param parent
     */
    public void showCenter(View parent) {
        if (!this.isShowing()) {
            changBg(0.5f);
            this.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } else {
            changBg(1.0f);
            this.dismiss();
        }

    }

    /**
     * 现在在顶部
     *
     * @param parent
     */
    public void showTop(View parent) {
        if (!this.isShowing()) {
            changBg(0.5f);
            this.showAtLocation(parent, Gravity.TOP, 0, DensityUtil.getStatusBarHeight(mContext));
        } else {
            changBg(1.0f);
            this.dismiss();
        }

    }

    /**
     * 显示popupWindow ，位于view下方
     *
     * @param parent
     */
    public void showPopupWindow_Below(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, Gravity.NO_GRAVITY, location[0], location[1] + parent.getHeight() + DensityUtil.dip2px(mContext, 0));
        } else {
            this.dismiss();
        }
    }
}
