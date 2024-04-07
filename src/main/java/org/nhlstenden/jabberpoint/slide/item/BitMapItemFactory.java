package org.nhlstenden.jabberpoint.slide.item;

public class BitMapItemFactory extends SlideItemFactory {

  @Override
  public SlideItem createSlideItem(int level, String value) {
    return new BitmapItem(level, value);
  }
}
