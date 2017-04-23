package net.example.foursquareapitest.venuessearch;

import net.example.foursquareapitest.model.FoursquareApi;
import net.example.foursquareapitest.model.entities.VenuesResponse;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Carlo on 23/04/2017.
 */

public class VenuesSearchPresenter {

    private FoursquareApi foursquareApi;

    private VenuesSearchView view;

    private Subscription ongoingCall;

    public VenuesSearchPresenter(FoursquareApi foursquareApi) {
        this.foursquareApi = foursquareApi;
    }

    public void initialise() {
    }


    public void searchRequested(String query) {
        if (ongoingCall != null) {
            ongoingCall.unsubscribe();
        }
        if (query == null || query.equals("")) {
            if (isViewBound()) {
                view.hideLoading();
                view.showEmptySearchStringError();
            }
            return;
        }
        if (isViewBound()) {
            view.showLoading();
        }
        ongoingCall = foursquareApi.searchVenues(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                new Action1<VenuesResponse>() {
                    @Override
                    public void call(VenuesResponse response) {
                        if (isViewBound()) {
                            view.hideLoading();
                            view.showSearchResults(response.getVenues());
                        }
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (isViewBound()) {
                            view.hideLoading();
                            view.showSearchError();
                        }
                    }
                }
        );
    }

    public void bindView(VenuesSearchView view) {
        this.view = view;
    }

    public void unbindView() {
        this.view = null;
    }

    private boolean isViewBound() {
        return view != null;
    }
}
