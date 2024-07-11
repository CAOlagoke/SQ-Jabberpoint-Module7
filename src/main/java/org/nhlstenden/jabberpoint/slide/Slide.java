package org.nhlstenden.jabberpoint.slide;

import org.nhlstenden.jabberpoint.slide.item.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * A slide. This class has a drawing functionality.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Slide {
  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;
  protected String title;
  protected Vector<SlideItem> slideItems; // slide items are saved in a Vector

  public Slide(String title) {
    this.title = title;
    this.slideItems = new Vector<>();
  }

  // Add a slide item
  public void addSlideItem(SlideItem slideItem) {
    this.slideItems.addElement(slideItem);
  }

  // give the title of the slide
  public String getTitle() {
    return this.title;
  }

  // change the title of the slide
  public void setTitle(String title) {
    this.title = title;
  }

  // Create SlideItem.TextItem of String, and add the SlideItem.TextItem
  public void addTextItem(int level, String message) {
    SlideItemFactory textItemFactory = new TextItemFactory();
    this.addSlideItem(textItemFactory.createSlideItem(level, message));
  }

  public void addBitmapItem(int level, String imageName) {
    SlideItemFactory bitmapItemFactory = new BitMapItemFactory();
    this.addSlideItem(bitmapItemFactory.createSlideItem(level, imageName));
  }

  public SlideItem getSlideItem(int position) {
    return this.slideItems.elementAt(position);
  }

  // returns all SlideItems in a Vector
  public Vector<SlideItem> getSlideItems() {
    return this.slideItems;
  }

  // give the size of the org.nhlstenden.jabberpoint.slide.Slide
  public int getSizeOfSlideItems() {
    return this.slideItems.size();
  }

  // draw the slide
  public void draw(Graphics graphics, Rectangle area, ImageObserver view) {
    float scale = this.getScale(area);
    int xCoordinate = area.x;
    int yCoordinate = area.y;

    yCoordinate += this.drawTitle(graphics, xCoordinate, yCoordinate, view, scale);

    for (int i = 0; i < this.getSizeOfSlideItems(); i++) {
      SlideItem slideItem = this.getSlideItems().elementAt(i);

      int slideItemLevel = this.getLevel(slideItem);
      //   System.out.println("Text of slideItem, color" + i + ": " + getText(slideItem));
      Style style = Style.getStyle(slideItemLevel);
      slideItem.draw(xCoordinate, yCoordinate, scale, graphics, style, view);

      yCoordinate += slideItem.getBoundingBox(graphics, view, scale, style).height;
    }
  }

  public int drawTitle(Graphics g, int x, int y, ImageObserver view, float scale) {

    int defaultLevel = 0;
    SlideItemFactory textItemFactory = new TextItemFactory();
    Style style = Style.getStyle(defaultLevel);
    SlideItem slideItem = textItemFactory.createSlideItem(defaultLevel, this.getTitle());

    slideItem.draw(x, y, scale, g, style, view);

    return slideItem.getBoundingBox(g, view, scale, style).height;
  }

  public String getText(SlideItem slideItem) {
    String text = null;
    if (slideItem instanceof TextItem) {
      text = ((TextItem) slideItem).getText();
    } else if (slideItem instanceof BitmapItem) {
      text = ((BitmapItem) slideItem).getImageName();
    }
    return text;
  }

  public int getLevel(SlideItem slideItem) {

    return slideItem.getLevel();
  }

  // Give the scale for drawing
  private float getScale(Rectangle area) {
    return Math.min(
        ((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
  }
}
