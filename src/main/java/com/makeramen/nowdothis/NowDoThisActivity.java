package com.makeramen.nowdothis;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import javax.inject.Inject;

import static com.makeramen.nowdothis.NowDoThisApp.KEY_TODOS;

public class NowDoThisActivity extends FragmentActivity {
  @Inject SharedPreferences sharedPreferences;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    NowDoThisApp.inject(this);

    if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
      Fragment fragment = TextUtils.isEmpty(sharedPreferences.getString(KEY_TODOS, "").trim()) ?
          new EditListFragment() : new TodoFragment();

      getSupportFragmentManager().beginTransaction()
          .replace(android.R.id.content, fragment)
          .commit();
    }
  }
}
