package org.nhlstenden.jabberpoint.slide.item;

public class TextItemFactory extends SlideItemFactory {

  @Override
  public SlideItem createSlideItem(int level, String value) {
    return new TextItem(level, value);
  }
}
