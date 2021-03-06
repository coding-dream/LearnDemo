package com.gomo.learndemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gomo.learndemo.bean.MessageBean;
import com.gomo.learndemo.util.LogUtils;
import com.gomo.learndemo.viewmodel.MessageViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        LogUtils.d("initData");
        MessageViewModel viewModel = ViewModelProviders.of(this).get(MessageViewModel.class);

        viewModel.getMessageObserver().observe(this, new Observer<MessageBean>() {
            @Override
            public void onChanged(@Nullable MessageBean messageBean) {
                if (messageBean != null) {
                    LogUtils.d(messageBean.toString());
                }
            }
        });
        viewModel.setHoroscopeId(2);
        viewModel.getProjectList();
    }
}
