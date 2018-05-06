package com.gomo.learndemo.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gomo.learndemo.R;

import org.reactivestreams.Subscription;

public class RxActivity extends AppCompatActivity implements View.OnClickListener {

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        initView();

    }

    private void initView() {
        findViewById(R.id.btn_rx_create).setOnClickListener(this);
        findViewById(R.id.btn_rx_flowable).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rx_create:
                RxSample.getInstance().create();
                break;
            case R.id.btn_rx_flowable:
                RxSample.getInstance().flowable();
                break;
            default:
                break;
        }
    }
}
