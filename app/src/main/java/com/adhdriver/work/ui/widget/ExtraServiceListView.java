package com.adhdriver.work.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/5/19.
 * 类描述
 * 版本
 */

public class ExtraServiceListView extends ListView {


    public ExtraServiceListView(Context context) {
        super(context);
    }

    public ExtraServiceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtraServiceListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
