package me.lyson.design.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.lyson.design.R;
import me.lyson.design.ui.common.BaseActivity;
import me.lyson.design.ui.common.ToolbarSetter;

/**
 * Created by Lyson on 15/3/27.
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @InjectView(R.id.viewpager)
    ViewPager viewPager;

    MainPagerAdapter pagerAdapter;
    MainPagerChangeListener pagerChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        setTitle("Community");

        ToolbarSetter.setupMainToolbar(this, getToolBar());

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        pagerChangeListener = new MainPagerChangeListener();
        viewPager.setOnPageChangeListener(pagerChangeListener);
        tabs.setViewPager(viewPager);
        tabs.setOnPageChangeListener(pagerChangeListener);
        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int i) {
                viewPager.setCurrentItem(i, true);
            }
        });
    }


    class MainPagerAdapter extends FragmentPagerAdapter {
        private final String[] TITLE = {"活动", "话题", "团购", "我的"};

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0 ? new MarketFragment() : position == 1 ? new VillageFragment() : position == 2 ? new MeFragment() : new MeFragment();
        }
    }

    class MainPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageSelected(int position) {

        }
    }
}
