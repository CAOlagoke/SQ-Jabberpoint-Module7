package org.nhlstenden.jabberpoint.slide.item;

public abstract class SlideItemFactory {

  public abstract SlideItem createSlideItem(int level, String value);
}
