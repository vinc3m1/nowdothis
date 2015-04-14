package com.makeramen.nowdothis;

import com.makeramen.nowdothis.dagger.PerApp;
import com.makeramen.nowdothis.ui.EditListFragment;
import com.makeramen.nowdothis.ui.NowDoThisActivity;
import com.makeramen.nowdothis.ui.TodoFragment;
import dagger.Component;

@PerApp
@Component(
    modules = {
        NowDoThisModule.class
    }
)
public interface NowDoThisComponent {
  public void inject(NowDoThisActivity nowDoThisActivity);

  public void inject(EditListFragment editListFragment);

  public void inject(TodoFragment todoFragment);
}
