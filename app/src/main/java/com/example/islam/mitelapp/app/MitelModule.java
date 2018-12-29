package com.example.islam.mitelapp.app;

import android.app.Application;
import android.content.Context;

import com.example.islam.mitelapp.data.MitelRepository;
import com.example.islam.mitelapp.data.MitelRepositoryImp;
import com.example.islam.mitelapp.data.local.SharedPreference;
import com.example.islam.mitelapp.data.local.SharedPreferenceImp;
import com.example.islam.mitelapp.data.remote.MiletRemoteRepo;
import com.example.islam.mitelapp.data.remote.ServiceGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */
@Module
public class MitelModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    MiletRemoteRepo provideRemoteDataSource() {
        return ServiceGenerator.createService(MiletRemoteRepo.class);
    }

    @Singleton
    @Provides
    SharedPreference provideSharedPreferenceSource(Application application) {
        return new SharedPreferenceImp(application.getApplicationContext());
    }

    @Singleton
    @Provides
    MitelRepository provideMitellRepository(Application application, MiletRemoteRepo miletRemoteRepo, SharedPreference sharedPreference) {
        return new MitelRepositoryImp(application, miletRemoteRepo, sharedPreference);
    }

}
