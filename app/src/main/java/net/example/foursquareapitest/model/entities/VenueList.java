package net.example.foursquareapitest.model.entities;

import java.util.List;

/**
 * Created by Carlo on 23/04/2017.
 */

public class VenueList {

    private List<Venue> venues;

    public VenueList(List<Venue> venues) {
        this.venues = venues;
    }

    public List<Venue> getVenues() {
        return venues;
    }
}
