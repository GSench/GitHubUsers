package ru.gsench.githubusers.presentation.view.view_etc;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;

/**
 * Created by grish on 13.05.2017.
 */

public class CollapsingToolbarLayoutBugFix extends CollapsingToolbarLayout {

    public CollapsingToolbarLayoutBugFix(Context context) {
        super(context);
        setDrawingCacheEnabled(false);
        setChildrenDrawingCacheEnabled(false);
        setWillNotCacheDrawing(true);
    }

    public CollapsingToolbarLayoutBugFix(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDrawingCacheEnabled(false);
        setChildrenDrawingCacheEnabled(false);
        setWillNotCacheDrawing(true);
    }

    public CollapsingToolbarLayoutBugFix(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDrawingCacheEnabled(false);
        setChildrenDrawingCacheEnabled(false);
        setWillNotCacheDrawing(true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(changed) super.onLayout(true, left, top, right, bottom);
    }
}
