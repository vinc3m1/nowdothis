package com.makeramen.nowdothis.data.imgur;

import android.content.ContentResolver;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class ImageUpload implements TypedInput, TypedOutput {

  private final ContentResolver mContentResolver;
  private final Uri mUri;

  public ImageUpload(ContentResolver contentResolver, Uri uri) {
    mContentResolver = contentResolver;
    mUri = uri;
  }

  @Override public String fileName() {
    String path = mUri.getPath();
    return path.substring(path.lastIndexOf('/') + 1);
  }

  @Override public String mimeType() {
    return mContentResolver.getType(mUri);
  }

  @Override public long length() {
    return -1;
  }

  @Override public void writeTo(OutputStream out) throws IOException {
    copy(in(), out);
  }

  @Override public InputStream in() throws IOException {
    return mContentResolver.openInputStream(mUri);
  }

  // from commons-io
  public static long copy(InputStream input, OutputStream output)
      throws IOException {
    byte[] buffer = new byte[1024 * 4];
    long count = 0;
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
      count += n;
    }
    return count;
  }
}
