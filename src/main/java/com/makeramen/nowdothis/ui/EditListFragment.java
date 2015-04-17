package com.makeramen.nowdothis.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.makeramen.nowdothis.NowDoThisApp;
import com.makeramen.nowdothis.R;
import com.makeramen.nowdothis.data.TodoStorage;
import com.makeramen.nowdothis.ui.imgur.ImgurUploadActivity;
import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

public class EditListFragment extends Fragment {

  static final int REQUEST_CODE_IMGUR = 3;

  InputMethodManager imm;
  @Inject TodoStorage todoStorage;
  @InjectView(R.id.editor) EditText editor;

  @Override public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_editlist, container, false);
    ButterKnife.inject(this, view);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    NowDoThisApp.getComponent(getActivity()).inject(this);
    imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
  }

  @Override public void onResume() {
    super.onResume();
    editor.setText(TextUtils.join("\n", todoStorage.getTodos()));
    editor.post(new Runnable() {
      @Override public void run() {
        editor.requestFocus();
        editor.setSelection(editor.length());
        imm.showSoftInput(editor, InputMethodManager.SHOW_IMPLICIT);
      }
    });
  }

  @Override public void onPause() {
    super.onPause();
    imm.hideSoftInputFromWindow(editor.getWindowToken(), 0);
    todoStorage.saveTodos(editor.getText().toString());
  }

  @OnClick(R.id.btn_ready) void readyClick() {
    getFragmentManager().beginTransaction()
        .replace(getId(), new TodoFragment())
        .commit();
  }

  @OnClick(R.id.btn_imgur) void imgurClick() {
    startActivityForResult(new Intent(getActivity(), ImgurUploadActivity.class),
        REQUEST_CODE_IMGUR);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case REQUEST_CODE_IMGUR:
        if (resultCode == RESULT_OK && data != null) {
          // do stuff
        }
        return;
    }
    super.onActivityResult(requestCode, resultCode, data);
  }
}
