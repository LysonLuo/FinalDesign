package me.lyson.design.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.lyson.design.R;

/**
 * Created by Lyson on 15/3/27.
 */
public class RetryFragment extends Fragment {
    private TextView retryTextView;
    private OnRetryListener retryListener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retry, container, false);
        retryTextView = (TextView) view.findViewById(R.id.txt_retry);
        retryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retryListener != null) {
                    retryListener.onRetry();
                }
            }
        });

        return view;
    }

    public void setOnRetryListener(OnRetryListener listener) {
        retryListener = listener;
    }

    public interface OnRetryListener {
        void onRetry();
    }
}
