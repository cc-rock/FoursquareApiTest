package net.example.foursquareapitest.venuessearch;

import net.example.foursquareapitest.model.entities.VenueList;

/**
 * Created by Carlo on 23/04/2017.
 */

public interface VenuesSearchView {

    void showLoading();

    void showEmptySearchStringError();

    void showSearchError();

    void showSearchResults(VenueList venueList);

    void hideLoading();
}
