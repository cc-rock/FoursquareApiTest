package net.example.foursquareapitest.venuessearch;

/**
 * Created by Carlo on 23/04/2017.
 */

public class VenuesSearchPresenter {

    private String searchQuery;

    public void initialise(String searchQuery) {
        this.searchQuery = searchQuery;
    }


    public void searchRequested(String query) {
        this.searchQuery = query;
    }
}
