package com.makeramen.nowdothis.ui.imgur;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.makeramen.nowdothis.NowDoThisApp;
import com.makeramen.nowdothis.R;
import com.makeramen.nowdothis.data.imgur.ImageUpload;
import com.makeramen.nowdothis.data.imgur.ImgurApi;
import com.makeramen.nowdothis.data.imgur.Responses;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ImgurUploadActivity extends Activity {

  private static final int REQUEST_CODE_IMG_PICK = 13554;

  private static final String DATA_IMG_PATH = "path";
  public static final String EXTRA_IMG_URL = "url";

  @InjectView(R.id.image) ImageView imageView;
  @InjectView(R.id.btn_choose) Button chooseButton;
  @InjectView(R.id.btn_upload) Button uploadButton;
  @Inject Picasso picasso;
  @Inject ImgurApi mImgurApi;
  @Nullable Uri imgUri;

  ImgurActivityComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_imgur_upload);
    ButterKnife.inject(this);

    component = DaggerImgurActivityComponent.builder()
        .nowDoThisAppComponent(NowDoThisApp.getComponent(this))
        // optional
        //.imgurModule(new ImgurModule())
        .build();

    component.inject(this);

    if (savedInstanceState != null && savedInstanceState.containsKey(DATA_IMG_PATH)) {
      imgUri = savedInstanceState.getParcelable(DATA_IMG_PATH);
    }
    updateViewState();
  }

  private void updateViewState() {
    if (imgUri != null) {
      picasso.load(imgUri).into(imageView);
      uploadButton.setEnabled(true);
      chooseButton.setVisibility(View.GONE);
    } else {
      uploadButton.setEnabled(false);
      chooseButton.setVisibility(View.VISIBLE);
    }
  }

  @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelable(DATA_IMG_PATH, imgUri);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case REQUEST_CODE_IMG_PICK:
        if (resultCode == RESULT_OK) {
          imgUri = data.getData();
          updateViewState();
        }
        return;
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  @OnClick({ R.id.image, R.id.btn_choose }) void onChooseClick() {
    startActivityForResult(
        new Intent(Intent.ACTION_PICK).setType("image/"),
        //new Intent(Intent.ACTION_OPEN_DOCUMENT)
        //    .addCategory(Intent.CATEGORY_OPENABLE)
        //    .setType("image/"),
        REQUEST_CODE_IMG_PICK);
  }

  @OnClick({ R.id.btn_upload }) void onUploadClick() {
    final ProgressDialog dialog = ProgressDialog.show(this, null, "Uploading...", true, false);
    mImgurApi.uploadImage(new ImageUpload(getContentResolver(), imgUri),
        new Callback<Responses.ImageResponse>() {
          @Override public void success(Responses.ImageResponse imageResponse, Response response) {
            dialog.dismiss();
            setResult(RESULT_OK, new Intent().putExtra(EXTRA_IMG_URL, imageResponse.data.link));
            finish();
          }

          @Override public void failure(RetrofitError error) {
            dialog.dismiss();
            Toast.makeText(ImgurUploadActivity.this, "Error uploading image: " + error.getMessage(),
                Toast.LENGTH_SHORT).show();
          }
        });
  }
}
