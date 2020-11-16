package com.android.canvas;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.TextView;

public class ScaleTextView extends androidx.appcompat.widget.AppCompatTextView {

    public ScaleTextView(Context context) {
        this(context, null);
    }

    public ScaleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        updateTextSize(context);
    }

    public void updateTextSize(Context context) {
        getTypeface();
        float currentTextSize = getTextSize();
        SharedPreferences otherSettings = context.getSharedPreferences("settings", 0);
        float newScale = otherSettings.getFloat("key_scaling", 1f);
        setTextSize(newScale * currentTextSize);
    }

}