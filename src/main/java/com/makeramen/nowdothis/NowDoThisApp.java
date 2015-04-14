package com.makeramen.nowdothis;

import android.app.Application;
import android.content.Context;

public class NowDoThisApp extends Application {

  public static final String KEY_TODOS = "todos";

  private NowDoThisComponent component;

  @Override public void onCreate() {
    super.onCreate();

    component = DaggerNowDoThisComponent.builder()
        .nowDoThisModule(new NowDoThisModule(this))
        .build();
  }

  public static NowDoThisComponent getComponent(Context context) {
    return ((NowDoThisApp) context.getApplicationContext()).component;
  }
}
