package com.makeramen.nowdothis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditListActivity extends Activity implements OnClickListener {

  EditText mEditText;
  Button saveButton;
  TextView cancelLink;
  SharedPreferences prefs;
  String catString;
  String[] item;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editlist);
    mEditText = (EditText) this.findViewById(R.id.editor);
    saveButton = (Button) this.findViewById(R.id.savebutton);
    cancelLink = (TextView) this.findViewById(R.id.cancellink);
    saveButton.setOnClickListener(this);
    cancelLink.setOnClickListener(this);

    prefs = this.getSharedPreferences(ItemViewActivity.PREFS_KEY, 0);
    catString = prefs.getString(ItemViewActivity.LIST_KEY, "take a nap\nbuy pen\norganize room");
    mEditText.setText(catString);
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    switch (id) {
      case R.id.savebutton:
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ItemViewActivity.LIST_KEY, mEditText.getText().toString());
        editor.commit();
      case R.id.cancellink:
        this.startActivity(new Intent(this, ItemViewActivity.class));
        this.finish();
        break;
    }
  }
}