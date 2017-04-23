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

    private String searchQuery;

    private FoursquareApi foursquareApi;

    private VenuesSearchView view;

    private Subscription ongoingCall;

    public VenuesSearchPresenter(FoursquareApi foursquareApi) {
        this.foursquareApi = foursquareApi;
    }

    public void initialise(String searchQuery) {
        this.searchQuery = searchQuery;
    }


    public void searchRequested(String query) {
        this.searchQuery = query;
        if (ongoingCall != null) {
            ongoingCall.unsubscribe();
        }
        if (query == null || query.equals("")) {
            if (view != null) {
                view.hideLoading();
                view.showEmptySearchStringError();
            }
            return;
        }
        if (view != null) {
            view.showLoading();
        }
        ongoingCall = foursquareApi.searchVenues(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                new Action1<VenuesResponse>() {
                    @Override
                    public void call(VenuesResponse response) {
                        if (view != null) {
                            view.hideLoading();
                            view.showSearchResults(response.getVenues());
                        }
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (view != null) {
                            view.hideLoading();
                            view.showSearchError();
                        }
                    }
                }
        );
    }

    public void setView(VenuesSearchView view) {
        this.view = view;
    }
}
