package SlideItemFactory;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.*;

/** <p>A text item.</p>
 * <p>A SlideItem.TextItem has drawingfunctionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class TextItem implements SlideItem {

	private String text;
	private int level;

	public TextItem(int level, String text) {
		this.level = level;
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public int getLevel() {
		return this.level;
	}



	// 
	public AttributedString getAttributedString(Style style, float scale) {

		int beginIndex = 0;
		AttributedString attributedString = new AttributedString(getText());
		
		attributedString.addAttribute(TextAttribute.FONT, style.getFont(scale), beginIndex, text.length());

		return attributedString;
	}

// give the bounding box of the item
	public Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale, Style style) {

		int boundingWidth = 0;
		int boundingHeight = (int) (style.getLeading() * scale);

		List<TextLayout> layouts = getLayouts(graphics, style, scale);
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

		int xCoordinate = (int) (style.indent*scale);
		int yCoordinate = 0;

		return new Rectangle(xCoordinate, yCoordinate, boundingWidth, boundingHeight );
	}

// draw the item
	public void draw(int xCoordinate, int yCoordinate, float scale, Graphics graphics, Style style, ImageObserver imageObserver) {

		if(!textIsEmpty()){

			List<TextLayout> layouts = getLayouts(graphics, style, scale);
			Graphics2D graphics2D = (Graphics2D)graphics;
			graphics2D.setColor(style.getColor());

			Point pen = new Point(xCoordinate + (int)(style.getIndent() * scale),yCoordinate + (int) (style.getLeading() * scale));

			Iterator<TextLayout> iterator = layouts.iterator();

			while (iterator.hasNext()) {
				TextLayout layout = iterator.next();
				pen.y += layout.getAscent();
				layout.draw(graphics2D, pen.x, pen.y);
				pen.y += layout.getDescent();
			}
		}
	  }

	  public boolean textIsEmpty(){

		  if (getText() == null || getText().length() == 0) {
			  return true;
		  }

		  return false;
	  }
	private List<TextLayout> getLayouts(Graphics graphics, Style style, float scale) {

		List<TextLayout> textLayouts = new ArrayList<>();

		AttributedString attributedString = getAttributedString(style, scale);
    	Graphics2D graphics2D = (Graphics2D) graphics;
    	FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();

		int textLength = getText().length();
		float wrappingWidth = (Slide.WIDTH - style.getIndent()) * scale;
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attributedString.getIterator(), fontRenderContext);

    	while (measurer.getPosition() < textLength) {
    		TextLayout textLayout = measurer.nextLayout(wrappingWidth);
    		textLayouts.add(textLayout);
    	}
    	return textLayouts;
	}

	public String toString() {
		return "SlideItem.TextItem[" + getLevel()+","+getText()+"]";
	}
}
