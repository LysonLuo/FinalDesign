package me.lyson.design.ui.common;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import me.lyson.design.R;
import me.lyson.design.utils.MagicLog;

/**
 * Created by Lyson on 15/3/27.
 * 基类，所有Activity都要继承
 */
public class BaseActivity extends ActionBarActivity {
    public static final int TOOLBAR_POSITION_TOP = 0;
    public static final int TOOLBAR_POSITION_COVER = 1;

    private static final String LogTag = BaseActivity.class.getCanonicalName();

    private Toolbar mToolbar;
    private ProgressDialog mProgressdialog;
    private ProgressFragment mProgressFragment;
    private RetryFragment mRetryFragment;
    private NoDataFragment mNoDataFragment;

    private boolean isActivityAlive = true;

    private boolean isShowToolbar = true;
    private int toolbarPosition = TOOLBAR_POSITION_TOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        ViewGroup topLevelLayout = null;
        if (isShowToolbar) {
            if (toolbarPosition == TOOLBAR_POSITION_TOP) {
                topLevelLayout = new LinearLayout(this);
                topLevelLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ((LinearLayout) topLevelLayout).setOrientation(LinearLayout.VERTICAL);
            } else if (toolbarPosition == TOOLBAR_POSITION_COVER) {
                topLevelLayout = new FrameLayout(this);
                topLevelLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            Toolbar toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.widget_toolbar, null);
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material));
            toolbar.setLayoutParams(layoutParams);
            //This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead
            setSupportActionBar(toolbar);
            ToolbarSetter.setupDefaultToolbar(this, toolbar);
            mToolbar = toolbar;
        }
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        FrameLayout container = new FrameLayout(this);
        container.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        container.setId(R.id.content_layout_id);
        container.addView(view);

        if (topLevelLayout != null) {
            if (toolbarPosition == TOOLBAR_POSITION_TOP) {
                topLevelLayout.addView(mToolbar);
                topLevelLayout.addView(container);
            } else if (toolbarPosition == TOOLBAR_POSITION_COVER) {
                topLevelLayout.addView(container);
                topLevelLayout.addView(mToolbar);
            }
        }
        setContentView(isShowToolbar ? topLevelLayout : container);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public Toolbar getToolBar() {
        return mToolbar;
    }

    public boolean isActivityAlive() {
        return isActivityAlive;
    }

    /**
     * 在setContentView前调用
     *
     * @param isShowToolbar
     */
    public void setShowToolbar(boolean isShowToolbar) {
        this.isShowToolbar = isShowToolbar;
    }

    public boolean isShowToolbar() {
        return isShowToolbar;
    }
    /**
     * 在setContentView前调用
     * @param
     */
    public void setToolbarPosition(int toolbarPosition) {
        this.toolbarPosition = toolbarPosition;
    }
    /**
     * 显示圆形进度条的Fragment，区别于ProgressDialog，覆盖在整个Activity中
     */
    protected void showProgressFragment() {
        if (!isActivityAlive) {
            MagicLog.d(LogTag, "Activity is not aclive");
            return;
        }
        if (mProgressFragment == null) {
            mProgressFragment = new ProgressFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!mProgressFragment.isAdded()) {
            ft.add(R.id.content_layout_id, mProgressFragment, "");
        }
        ft.show(mProgressFragment);

        if (mRetryFragment != null && mRetryFragment.isVisible()) {
            ft.remove(mRetryFragment);
        }

        ft.commitAllowingStateLoss();
    }

    /**
     * 把ProgressFragment从Activity中移除
     */
    protected void removeProgressFragment() {
        if (!isActivityAlive) {
            MagicLog.d(LogTag,"activity is not alive");
            return;
        }
        if (mProgressFragment==null){
            return;
        }
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.remove(mProgressFragment);
        ft.commitAllowingStateLoss();
    }

    protected void showNoDataFragment() {
        showNoDataFragment(R.id.content_layout_id);
    }

    protected void showNoDataFragment(String text) {
        showNoDataFragment(R.id.content_layout_id, text);
    }

    protected void showNoDataFragment(int layouId) {
        if (!isActivityAlive) {
            MagicLog.d(LogTag, "activity is not alive");
            return;
        }
        if (mNoDataFragment == null) {
            mNoDataFragment = new NoDataFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!mNoDataFragment.isAdded()) {
            ft.add(layouId, mNoDataFragment, "");
        }
        ft.show(mNoDataFragment);
        ft.commitAllowingStateLoss();
    }

    protected void showNoDataFragment(int layouId, String text) {
        showNoDataFragment(layouId);
        if (mNoDataFragment != null) {
            mNoDataFragment.setText(text);
        }
    }

    protected void removeNoDataFragment() {
        if (!isActivityAlive) {
            MagicLog.d(LogTag, "activity is not alive");
            return;
        }
        if (mNoDataFragment == null) {
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(mNoDataFragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 显示圆形进度条对话框，获取整个window的焦点，一般用于登录等具有输入操作的Activity
     *
     * @param message
     */
    protected void showProgressDialog(String message) {
        if (mProgressdialog == null) {
            mProgressdialog = new ProgressDialog(this);
            mProgressdialog.setCanceledOnTouchOutside(false);
            mProgressdialog.setCancelable(false);
        }
        if (mProgressdialog.isShowing()) {
            mProgressdialog.dismiss();
        }
        mProgressdialog.setMessage(message);
        mProgressdialog.show();
    }

    protected void dismissProgressDialog() {
        if (mProgressdialog != null) {
            mProgressdialog.dismiss();
        }
    }

    protected void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
