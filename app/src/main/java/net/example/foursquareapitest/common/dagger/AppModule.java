package net.example.foursquareapitest.common.dagger;

import net.example.foursquareapitest.venuessearch.VenuesSearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Carlo on 23/04/2017.
 */
@Module
public class AppModule {

    @Provides
    VenuesSearchPresenter provideVenuesSearchPresenter() {
        return new VenuesSearchPresenter();
    }

}
