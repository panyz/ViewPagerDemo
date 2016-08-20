package com.pyz.viewpagerdemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pyz.viewpagerdemo.R;
import com.pyz.viewpagerdemo.adapter.MyFragmentStatePagerAdapter;

/**
 * @Author: pyz
 * @Package: com.pyz.viewpagerdemo
 * @Description: TODO
 * @Project: ViewPagerDemo
 * @Date: 2016/8/1 14:22
 */
public class Fragment1 extends Fragment {

    private ViewPager mViewPager1;
    private TabLayout mTabLayout;
    private String[] tabTitle = {"第一页面","第二页面","第三页面"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1_layout, container, false);
        initViews(rootView);
        initData();
        return rootView;

    }

    private void initViews(View rootView) {
        mViewPager1 = (ViewPager) rootView.findViewById(R.id.mViewPager1);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.mTabLayout);
    }

    private void initData() {
        for (int i=0; i<tabTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#7CCD7C"));
        mTabLayout.setTabTextColors(Color.GRAY,Color.parseColor("#FF4081"));


        mViewPager1.setAdapter(new MyFragmentStatePagerAdapter(getChildFragmentManager(),tabTitle));
        mViewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager1.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
