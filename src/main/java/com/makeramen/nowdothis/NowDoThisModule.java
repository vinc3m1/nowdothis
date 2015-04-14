package com.makeramen.nowdothis;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.makeramen.nowdothis.ui.EditListFragment;
import com.makeramen.nowdothis.ui.NowDoThisActivity;
import com.makeramen.nowdothis.ui.TodoFragment;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(
    injects = {
        NowDoThisActivity.class,
        EditListFragment.class,
        TodoFragment.class,
    }
)
public class NowDoThisModule {

  static final String PREFS_DEFAULT = "nowdothis";

  final NowDoThisApp app;

  public NowDoThisModule(NowDoThisApp app) {
    this.app = app;
  }

  @Provides @Singleton NowDoThisApp provideNowDoThisApp() {
    return app;
  }

  @Provides @Singleton Application provideApplication(NowDoThisApp app) {
    return app;
  }

  @Provides @Singleton SharedPreferences provideSharedPrefs(Application app) {
    return app.getSharedPreferences(PREFS_DEFAULT, Context.MODE_PRIVATE);
  }

  @Provides @Singleton Gson provideGson() {
    return new Gson();
  }
}
