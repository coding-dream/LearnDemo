package com.gomo.learndemo;

import android.arch.lifecycle.MutableLiveData;

import com.gomo.learndemo.bean.MessageBean;
import com.gomo.learndemo.http.HttpApiManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by deeper on 2018/5/1.
 */

public class DataRepository {

    private HttpApiManager mHttpApiManager;
    // private AppDatabase mAppDb;

    public static DataRepository getInstance() {
        return SingleHolder.sInstance;
    }

    private static class SingleHolder {
        private static final DataRepository sInstance = new DataRepository();
    }

    private DataRepository() {
        mHttpApiManager = HttpApiManager.getInstance();
        // mAppDb = DatabaseCreator.getInstance().getAppDatabase();
    }

    public void getProjectList(final MutableLiveData<MessageBean> liveData, String page, int cid) {
        mHttpApiManager.getProjectList(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MessageBean>() {
                    @Override
                    public void accept(MessageBean messageBean) throws Exception {
                        MessageBean message = new MessageBean("I'm from server test");
                        liveData.setValue(message);
                    }
                });
    }
}