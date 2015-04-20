package com.makeramen.nowdothis.data.imgur;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedInput;

public interface ImgurApi {

  static final String CLIENT_ID = "5cd5768b524d0da";

  @Headers("Authorization: Client-ID " + CLIENT_ID)
  @POST("/image")
  public void uploadImage(
      @Body TypedInput typedInput,
      Callback<Responses.ImageResponse> callback
  );
}
