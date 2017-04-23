package net.example.foursquareapitest.venuessearch;

import net.example.foursquareapitest.model.FoursquareApi;
import net.example.foursquareapitest.model.entities.VenuesResponse;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Carlo on 23/04/2017.
 */

public class VenuesSearchPresenter {

    private FoursquareApi foursquareApi;

    private Scheduler ioScheduler;
    private Scheduler mainThreadScheduler;

    private VenuesSearchView view;

    private Subscription ongoingCall;

    public VenuesSearchPresenter(FoursquareApi foursquareApi, Scheduler ioScheduler, Scheduler mainThreadScheduler) {
        this.foursquareApi = foursquareApi;
        this.ioScheduler = ioScheduler;
        this.mainThreadScheduler = mainThreadScheduler;
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
                .subscribeOn(ioScheduler)
                .observeOn(mainThreadScheduler)
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
