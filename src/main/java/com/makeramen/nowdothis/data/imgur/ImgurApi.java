package com.makeramen.nowdothis.data.imgur;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.mime.TypedFile;

public interface ImgurApi {

  static final String CLIENT_ID = "5cd5768b524d0da";

  @POST("/image")
  public void uploadImage(
      @Body TypedFile typedFile,
      Callback<String> callback
  );
}
