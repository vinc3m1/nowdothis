package com.makeramen.nowdothis.data;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.makeramen.nowdothis.dagger.PerApp;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;

@PerApp
public class TodoStorage {

  public static final String KEY_TODOS = "todos";

  final Gson gson;
  final SharedPreferences prefs;

  @Inject
  public TodoStorage(SharedPreferences prefs, Gson gson) {
    this.prefs = prefs;
    this.gson = gson;
  }

  public @NonNull String[] getTodos() {
    try {
      return gson.fromJson(prefs.getString(KEY_TODOS, "[]"), String[].class);
    } catch (Exception e) {
      return new String[0];
    }
  }

  public void saveTodos(@NonNull String string) {
    String[] dirty = string.split("\n");
    ArrayList<String> clean = new ArrayList<>(dirty.length);
    for (String s : dirty) {
      s = s.trim();
      if (!TextUtils.isEmpty(s)) {
        clean.add(s);
      }
    }

    prefs.edit()
        .putString(KEY_TODOS, gson.toJson(clean))
        .apply();
  }

  @Nullable public String popList() {
    String[] todos = getTodos();
    if (todos.length > 1) {
      todos = Arrays.copyOfRange(todos, 1, todos.length);
      saveTodos(TextUtils.join("\n", todos));
      return todos[0];
    } else {
      saveTodos("");
      return null;
    }
  }
}
