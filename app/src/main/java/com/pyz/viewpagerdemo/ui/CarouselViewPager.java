package com.pyz.viewpagerdemo.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * @Author: pyz
 * @Package: com.pyz.viewpagerdemo.ui
 * @Description: TODO
 * @Project: ViewPagerDemo
 * @Date: 2016/8/19 10:38
 */
public class CarouselViewPager extends ViewPager {

    private int displayTime = 3000;//图片展示的时间，默认为3秒
    private CarouselDirection direction = CarouselDirection.LEFT;//图片自动滑动的方向向左

    public CarouselViewPager(Context context) {
        super(context);
    }

    public CarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置图片展示时间
     * @param time
     */
    public void setDisplayTime(int time){
        displayTime = time;
    }

    /**
     * 设置图片自动滑动的方向
     * @param direction
     */
    public void setDirection(CarouselDirection direction) {
        this.direction = direction;
    }

    /**
     * 开始自动轮播
     */
    public void start(){
        stop();
        postDelayed(automaticDisplay,displayTime);
    }

    /**
     * 停止自动轮播
     */
    public void stop(){
        removeCallbacks(automaticDisplay);
    }

    /**
     * 图片轮播方向枚举类
     */
    public enum CarouselDirection {
        LEFT,RIGHT
    }

    private Runnable automaticDisplay = new Runnable() {
        @Override
        public void run() {
            display(direction);
        }
    };

    /**
     * 图片轮播
     * @param direction
     */
    private synchronized void display(CarouselDirection direction) {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null ) {
            int count = pagerAdapter.getCount();//图片的张数
            int currentItem = getCurrentItem();//当前展示到第几张

            switch (direction) {
                case LEFT:
                    currentItem++;
                    //当前展示的图片为最后一张时，则返回第一张
                    if (currentItem >= count ){
                        currentItem = 0;
                    }
                    break;
                case RIGHT:
                    currentItem--;
                    //当前展示的图片为最后一张时，则返回第一张
                    if (currentItem < 0){
                        currentItem = count-1;
                    }
                    break;
            }
            setCurrentItem(currentItem);
        }
        start();
    }

    @Override
    protected void onFinishInflate() {
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_IDLE){
                    start();
                } else if (state == SCROLL_STATE_DRAGGING) {
                    stop();
                }
            }
        });
    }
}
