package me.lyson.design.ui.common;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.lyson.design.R;

/**
 * Created by Lyson on 15/3/27.
 */
public class ToolbarSetter {
    public static void setupDefaultToolbar(final Context context,Toolbar toolbar){
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof BaseActivity){
                    ((BaseActivity)context).finish();
                }
            }
        });
    }

    public static void setupMainToolbar(final Context context,Toolbar toolbar){
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationOnClickListener(null);
    }
}
