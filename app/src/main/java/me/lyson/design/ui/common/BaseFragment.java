package me.lyson.design.ui.common;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import me.lyson.design.ui.BaseActivity;
import me.lyson.design.ui.utils.MagicLog;

/**
 * Created by Lyson on 15/3/27.
 */
public class BaseFragment extends Fragment {
    private static final String LogTag= BaseActivity.class.getCanonicalName();
    private ProgressDialog mProgressdialog;
    private ProgressFragment mProgressFrgament;
    private RetryFragment mRetryFragment;
    private NoDataFragment mNoDataFragment;

    private boolean isFragmentAlive=true;

    @Override
    public void onDestroy() {
        super.onDestroy();
        isFragmentAlive=false;
    }

    /**
     * 显示圆形进度条的Fragment，需要显示指定ViewGroup的id
     *
     * @param layoutId
     */
    protected void showProgressFragment(int layoutId) {
        if (!isFragmentAlive) {
            MagicLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (getActivity() != null && !((BaseActivity) getActivity()).isActivityAlive()) {
            MagicLog.d(LogTag, "activity is not alive");
            return;
        }
        if (mProgressFrgament == null) {
            mProgressFrgament = new ProgressFragment();
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!mProgressFrgament.isAdded()) {
            ft.add(layoutId, mProgressFrgament, "");
        }
        ft.show(mProgressFrgament);

        if (mProgressFrgament != null && mProgressFrgament.isVisible()) {
            ft.remove(mProgressFrgament);
        }

        ft.commitAllowingStateLoss();
    }

    protected void removeProgressFragment() {
        if (!isFragmentAlive) {
            MagicLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (!((BaseActivity) getActivity()).isActivityAlive()) {
            MagicLog.d(LogTag, "activity is not alive");
            return;
        }
        if (mProgressFrgament == null) {
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(mProgressFrgament);
        ft.commitAllowingStateLoss();
        mProgressFrgament = null;
    }

    /**
     * 在数据加载失败或其他异常情况的情况下，调用该方法显示RetryFragment，需要显示指定ViewGroup的id
     */
    protected void showRetryFragment(int layoutId, RetryFragment.OnRetryListener listener) {
        if (!isFragmentAlive) {
            MagicLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (!((BaseActivity) getActivity()).isActivityAlive()) {
            MagicLog.d(LogTag, "activity is not alive");
            return;
        }
        if (mRetryFragment == null) {
            mRetryFragment = new RetryFragment();
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!mRetryFragment.isAdded()) {
            ft.add(layoutId, mRetryFragment, "");
        }
        ft.show(mRetryFragment);
        ft.commitAllowingStateLoss();
        mRetryFragment.setOnRetryListener(listener);
    }

    protected void removeRetryFragment() {
        if (!isFragmentAlive) {
            MagicLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (!((BaseActivity) getActivity()).isActivityAlive()) {
            MagicLog.d(LogTag, "activity is not alive");
            return;
        }
        if (mRetryFragment == null) {
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(mRetryFragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 无数据时所显示的UI
     *
     * @param layouId
     */
    protected void showNoDataFragment(int layouId) {
        if (!isFragmentAlive) {
            MagicLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (!((BaseActivity) getActivity()).isActivityAlive()) {
            MagicLog.d(LogTag, "activity is not alive");
            return;
        }
        if (mNoDataFragment == null) {
            mNoDataFragment = new NoDataFragment();
        }
        FragmentManager fm = getChildFragmentManager();
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
        if (!isFragmentAlive) {
            MagicLog.d(LogTag, "fragment is not alive");
            return;
        }
        if (!((BaseActivity) getActivity()).isActivityAlive()) {
            MagicLog.d(LogTag, "activity is not alive");
            return;
        }
        if (mNoDataFragment == null) {
            return;
        }
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(mNoDataFragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * @param message
     */
    protected void showProgressDialog(String message) {
        if (mProgressdialog == null) {
            mProgressdialog = new ProgressDialog(getActivity());
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

}
