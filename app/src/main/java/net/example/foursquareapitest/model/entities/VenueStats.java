package net.example.foursquareapitest.model.entities;

/**
 * Created by Carlo on 23/04/2017.
 */

public class VenueStats {

    private int checkinsCount;
    private int usersCount;
    private int tipCount;

    public VenueStats(int checkinsCount, int usersCount, int tipCount) {
        this.checkinsCount = checkinsCount;
        this.usersCount = usersCount;
        this.tipCount = tipCount;
    }

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public int getTipCount() {
        return tipCount;
    }
}
