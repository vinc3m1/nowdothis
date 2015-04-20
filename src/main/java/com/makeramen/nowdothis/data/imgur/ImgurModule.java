package com.makeramen.nowdothis.data.imgur;

import com.google.gson.Gson;
import com.makeramen.nowdothis.BuildConfig;
import com.makeramen.nowdothis.dagger.PerImgurActivity;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

import static retrofit.RestAdapter.LogLevel;

@Module
public class ImgurModule {

  @Provides @PerImgurActivity Converter provideConverter(Gson gson) {
    return new GsonConverter(gson);
  }

  @Provides @PerImgurActivity Client provideClient(OkHttpClient okHttpClient) {
    return new OkClient(okHttpClient);
  }

  @Provides @PerImgurActivity Endpoint provideEndpoint() {
    return Endpoints.newFixedEndpoint("https://api.imgur.com/3/");
  }

  @Provides @PerImgurActivity RestAdapter provideRestAdapter(Endpoint endpoint, Converter converter,
      Client client) {
    return new RestAdapter.Builder()
        .setEndpoint(endpoint)
        .setClient(client)
        .setConverter(converter)
        .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
        .build();
  }

  @Provides @PerImgurActivity ImgurApi provideImgurApi(RestAdapter restAdapter) {
    return restAdapter.create(ImgurApi.class);
  }
}
