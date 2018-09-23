package net.github.finduser.search;

import net.github.finduser.models.SearchResponse;
import net.github.finduser.networking.NetworkError;
import net.github.finduser.networking.Service;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class SearchPresenter {
    private final Service service;
    private final SearchView view;
    private CompositeSubscription subscriptions;

    public SearchPresenter(Service service, SearchView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList(String user) {
        view.showWait();

        Subscription subscription = service.getUserList(user,new Service.NetworkApiCallback() {
            @Override
            public void onSuccess(SearchResponse userListResponse) {
                view.removeWait();
                view.getUserListSuccess(userListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
