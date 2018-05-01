package com.gomo.learndemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gomo.learndemo.bean.MessageBean;
import com.gomo.learndemo.viewmodel.MessageViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        final MessageViewModel viewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        viewModel.getCharacterObserver().observe(this, new Observer<MessageBean>() {
            @Override
            public void onChanged(@Nullable MessageBean messageBean) {
                if (messageBean != null) {
                    System.out.println("=========> " + messageBean);
                }
            }
        });
        viewModel.setHoroscopeId(2);

        viewModel.getProjectList();
    }
}
