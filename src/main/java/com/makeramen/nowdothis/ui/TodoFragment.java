package com.makeramen.nowdothis.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.makeramen.nowdothis.NowDoThisApp;
import com.makeramen.nowdothis.R;
import com.makeramen.nowdothis.data.TodoStorage;
import javax.inject.Inject;

public class TodoFragment extends Fragment {

  @Inject TodoStorage todoStorage;
  @InjectView(R.id.itemtext) TextView itemText;
  @InjectView(R.id.btn_editlist) TextView editListBtn;
  @InjectView(R.id.btn_done) Button doneButton;

  @Override public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_todo, container, false);
    ButterKnife.inject(this, view);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    NowDoThisApp.getComponent(getActivity()).inject(this);
  }

  @Override public void onResume() {
    super.onResume();

    String[] todos = todoStorage.getTodos();
    updateUI(todos.length > 0 ? todos[0] : null);
  }

  void updateUI(@Nullable String todo) {
    if (todo != null) {
      if (!todo.endsWith(".")) { todo = todo + "."; }
      itemText.setText(todo);
      itemText.setTextColor(getResources().getColor(R.color.black));
    } else {
      itemText.setText(R.string.all_done);
      itemText.setTextColor(getResources().getColor(R.color.green));
      doneButton.setVisibility(View.INVISIBLE);
    }
  }

  @OnClick(R.id.btn_done) void doneClick() {
    updateUI(todoStorage.popList());
  }

  @OnClick(R.id.btn_editlist) void editList() {
    getFragmentManager().beginTransaction()
        .replace(getId(), new EditListFragment())
        .commit();
  }
}
