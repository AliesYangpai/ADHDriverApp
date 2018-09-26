package com.adhdriver.work.ui.widget.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by Administrator on 2018/1/5.
 * 类描述
 * 版本
 */

public class AdditionServiceGridView extends GridView {
    public AdditionServiceGridView(Context context) {
        super(context);
    }

    public AdditionServiceGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdditionServiceGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
