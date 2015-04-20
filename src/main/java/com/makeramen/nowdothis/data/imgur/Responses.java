package com.makeramen.nowdothis.data.imgur;

public class Responses {

  public class ImageResponse {
    public Image data;
    public boolean success;
    public int status;
  }

  public class Image {
    public String id;
    public String title;
    public String description;
    public String type;
    public boolean animated;
    public int width;
    public int height;
    public int size;
    public String link;
  }
}
