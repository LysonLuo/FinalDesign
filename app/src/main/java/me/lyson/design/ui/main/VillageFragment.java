package me.lyson.design.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.lyson.design.R;
import me.lyson.design.ui.common.BaseFragment;

/**
 * Created by Lyson on 15/3/30.
 */
public class VillageFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataset = new String[]{"Linkin Park 2015 global concert", "Linkin Park 2014 global concert", "Linkin Park 2013 global concert", "Linkin Park 2012 global concert"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_village, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_show);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ShowRecyclerViewAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    class ShowRecyclerViewAdapter extends RecyclerView.Adapter<ShowRecyclerViewAdapter.ViewHolder> {
        private String[] mDataset;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewShowName;

            public ViewHolder(View view) {
                super(view);
                textViewShowName = (TextView) view.findViewById(R.id.textview_show_name);
            }
        }

        public ShowRecyclerViewAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        @Override
        public ShowRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.widget_village_card_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textViewShowName.setText(mDataset[position]);

        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
