package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.tool.interfaces.OnRepeatClickListener;

public class MutualEventsActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_events);
        ImageView imageView=findViewById(R.id.iv_a4);
        imageView.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                startActivity(new Intent(MutualEventsActivity.this,EventDetailsActivity.class));
            }
        });
    }
}
