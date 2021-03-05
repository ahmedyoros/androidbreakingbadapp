package com.eservices.breakingbad.data.di;

import android.content.Context;
import com.eservices.breakingbad.data.api.CharacterDisplayService;
import com.eservices.breakingbad.data.repository.characterdisplay.CharacterDisplayDataRepository;
import com.eservices.breakingbad.data.repository.characterdisplay.CharacterDisplayRepository;
import com.eservices.breakingbad.data.repository.characterdisplay.remote.CharacterDisplayRemoteDataSource;
import com.eservices.breakingbad.presentation.viewmodel.ViewModelFactory;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *A class for a fake dependencyInjection
 */
public class FakeDependencyInjection {

    private static CharacterDisplayService characterDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static CharacterDisplayRepository characterDisplayRepository;
    private static ViewModelFactory viewModelFactory;
    private static Context applicationContext;


    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getCharacterDisplayRepository());
        }
        return viewModelFactory;
    }


    public static CharacterDisplayRepository getCharacterDisplayRepository() {
        if (characterDisplayRepository == null) {
            characterDisplayRepository = new CharacterDisplayDataRepository(
                    new CharacterDisplayRemoteDataSource(getCharacterDisplayService()));
        }
        return characterDisplayRepository;
    }

    public static CharacterDisplayService getCharacterDisplayService() {
        if (characterDisplayService == null) {
            characterDisplayService = getRetrofit().create(CharacterDisplayService.class);
        }
        return characterDisplayService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.breakingbadapi.com/api/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

}
