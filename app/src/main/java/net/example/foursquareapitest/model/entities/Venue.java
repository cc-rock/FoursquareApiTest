package net.example.foursquareapitest.model.entities;

/**
 * Created by Carlo on 23/04/2017.
 */

public class Venue {

    private String id;
    private String name;
    private VenueLocation location;
    private VenueStats stats;
    private int rating;

    public Venue(String id, String name, VenueLocation location, VenueStats stats, int rating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.stats = stats;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VenueLocation getLocation() {
        return location;
    }

    public VenueStats getStats() {
        return stats;
    }

    public int getRating() {
        return rating;
    }
}
