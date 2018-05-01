package com.gomo.learndemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.gomo.learndemo.DataRepository;

import io.reactivex.functions.Consumer;

/**
 * Created by deeper on 2018/5/1.
 */

public class StartViewModel extends ViewModel {

    private MutableLiveData<Boolean> mHadInitObserver = new MutableLiveData<>();

    public StartViewModel() {
        DataRepository.getInstance().initLearnDemoData()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean hasInit) throws Exception {
                        mHadInitObserver.setValue(hasInit);
                    }
                });
    }

    public LiveData<Boolean> getInitObserver() {
        return mHadInitObserver;
    }
}
