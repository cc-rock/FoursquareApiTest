package net.example.foursquareapitest.model.entities;

/**
 * Created by Carlo on 23/04/2017.
 */

public class VenueLocation {

    private String address;
    private String crossStreet;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private double lat;
    private double lng;
    private double distance;

    public VenueLocation(String address, String crossStreet, String city, String state, String postalCode, String country, double lat, double lng, double distance) {
        this.address = address;
        this.crossStreet = crossStreet;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public double getDistance() {
        return distance;
    }
}
