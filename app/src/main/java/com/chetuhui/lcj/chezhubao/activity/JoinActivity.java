package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.tool.BaseTool;
import com.chetuhui.lcj.chezhubao.tool.TextTool;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

import static com.chetuhui.lcj.chezhubao.tool.Constants.MIN_CLICK_DELAY_TIME;

public class JoinActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarJoin;
    /**
     * ￥1234567
     */
    private TextView mTvJoinJijinchijine;
    private RelativeLayout mRlJoinR1;
    /**  */
    private TextView mTvJoinFangan99;
    /**  */
    private TextView mTvJoinFangan199;
    /**  */
    private TextView mTvJoinFangan399;
    /**  */
    private TextView mTvJoinFangan599;
    /**  */
    private TextView mTvJoinFangan799;
    /**
     * 99/年
     */
    private SuperTextView mTvJoinXuanze99;
    /**
     * 199/年
     */
    private SuperTextView mTvJoinXuanze199;
    /**
     * 399/年
     */
    private SuperTextView mTvJoinXuanze399;
    /**
     * 599/年
     */
    private SuperTextView mTvJoinXuanze599;
    /**
     * 799/年
     */
    private SuperTextView mTvJoinXuanze799;
    private CheckBox mCbJoinTongyi;
    /**
     * 《互助平台须知》
     */
    private TextView mTvJoinXuzhi;
    /**
     * 《平台条款》
     */
    private TextView mTvJoinTiaokuan;
    /**
     * 《用户协议》
     */
    private TextView mTvJoinXieyi;
    private ImageView mIvJoinContent;
    /**
     * ￥99
     */
    private TextView mTvJoinYixuanjine;
    /**
     * 我要加入
     */
    private SuperTextView mTvJoinWoyaojiaru;
    private RelativeLayout mRlJoinR2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        initView();
    }

    private void initView() {
        mTitlebarJoin = (CommonTitleBar) findViewById(R.id.titlebar_join);
        mTitlebarJoin.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
                // CommonTitleBar.ACTION_LEFT_TEXT;        // 左边TextView被点击
                // CommonTitleBar.ACTION_LEFT_BUTTON;      // 左边ImageBtn被点击
                // CommonTitleBar.ACTION_RIGHT_TEXT;       // 右边TextView被点击
                // CommonTitleBar.ACTION_RIGHT_BUTTON;     // 右边ImageBtn被点击
                // CommonTitleBar.ACTION_SEARCH;           // 搜索框被点击,搜索框不可输入的状态下会被触发
                // CommonTitleBar.ACTION_SEARCH_SUBMIT;    // 搜索框输入状态下,键盘提交触发，此时，extra为输入内容
                // CommonTitleBar.ACTION_SEARCH_VOICE;     // 语音按钮被点击
                // CommonTitleBar.ACTION_SEARCH_DELETE;    // 搜索删除按钮被点击
                // CommonTitleBar.ACTION_CENTER_TEXT;      // 中间文字点击
            }
        });

        mTvJoinJijinchijine = (TextView) findViewById(R.id.tv_join_jijinchijine);
        mTvJoinJijinchijine.setOnClickListener(this);
        mRlJoinR1 = (RelativeLayout) findViewById(R.id.rl_join_r1);
        mTvJoinFangan99 = (TextView) findViewById(R.id.tv_join_fangan99);
        mTvJoinFangan99.setOnClickListener(this);
        mTvJoinFangan199 = (TextView) findViewById(R.id.tv_join_fangan199);
        mTvJoinFangan199.setOnClickListener(this);
        mTvJoinFangan399 = (TextView) findViewById(R.id.tv_join_fangan399);
        mTvJoinFangan399.setOnClickListener(this);
        mTvJoinFangan599 = (TextView) findViewById(R.id.tv_join_fangan599);
        mTvJoinFangan599.setOnClickListener(this);
        mTvJoinFangan799 = (TextView) findViewById(R.id.tv_join_fangan799);
        mTvJoinFangan799.setOnClickListener(this);
        mTvJoinXuanze99 = (SuperTextView) findViewById(R.id.tv_join_xuanze99);
        mTvJoinXuanze99.setOnClickListener(this);
        mTvJoinXuanze199 = (SuperTextView) findViewById(R.id.tv_join_xuanze199);
        mTvJoinXuanze199.setOnClickListener(this);
        mTvJoinXuanze399 = (SuperTextView) findViewById(R.id.tv_join_xuanze399);
        mTvJoinXuanze399.setOnClickListener(this);
        mTvJoinXuanze599 = (SuperTextView) findViewById(R.id.tv_join_xuanze599);
        mTvJoinXuanze599.setOnClickListener(this);
        mTvJoinXuanze799 = (SuperTextView) findViewById(R.id.tv_join_xuanze799);
        mTvJoinXuanze799.setOnClickListener(this);
        mCbJoinTongyi = (CheckBox) findViewById(R.id.cb_join_tongyi);
        mCbJoinTongyi.setOnClickListener(this);
        mTvJoinXuzhi = (TextView) findViewById(R.id.tv_join_xuzhi);
        mTvJoinXuzhi.setOnClickListener(this);
        mTvJoinTiaokuan = (TextView) findViewById(R.id.tv_join_tiaokuan);
        mTvJoinTiaokuan.setOnClickListener(this);
        mTvJoinXieyi = (TextView) findViewById(R.id.tv_join_xieyi);
        mTvJoinXieyi.setOnClickListener(this);
        mIvJoinContent = (ImageView) findViewById(R.id.iv_join_content);
        mTvJoinYixuanjine = (TextView) findViewById(R.id.tv_join_yixuanjine);
        mTvJoinYixuanjine.setOnClickListener(this);
        mTvJoinWoyaojiaru = (SuperTextView) findViewById(R.id.tv_join_woyaojiaru);
        mTvJoinWoyaojiaru.setOnClickListener(this);
        mRlJoinR2 = (RelativeLayout) findViewById(R.id.rl_join_r2);
        setText();
        getDefault();


    }
    private  void  setText(){
        // 响应点击事件的话必须设置以下属性
        mTvJoinFangan99.setMovementMethod(LinkMovementMethod.getInstance());
        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                .append("99元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过\n")
                .append("800元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")

                .into(mTvJoinFangan99);
        mTvJoinFangan199.setMovementMethod(LinkMovementMethod.getInstance());
        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                .append("199元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过\n")
                .append("1000元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")

                .into(mTvJoinFangan199);
        mTvJoinFangan399.setMovementMethod(LinkMovementMethod.getInstance());
        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                .append("399元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过\n")
                .append("1500元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")

                .into(mTvJoinFangan399);
        mTvJoinFangan599.setMovementMethod(LinkMovementMethod.getInstance());
        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                .append("599元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过\n")
                .append("3000元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")

                .into(mTvJoinFangan599);
        mTvJoinFangan799.setMovementMethod(LinkMovementMethod.getInstance());
        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                .append("799元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过")
                .append("6000元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")

                .into(mTvJoinFangan799);

    }

    private void getDefault(){
        mTvJoinXuanze99.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze99.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze99.setStrokeColor(getResources().getColor(R.color.a));

        mTvJoinXuanze199.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze199.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze199.setStrokeColor(getResources().getColor(R.color.a));

        mTvJoinXuanze399.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze399.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze399.setStrokeColor(getResources().getColor(R.color.a));

        mTvJoinXuanze599.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze599.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze599.setStrokeColor(getResources().getColor(R.color.a));

        mTvJoinXuanze799.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze799.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze799.setStrokeColor(getResources().getColor(R.color.a));


    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
            switch (v.getId()) {
                default:
                    break;

                case R.id.tv_join_xuanze99:
                    getDefault();
                    mTvJoinXuanze99.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze99.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze99.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥99");

                    break;
                case R.id.tv_join_xuanze199:
                    getDefault();
                    mTvJoinXuanze199.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze199.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze199.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥199");
                    break;
                case R.id.tv_join_xuanze399:
                    getDefault();
                    mTvJoinXuanze399.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze399.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze399.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥399");
                    break;
                case R.id.tv_join_xuanze599:
                    getDefault();
                    mTvJoinXuanze599.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze599.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze599.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥599");
                    break;
                case R.id.tv_join_xuanze799:
                    getDefault();
                    mTvJoinXuanze799.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze799.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze799.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥799");
                    break;
                case R.id.cb_join_tongyi:
                    break;
                case R.id.tv_join_xuzhi:
                    break;
                case R.id.tv_join_tiaokuan:
                    break;
                case R.id.tv_join_xieyi:
                    break;
                case R.id.tv_join_yixuanjine:
                    break;
                case R.id.tv_join_woyaojiaru:
                    intent=new Intent(JoinActivity.this,SelectCarActivity.class);
                    break;
            }
            if (intent!=null){
                startActivity(intent);
            }

        }else{
            BaseToast.normal("点得太快了");
            return;
        }
    }
}
