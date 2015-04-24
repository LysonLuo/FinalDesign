package me.lyson.design.ui.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Lyson on 3/31/15.
 */
public class FlatButton extends info.hoang8f.widget.FButton {
    public FlatButton(Context context) {
        super(context);
        setShadowEnabled(false);
    }

    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setShadowEnabled(false);
    }

    public FlatButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setShadowEnabled(false);
    }
}
