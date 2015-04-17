package com.makeramen.nowdothis.ui.imgur;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.makeramen.nowdothis.R;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

public class ImgurUploadActivity extends Activity {

  private static final int REQUEST_CODE_IMG_PICK = 13554;

  private static final String DATA_IMG_PATH = "path";

  @InjectView(R.id.image) ImageView imageView;
  @InjectView(R.id.btn_choose) Button chooseButton;
  @InjectView(R.id.btn_upload) Button uploadButton;
  @Inject Picasso picasso;
  @Nullable Uri imgPath;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState != null && savedInstanceState.containsKey(DATA_IMG_PATH)) {
      imgPath = savedInstanceState.getParcelable(DATA_IMG_PATH);
      picasso.load(imgPath).into(imageView);
      uploadButton.setEnabled(true);
      chooseButton.setVisibility(View.GONE);
    } else {
      uploadButton.setEnabled(false);
      chooseButton.setVisibility(View.VISIBLE);
    }

    setContentView(R.layout.activity_imgur_upload);
    ButterKnife.inject(this);
  }

  @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelable(DATA_IMG_PATH, imgPath);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case REQUEST_CODE_IMG_PICK:
        if (resultCode == RESULT_OK) {
          imgPath = data.getData();
          picasso.load(imgPath).into(imageView);
        }
        return;
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  @OnClick({R.id.image, R.id.btn_choose}) void onChooseClick() {
    startActivityForResult(new Intent(Intent.ACTION_PICK).setType("image/"), REQUEST_CODE_IMG_PICK);
  }

  @OnClick({R.id.btn_upload}) void onUploadClick() {
    // do upload
  }
}
