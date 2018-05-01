package com.gomo.learndemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.gomo.learndemo.bean.MessageBean;
import com.gomo.learndemo.bean.User;
import com.gomo.learndemo.http.HttpApiManager;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by deeper on 2018/5/1.
 *
 * 数据仓库：封装网络请求或者本地数据库缓存
 *
 * 1. ViewModel中调用，这里一般返回一个LiveData（同步）或者是Observable（异步）
 * 2. Observable（异步）情况下{1. 返回Observable在ViewModuel中处理  2. 传入参数liveData直接在异步回调中设置setValue返回void}
 * 3. 尽量不要在Fragment或者Activity中处理逻辑：如fragment中处理 Observable（don't do this）
 * 4. 其实建议把本地和网络仓库分隔开逻辑会更清晰：Google MVP模式中的做法。
 */

public class DataRepository {

    private HttpApiManager mHttpApiManager;
    private AppDatabase mAppDb;

    public static DataRepository getInstance() {
        return SingleHolder.sInstance;
    }

    private static class SingleHolder {
        private static final DataRepository sInstance = new DataRepository();
    }

    private DataRepository() {
        mHttpApiManager = HttpApiManager.getInstance();
        mAppDb = DatabaseCreator.getInstance().getAppDatabase();
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

    public LiveData<User> loadUser(int userId) {
        LiveData<User> userData = mAppDb.getUserDao().getUser(userId);
        return userData;
    }

    public Observable<Boolean> initLearnDemoData() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
                User user = new User("xiaoming", "love123");
                mAppDb.getUserDao().insert(user);
                e.onNext(Boolean.TRUE);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
