package me.lyson.design.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.lyson.design.R;
import me.lyson.design.ui.common.BaseFragment;

/**
 * Created by Lyson on 15/3/30.
 */
public class MeFragment extends BaseFragment {
    @InjectView(R.id.listview_me)
    ListView meListView;
    MeListViewAdapter adapter;
    private String[] mDataSets=new String[]{"", "小区名称", "申请认证", "", "我的订单", "我的优惠券", "我的邀请码", "收货地址", "购物车", "", "邀请家人／租客", "设置"};
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        ButterKnife.inject(this, view);
        adapter = new MeListViewAdapter();
        meListView.setAdapter(adapter);
        return view;
    }

    class MeListViewAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mDataSets.length;
        }

        @Override
        public Object getItem(int position) {
            return mDataSets[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            switch (position){
                case 0:{
                    return 0;
                }
                case 3:
                case 9:{
                 return 1;
                }
                default:{
                    return 2;
                }
            }
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view != null){
                holder=(ViewHolder)view.getTag();
            }else{
                if (getItemViewType(position)==0){
                    view=LayoutInflater.from(getActivity()).inflate(R.layout.widget_user_information_item_image, null);
                }else if (getItemViewType(position)==1){
                    view=new View(getActivity());
                    ListView.LayoutParams layoutParams=new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,20);
                    view.setBackgroundColor(Color.LTGRAY);
                    view.setLayoutParams(layoutParams);
                }else{
                    view=LayoutInflater.from(getActivity()).inflate(R.layout.widget_user_information_item_text, null);
                    holder=new ViewHolder(view);
                    holder.userItemNameTextView.setText(mDataSets[position]);
                    view.setTag(holder);
                }

            }
            return view;
        }

    }

    class ViewHolder{
        @InjectView(R.id.textview_user_item_name)
        TextView userItemNameTextView;
        public ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }
}
