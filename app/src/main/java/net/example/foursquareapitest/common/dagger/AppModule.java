package net.example.foursquareapitest.common.dagger;

import net.example.foursquareapitest.model.FoursquareApi;
import net.example.foursquareapitest.venuessearch.VenuesSearchPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Carlo on 23/04/2017.
 */
@Module
public class AppModule {

    @Provides
    public VenuesSearchPresenter provideVenuesSearchPresenter(FoursquareApi foursquareApi,
                                                              @Named("io") Scheduler ioScheduler,
                                                              @Named("mainThread") Scheduler mainThreadScheduler) {
        return new VenuesSearchPresenter(foursquareApi, ioScheduler, mainThreadScheduler);
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

    @Provides
    @Named("io")
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named("mainThread")
    public Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
