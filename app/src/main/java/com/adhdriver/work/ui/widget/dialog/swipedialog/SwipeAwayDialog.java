package com.adhdriver.work.ui.widget.dialog.swipedialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


/**
 * @author kakajika
 * @since 15/08/15.
 */
public class SwipeAwayDialog extends AlertDialog {


    private boolean mSwipeable = true;
    private boolean mTiltEnabled = true;
    private boolean mSwipeLayoutGenerated = false;
    private SwipeDismissTouchListener mListener = null;

    private Context mContext;

    protected SwipeAwayDialog(@NonNull Context context) {
        super(context);
         this.mContext = context;
    }

    protected SwipeAwayDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    /**
     * Set whether dialog can be swiped away.
     */
    public void setSwipeable(boolean swipeable) {
        mSwipeable = swipeable;
    }

    /**
     * Get whether dialog can be swiped away.
     */
    public boolean isSwipeable() {
        return mSwipeable;
    }

    /**
     * Set whether tilt effect is enabled on swiping.
     */
    public void setTiltEnabled(boolean tiltEnabled) {
        mTiltEnabled = tiltEnabled;
        if (mListener != null) {
            mListener.setTiltEnabled(tiltEnabled);
        }
    }

    /**
     * Get whether tilt effect is enabled on swiping.
     */
    public boolean isTiltEnabled() {
        return mTiltEnabled;
    }

    /**
     * Called when dialog is swiped away to dismiss.
     *
     * @return true to prevent dismissing
     */
    public boolean onSwipedAway(boolean toRight) {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!mSwipeLayoutGenerated) {
            Window window = getWindow();
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            View content = decorView.getChildAt(0);
            decorView.removeView(content);

            SwipeableFrameLayout layout = new SwipeableFrameLayout(this.mContext);

            ColorDrawable colorDrawable = new ColorDrawable(0x0000000);
//            layout.setBackgroundDrawable(colorDrawable);
            decorView.setBackgroundDrawable(colorDrawable);

            layout.addView(content);
            decorView.addView(layout);

            mListener = new SwipeDismissTouchListener(decorView, "layout", new SwipeDismissTouchListener.DismissCallbacks() {
//                @Override
//                public boolean canDismiss(Object token) {
//                    return isCancelable() && mSwipeable;
//                }


                @Override
                public boolean canDismiss(Object token) {
                    return mSwipeable;
                }


                @Override
                public void onDismiss(View view, boolean toRight, Object token) {
                    if (!onSwipedAway(toRight)) {
                        dismiss();
                    }
                }
            });
            mListener.setTiltEnabled(mTiltEnabled);
            layout.setSwipeDismissTouchListener(mListener);
            layout.setOnTouchListener(mListener);
            layout.setClickable(true);
            mSwipeLayoutGenerated = true;
        }
    }

}



