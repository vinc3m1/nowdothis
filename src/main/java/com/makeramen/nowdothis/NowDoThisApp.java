package com.makeramen.nowdothis;

import android.app.Application;
import android.content.Context;

public class NowDoThisApp extends Application {

  private NowDoThisAppComponent component;

  @Override public void onCreate() {
    super.onCreate();

    component = DaggerNowDoThisAppComponent.builder()
        .nowDoThisModule(new NowDoThisModule(this))
        .build();
  }

  public static NowDoThisAppComponent getComponent(Context context) {
    return ((NowDoThisApp) context.getApplicationContext()).component;
  }
}
