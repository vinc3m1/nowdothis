package com.makeramen.nowdothis;

import android.app.Application;
import android.net.NetworkRequest;
import com.makeramen.nowdothis.dagger.PerApp;
import com.makeramen.nowdothis.data.DataModule;
import com.makeramen.nowdothis.data.NetworkModule;
import com.makeramen.nowdothis.ui.EditListFragment;
import com.makeramen.nowdothis.ui.NowDoThisActivity;
import com.makeramen.nowdothis.ui.TodoFragment;
import dagger.Component;

@PerApp
@Component(
    modules = {
        NowDoThisModule.class,
        DataModule.class,
        NetworkModule.class
    }
)
public interface NowDoThisAppComponent {
  public void inject(NowDoThisActivity nowDoThisActivity);

  public void inject(EditListFragment editListFragment);

  public void inject(TodoFragment todoFragment);

  public Application application();
}
