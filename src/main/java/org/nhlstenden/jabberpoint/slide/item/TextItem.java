package org.nhlstenden.jabberpoint.slide.item;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.*;
import org.nhlstenden.jabberpoint.slide.Slide;
import org.nhlstenden.jabberpoint.slide.Style;

/**
 * A text item.
 *
 * <p>A SlideItem.TextItem has drawingfunctionality.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class TextItem extends SlideItem {

  private final String text;

  public TextItem(int level, String text) {
    super(level);
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  //
  public AttributedString getAttributedString(Style style, float scale) {

    int beginIndex = 0;
    AttributedString attributedString = new AttributedString(this.getText());

    attributedString.addAttribute(
        TextAttribute.FONT, style.getFont(scale), beginIndex, this.text.length());

    return attributedString;
  }

  // give the bounding box of the item
  public Rectangle getBoundingBox(
      Graphics graphics, ImageObserver observer, float scale, Style style) {

    int boundingWidth = 0;
    int boundingHeight = (int) (style.getLeading() * scale);

    List<TextLayout> layouts = this.getLayouts(graphics, style, scale);
    Iterator<TextLayout> iterator = layouts.iterator();

    while (iterator.hasNext()) {
      TextLayout layout = iterator.next();
      Rectangle2D bounds = layout.getBounds();
      if (bounds.getWidth() > boundingWidth) {
        boundingWidth = (int) bounds.getWidth();
      }
      if (bounds.getHeight() > 0) {
        boundingHeight += bounds.getHeight();
      }

      boundingHeight += layout.getLeading() + layout.getDescent();
    }

    int xCoordinate = (int) (style.getIndent() * scale);
    int yCoordinate = 0;

    return new Rectangle(xCoordinate, yCoordinate, boundingWidth, boundingHeight);
  }

  // draw the item
  public void draw(
      int xCoordinate,
      int yCoordinate,
      float scale,
      Graphics graphics,
      Style style,
      ImageObserver imageObserver) {

    if (!this.textIsEmpty()) {

      List<TextLayout> layouts = this.getLayouts(graphics, style, scale);
      Graphics2D graphics2D = (Graphics2D) graphics;
      graphics2D.setColor(style.getColor());

      Point pen =
          new Point(
              xCoordinate + (int) (style.getIndent() * scale),
              yCoordinate + (int) (style.getLeading() * scale));

      Iterator<TextLayout> iterator = layouts.iterator();

      while (iterator.hasNext()) {
        TextLayout layout = iterator.next();
        pen.y += layout.getAscent();
        layout.draw(graphics2D, pen.x, pen.y);
        pen.y += layout.getDescent();
      }
    }
  }

  public boolean textIsEmpty() {
    return this.getText() == null || this.getText().isEmpty();
  }

  private List<TextLayout> getLayouts(Graphics graphics, Style style, float scale) {

    List<TextLayout> textLayouts = new ArrayList<>();

    AttributedString attributedString = this.getAttributedString(style, scale);
    Graphics2D graphics2D = (Graphics2D) graphics;
    FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();

    int textLength = this.getText().length();
    float wrappingWidth = (Slide.WIDTH - style.getIndent()) * scale;
    LineBreakMeasurer measurer =
        new LineBreakMeasurer(attributedString.getIterator(), fontRenderContext);

    while (measurer.getPosition() < textLength) {
      TextLayout textLayout = measurer.nextLayout(wrappingWidth);
      textLayouts.add(textLayout);
    }
    return textLayouts;
  }

  public String toString() {
    return "TextItem[" + this.getLevel() + "," + this.getText() + "]";
  }
}
