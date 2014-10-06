package com.makeramen.nowdothis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ItemViewActivity extends Activity implements OnClickListener {

  TextView itemText;
  TextView editList;
  Button doneButton;
  SharedPreferences prefs;
  String catstring;
  ArrayList<String> item;
  int itemIndex = 0;
  public static final String LIST_KEY = "list";
  public static final String PREFS_KEY = "nowdothis";

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_itemview);
    itemText = (TextView) this.findViewById(R.id.itemtext);
    doneButton = (Button) this.findViewById(R.id.donebutton);
    editList = (TextView) this.findViewById(R.id.editlist);
    doneButton.setOnClickListener(this);
    editList.setOnClickListener(this);

    prefs = this.getSharedPreferences(PREFS_KEY, 0);
    catstring = prefs.getString(LIST_KEY, "take a nap\nbuy pen\norganize room");
    String[] itemTemp = catstring.split("\n");

    item = new ArrayList<>();

    for (int i = 0; i < itemTemp.length; i++) {
      String temp = itemTemp[i].trim();
      if (temp.length() > 0) {
        item.add(temp);
      }
    }

    if (item.size() > 0) {
      itemText.setText(item.get(itemIndex) + ".");
    } else {
      itemIndex++;
      done();
    }
  }

  private void done() {
    itemIndex++;
    if (itemIndex < item.size()) {
      itemText.setText(item.get(itemIndex) + ".");
    } else {
      doneButton.setVisibility(View.INVISIBLE);
      itemText.setText(R.string.alldone);
      itemText.setTextColor(Color.rgb(35, 142, 35));
    }
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    switch (id) {
      case R.id.donebutton:
        done();
        break;
      case R.id.editlist:
        SharedPreferences.Editor editor = prefs.edit();
        // TODO concat stuff
        String listString = "";
        if (itemIndex < item.size()) {
          listString = item.get(itemIndex);

          for (int i = itemIndex + 1; i < item.size(); i++) {
            listString = listString + "\n" + item.get(i);
          }
        }

        editor.putString(ItemViewActivity.LIST_KEY, listString);
        editor.commit();
        this.startActivity(new Intent(this, EditListActivity.class));
        this.finish();
        break;
    }
  }
}