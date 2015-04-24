package me.lyson.design.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.lyson.design.R;
import me.lyson.design.ui.common.BaseFragment;
import me.lyson.design.ui.view.ExpandableHeightGridView;

/**
 * Created by Lyson on 15/3/30.
 */
public class MarketFragment extends BaseFragment {
    @InjectView(R.id.swipe_refresh_layout_market)
    SwipeRefreshLayout refreshLayout;
    @InjectView(R.id.scrollview_market)
    ScrollView scrollView;
    @InjectView(R.id.viewpager_market_banner)
    ViewPager bannerViewPager;
    @InjectView(R.id.gridview_market)

    ExpandableHeightGridView gridView;

    MarketGridViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_market,null);
        ButterKnife.inject(this,view);

        gridView.setExpanded(true);
        gridView.setFocusable(false);
        adapter=new MarketGridViewAdapter(getActivity());
        gridView.setAdapter(adapter);
        gridView.setDrawSelectorOnTop(true);
        bannerViewPager.setAdapter(new BannerPagerAdapter());

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    class MarketGridViewAdapter extends BaseAdapter{
        private Context context;

        public MarketGridViewAdapter(Context context){
            this.context=context;
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view!=null){
                holder=(ViewHolder)view.getTag();
            }else{
                view=LayoutInflater.from(context).inflate(R.layout.widget_market_card_view,viewGroup,false);
                holder=new ViewHolder(view);
                view.setTag(holder);
            }
            return view;
        }
    }

    class ViewHolder{
        @InjectView(R.id.circleimageview_market_item)
        CircleImageView imageView;
        @InjectView(R.id.textview_market_item_name)
        TextView marketName;
        public ViewHolder(View view){
            ButterKnife.inject(this,view);
        }
    }

    class BannerPagerAdapter extends PagerAdapter{
        public BannerPagerAdapter(){

        }
        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view =LayoutInflater.from(getActivity()).inflate(R.layout.widget_market_banner,null);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }


}
