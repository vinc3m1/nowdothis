package com.makeramen.nowdothis;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import dagger.ObjectGraph;

public class NowDoThisApp extends Application {

  public static final String KEY_TODOS = "todos";

  private ObjectGraph objectGraph;

  @Override public void onCreate() {
    super.onCreate();
    objectGraph = ObjectGraph.create(new NowDoThisModule(this));
  }

  static void inject(Activity activity) {
    inject(activity, activity);
  }

  static void inject(Object o, Context context) {
    NowDoThisApp.get(context).objectGraph.inject(o);
  }

  static NowDoThisApp get(Context context) {
    return (NowDoThisApp) context.getApplicationContext();
  }
}
