package com.gomo.learndemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.gomo.learndemo.DataRepository;
import com.gomo.learndemo.bean.User;
import com.gomo.learndemo.util.LogUtils;

import java.util.List;

import io.reactivex.functions.Consumer;

public class UserViewModel extends ViewModel {

    private LiveData<User> mUserObserver = new MutableLiveData<User>();

    private LiveData<List<User>> mUserListObserver = new MutableLiveData<List<User>>();

    public LiveData<User> getUserObserver(int userId) {
        LogUtils.d("UserViewModel.getUserObserver");
        mUserObserver = DataRepository.getInstance().loadUser(userId);
        return mUserObserver;
    }

    public LiveData<List<User>> getUserListObserver() {
        LogUtils.d("UserViewModel.getUserListObserver");
        mUserListObserver = DataRepository.getInstance().loadAllUser();
        return mUserListObserver;
    }

    public void save(User user) {
        // 数据库Room插入操作需要异步：delete => DataRepository.getInstance().getmAppDb().getUserDao().insert(user);
        // 查询操作返回LiveData<T>格式的可以UI线程查询

        DataRepository.getInstance().initLearnDemoData(user)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean success) throws Exception {
                        LogUtils.d("save " + success);
                    }
                });
    }
}
