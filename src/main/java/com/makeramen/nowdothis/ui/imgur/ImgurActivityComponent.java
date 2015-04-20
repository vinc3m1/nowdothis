package com.makeramen.nowdothis.ui.imgur;

import com.makeramen.nowdothis.NowDoThisAppComponent;
import com.makeramen.nowdothis.dagger.PerImgurActivity;
import com.makeramen.nowdothis.data.imgur.ImgurModule;
import dagger.Component;

@PerImgurActivity
@Component(
    dependencies = NowDoThisAppComponent.class,
    modules = {
        ImgurModule.class
    }
)
public interface ImgurActivityComponent {
  void inject(ImgurUploadActivity imgurUploadActivity);
}
