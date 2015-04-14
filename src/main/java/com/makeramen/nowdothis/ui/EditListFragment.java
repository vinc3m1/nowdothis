package com.makeramen.nowdothis.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.makeramen.nowdothis.PreferenceHelper;
import com.makeramen.nowdothis.R;
import javax.inject.Inject;

public class EditListFragment extends Fragment {

  InputMethodManager imm;
  @Inject PreferenceHelper preferenceHelper;
  @InjectView(R.id.editor) EditText editor;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_editlist, container, false);
    ButterKnife.inject(this, view);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    NowDoThisApp.inject(this, getActivity());
    imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
  }

  @Override public void onResume() {
    super.onResume();
    editor.setText(TextUtils.join("\n", preferenceHelper.getTodos()));
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
    preferenceHelper.saveTodos(editor.getText().toString());
  }

  @OnClick(R.id.btn_ready) void readyClick() {
    getFragmentManager().beginTransaction()
        .replace(getId(), new TodoFragment())
        .commit();
  }
}
