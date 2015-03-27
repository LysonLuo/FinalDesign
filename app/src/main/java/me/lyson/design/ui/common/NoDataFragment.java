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
public class NoDataFragment extends Fragment {

    private TextView noDataTextView;
    private String text = "暂无数据";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nodata, container, false);
        noDataTextView = (TextView) view.findViewById(R.id.txt_no_data);
        noDataTextView.setText(text);
        return view;
    }

    public void setText(String text) {
        this.text = text;
        if (noDataTextView != null) {
            noDataTextView.setText(text);
        }
    }
}
