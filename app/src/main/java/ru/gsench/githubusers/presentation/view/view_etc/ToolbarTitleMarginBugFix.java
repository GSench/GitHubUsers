package ru.gsench.githubusers.presentation.view.view_etc;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

/**
 * Created by grish on 13.05.2017.
 */

public class ToolbarTitleMarginBugFix extends android.support.v7.widget.Toolbar {

    public ToolbarTitleMarginBugFix(Context context) {
        super(context);
        setDrawingCacheEnabled(false);
        setChildrenDrawingCacheEnabled(false);
        setWillNotCacheDrawing(true);
    }

    public ToolbarTitleMarginBugFix(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDrawingCacheEnabled(false);
        setChildrenDrawingCacheEnabled(false);
        setWillNotCacheDrawing(true);
    }

    public ToolbarTitleMarginBugFix(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDrawingCacheEnabled(false);
        setChildrenDrawingCacheEnabled(false);
        setWillNotCacheDrawing(true);
    }

    @Override
    public int getTitleMarginBottom() {
        return dpToPx(56);
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}