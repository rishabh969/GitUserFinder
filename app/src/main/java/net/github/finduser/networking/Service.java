package net.github.finduser.networking;


import android.util.Log;
import net.github.finduser.models.SearchResponse;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Rishabh.
 */
public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getUserList(String user,final NetworkApiCallback callback) {

            return networkService.getUserList(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends SearchResponse>>() {
                    @Override
                    public Observable<? extends SearchResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                }).subscribe(new Observer<SearchResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("rishabh1","");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("rishabh2","rishabh2");
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(SearchResponse response) {
                        Log.d("rishabh3","rishabh3");
                        callback.onSuccess(response);
                    }
                });
    }

    public interface NetworkApiCallback{
        void onSuccess(SearchResponse cityListResponse);

        void onError(NetworkError networkError);
    }

}
