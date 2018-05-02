package com.gomo.learndemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gomo.learndemo.bean.User;
import com.gomo.learndemo.util.LogUtils;
import com.gomo.learndemo.viewmodel.MessageViewModel;
import com.gomo.learndemo.viewmodel.StartViewModel;
import com.gomo.learndemo.viewmodel.UserViewModel;

import java.util.List;
import java.util.Random;

public class RoomTestActivity extends AppCompatActivity implements View.OnClickListener {

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_test);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        // getUserObserver只在Activity初始化时调用，其他情况是数据库变动会被通知到。
        userViewModel.getUserObserver(2).observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                // 每次onChanged都是userId为2的这个user对象。
                LogUtils.d("onChange");
                if (user != null) {
                    LogUtils.d(user.toString());
                }
            }
        });
        userViewModel.getUserListObserver().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (users != null) {
                    LogUtils.d(users.toString());
                }
            }
        });
        initView();
    }

    private void initView() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Random random = new Random();
                User user = new User("dongdong", "dong" + random.nextInt(100));
                userViewModel.save(user);
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
            default:
                break;
        }
    }
}
