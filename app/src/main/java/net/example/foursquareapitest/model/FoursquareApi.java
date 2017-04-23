package net.example.foursquareapitest.model;

import net.example.foursquareapitest.model.entities.VenuesResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Carlo on 23/04/2017.
 */

public interface FoursquareApi {

    String BASE_URL = "https://api.foursquare.com/v2/";
    String CLIENT_ID = "SOPAE2CAB1NIXOF45DRE3K3130IXJ0AF4XZKWOCEXIGHZ1BR";
    String CLIENT_SECRET = "HWAPQWHJKY5C4AM1JHIZC2ETEJC1OS3VFIUEGYYAITEWC5PI";

    @GET("venues/search?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=20170423")
    Observable<VenuesResponse> searchVenues(@Query("near") String queryString);
}
