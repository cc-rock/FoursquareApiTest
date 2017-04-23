package net.example.foursquareapitest.common.dagger;

import net.example.foursquareapitest.model.FoursquareApi;
import net.example.foursquareapitest.venuessearch.VenuesSearchPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Carlo on 23/04/2017.
 */
@Module
public class AppModule {

    @Provides
    public VenuesSearchPresenter provideVenuesSearchPresenter(FoursquareApi foursquareApi) {
        return new VenuesSearchPresenter(foursquareApi);
    }

    @Provides
    public FoursquareApi provideFoursquareApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FoursquareApi.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(FoursquareApi.class);
    }

}
