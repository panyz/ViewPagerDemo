package com.pyz.viewpagerdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pyz.viewpagerdemo.R;
import com.pyz.viewpagerdemo.adapter.CarouselPagerAdapter;
import com.pyz.viewpagerdemo.ui.CarouselViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: pyz
 * @Package: com.pyz.viewpagerdemo
 * @Description: TODO
 * @Project: ViewPagerDemo
 * @Date: 2016/8/1 14:23
 */
public class Fragment2 extends Fragment implements ViewPager.OnPageChangeListener {
    private CarouselViewPager mCarouselView;
    private List<ImageView> ivList = new ArrayList<ImageView>();
    private int[] ivIds = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4};

    private ImageView[] indicationPoint;//指示点控件
    private LinearLayout pointLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment2_layout, container, false);
        initViews(rootView);
        initData();
        return rootView;
    }

    private void initViews(View rootView) {
        mCarouselView = (CarouselViewPager) rootView.findViewById(R.id.mCarouselView);
        pointLayout = (LinearLayout) rootView.findViewById(R.id.pointLayout);
    }

    private void initData() {
        for (int i = 0; i < ivIds.length; i++) {
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(ivIds[i]);
            ivList.add(iv);
        }

        indicationPoint = new ImageView[ivList.size()];
        for (int i = 0; i < indicationPoint.length; i++) {
            ImageView point = new ImageView(getActivity());
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(10, 10);
            layout.setMargins(10, 0, 10, 0);
            point.setLayoutParams(layout);

            indicationPoint[i] = point;
            if (i == 0) {
                indicationPoint[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                indicationPoint[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
            pointLayout.addView(point);
        }

        mCarouselView.setAdapter(new CarouselPagerAdapter(ivList));
        mCarouselView.addOnPageChangeListener(this);
        mCarouselView.setDisplayTime(2000);
        mCarouselView.start();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPointColor(position % ivList.size());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setPointColor(int selectItem) {
        for (int i = 0; i < indicationPoint.length; i++) {
            if (i == selectItem) {
                indicationPoint[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                indicationPoint[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }

        }
    }


}
