package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chetuhui.lcj.chezhubao_x.DepthPageTransformer;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.ViewPagerAdatper;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;


import java.util.ArrayList;
import java.util.List;

public class AppIntroActivity extends ActivityBase {

    private ViewPager mIn_vp;
    private LinearLayout mIn_ll;
    private List<View> mViewList;
    private ImageView mLight_dots;
    private int mDistance;
    private ImageView mOne_dot;
    private ImageView iv_in;
    private ImageView mTwo_dot;
    private ImageView mThree_dot;
    private Button mBtn_next;
    private RelativeLayout rl_dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_intro);
        initView();
        initData();
        mIn_vp.setAdapter(new ViewPagerAdatper(mViewList));
        addDots();
        moveDots();
        mIn_vp.setPageTransformer(true,new DepthPageTransformer());
    }

    private void moveDots() {
        mLight_dots.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获得两个圆点之间的距离
                mDistance = mIn_ll.getChildAt(1).getLeft() - mIn_ll.getChildAt(0).getLeft();
                mLight_dots.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });
        mIn_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //页面滚动时小白点移动的距离，并通过setLayoutParams(params)不断更新其位置
                float leftMargin = mDistance * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLight_dots.getLayoutParams();
                params.leftMargin = (int) leftMargin;
                mLight_dots.setLayoutParams(params);
                if(position==2){
                    mBtn_next.setVisibility(View.VISIBLE);
                    rl_dots.setVisibility(View.GONE);


                }
                if(position!=2&&mBtn_next.getVisibility()==View.VISIBLE){
                    mBtn_next.setVisibility(View.GONE);
                    rl_dots.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                //页面跳转时，设置小圆点的margin
                float leftMargin = mDistance * position;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLight_dots.getLayoutParams();
                params.leftMargin = (int) leftMargin;
                mLight_dots.setLayoutParams(params);
                if(position==2){
                    mBtn_next.setVisibility(View.VISIBLE);
                    rl_dots.setVisibility(View.GONE);
                }
                if(position!=2&&mBtn_next.getVisibility()==View.VISIBLE){
                    mBtn_next.setVisibility(View.GONE);
                    rl_dots.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addDots() {
        mOne_dot = new ImageView(this);
        mOne_dot.setImageResource(R.drawable.gray_dot);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 40, 0);
        mIn_ll.addView(mOne_dot, layoutParams);
        mTwo_dot = new ImageView(this);
        mTwo_dot.setImageResource(R.drawable.gray_dot);
        mIn_ll.addView(mTwo_dot, layoutParams);
        mThree_dot = new ImageView(this);
        mThree_dot.setImageResource(R.drawable.gray_dot);
        mIn_ll.addView(mThree_dot, layoutParams);
        setClickListener();

    }

    private void setClickListener() {
        mOne_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIn_vp.setCurrentItem(0);
            }
        });
        mTwo_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIn_vp.setCurrentItem(1);
            }
        });
        mThree_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIn_vp.setCurrentItem(2);
            }
        });
    }


    private void initData() {
        mViewList = new ArrayList<View>();
        LayoutInflater lf = getLayoutInflater().from(AppIntroActivity.this);
        View view1 = lf.inflate(R.layout.intro1, null);
        View view2 = lf.inflate(R.layout.intro2, null);
        View view3 = lf.inflate(R.layout.intro3, null);
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
    }

    private void initView() {

        rl_dots = (RelativeLayout) findViewById(R.id.rl_dots);
        mIn_vp = (ViewPager) findViewById(R.id.in_viewpager);
        mIn_ll = (LinearLayout) findViewById(R.id.in_ll);
        mLight_dots = (ImageView) findViewById(R.id.iv_light_dots);
        iv_in = (ImageView) findViewById(R.id.iv_in);
        mBtn_next = (Button) findViewById(R.id.bt_next);
        mBtn_next.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {

//                Intent intent = new Intent(AppIntroActivity.this, LoginActivity.class);
                Intent intent = new Intent(AppIntroActivity.this, HomeActivity.class);
                startActivity(intent);
                SPTool.putBoolean(AppIntroActivity.this, "isopen", true);
                finish();


            }
        });
        iv_in.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                Intent intent = new Intent(AppIntroActivity.this, HomeActivity.class);
                startActivity(intent);
                SPTool.putBoolean(AppIntroActivity.this, "isopen", true);
                finish();
            }
        });
    }

}
