package com.lvzhihao.slidefragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener
        , View.OnClickListener {

    // 三个textview
    private TextView tab1Tv, tab2Tv, tab3Tv;
    // 指示器
    private ImageView cursorImg;
    // viewpager
    private ViewPager viewPager;
    // fragment对象集合
    private ArrayList<Fragment> fragmentsList;
    // 记录当前选中的tab的index
    private int currentIndex = 0;
    // 指示器的偏移量
    private int offset = 0;
    // 左margin
    private int leftMargin = 0;
    // 屏幕宽度
    private int screenWidth = 0;
    // 屏幕宽度的三分之一
    private int screen1_3;
    //
    private RelativeLayout.LayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screen1_3 = screenWidth / 3;
        cursorImg = (ImageView) findViewById(R.id.cursor);
        lp = (RelativeLayout.LayoutParams) cursorImg.getLayoutParams();
        viewPager = (ViewPager) findViewById(R.id.third_vp);

        tab1Tv = (TextView) findViewById(R.id.tab1_tv);
        tab2Tv = (TextView) findViewById(R.id.tab2_tv);
        tab3Tv = (TextView) findViewById(R.id.tab3_tv);
        //
        tab1Tv.setOnClickListener(this);
        tab2Tv.setOnClickListener(this);
        tab3Tv.setOnClickListener(this);
        initViewPager();
    }

    private void initViewPager() {
        fragmentsList = new ArrayList<>();
        Fragment fragment = new Tab3Fragment();
        fragmentsList.add(fragment);
        fragment = new Tab3Fragment();
        fragmentsList.add(fragment);
        fragment = new Tab3Fragment();
        fragmentsList.add(fragment);

        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragmentsList));
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        offset = (screen1_3 - cursorImg.getLayoutParams().width) / 2;
        lp.leftMargin = position * screen1_3 + offset + positionOffsetPixels / 3;
        cursorImg.setLayoutParams(lp);
        currentIndex = position;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab1_tv:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tab2_tv:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tab3_tv:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
