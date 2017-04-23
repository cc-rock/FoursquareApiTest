package net.example.foursquareapitest.model.entities;

/**
 * Created by Carlo on 23/04/2017.
 */

public class VenuesResponse {

    private VenueList response;

    public VenuesResponse(VenueList response) {
        this.response = response;
    }

    public VenueList getVenues() {
        return response;
    }
}
