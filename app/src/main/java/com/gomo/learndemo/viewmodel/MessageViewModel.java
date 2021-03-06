package com.gomo.learndemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.gomo.learndemo.DataRepository;
import com.gomo.learndemo.bean.MessageBean;
import com.gomo.learndemo.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MessageViewModel extends ViewModel {

    private final MutableLiveData<MessageBean> mMessageObserver = new MutableLiveData<>();

    public LiveData<MessageBean> getMessageObserver() {
        return mMessageObserver ;
    }

    public void setHoroscopeId(int horoscopeId) {
        List<MessageBean> characters = new ArrayList<>();
        characters.add(new MessageBean("body1"));
        characters.add(new MessageBean("body2"));
        characters.add(new MessageBean("body3"));
        characters.add(new MessageBean("body4"));

        mMessageObserver.setValue(characters.get(horoscopeId));
    }

    public void getProjectList() {
        DataRepository.getInstance().getProjectList(mMessageObserver , "1", 294);
    }

}
