package SlideItemFactory;

import SlideItemFactory.*;
import Style.Style;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/** <p>A slide. This class has a drawing functionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	protected String title; // title is saved separately
	protected Vector<SlideItem> items; // slide items are saved in a Vector

	public Slide(String title) {
		this.title = title;
		this.items = new Vector<SlideItem>();
	}

	// Add a slide item
	public void addSlideItem(SlideItem slideItem) {
		this.items.addElement(slideItem);
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

		addSlideItem(textItemFactory.createSlideItem(level, message));
	}

	public void addBitmapItem(int level, String imageName){

		SlideItemFactory bitmapItemFactory = new BitMapItemFactory();

		addSlideItem(bitmapItemFactory.createSlideItem(level, imageName));
	}
	// give the  SlideItem.SlideItem
	public SlideItem getSlideItem(int number) {
		return (SlideItem)items.elementAt(number);
	}

	// give all SlideItems in a Vector
	public Vector<SlideItem> getSlideItems() {
		return this.items;
	}

	// give the size of the SlideItemFactory.Slide
	public int getSizeOfSlideItems() {
		return items.size();
	}

	// draw the slide
	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
	    int y = area.y;
	// To handle drawing the title separately
		
		y+= drawTitle(g, area.x, y, view, scale);

	    for (int i = 0; i < this.getSizeOfSlideItems(); i++) {
	      SlideItem slideItem = getSlideItems().elementAt(i);
			
		  int sItemLevel = getLevel(slideItem);
		  
		  System.out.println("Text of slideItem, color" + i + ": " + getText(slideItem));
		  System.out.println("Level of slideItem, color" + i + ": " + sItemLevel);
		

		  System.out.println("Level of slideItem" + i + ": " + Style.getStyle(sItemLevel));
		  
	      Style style = Style.getStyle(sItemLevel);

		  System.out.println("Style of slideItem" + i + ": " + style);
	      slideItem.draw(area.x, y, scale, g, style, view);
	      y += slideItem.getBoundingBox(g, view, scale, style).height;
	    }
	  }

	  public int drawTitle(Graphics g, int x, int y, ImageObserver view, float scale){

		int defaultLevel = 0;
		SlideItemFactory textItemFactory = new TextItemFactory();
		Style style = Style.getStyle(defaultLevel);
	    SlideItem slideItem =  textItemFactory.createSlideItem(defaultLevel, getTitle());

		slideItem.draw(x, y, scale, g, style, view);

		int increase = slideItem.getBoundingBox(g, view, scale, style).height;

		return increase;
	  }

	  public String getText(SlideItem slideItem){
		
		String text = null;
		if(slideItem instanceof TextItem){

			text = ((TextItem)slideItem).getText();
		}else if(slideItem instanceof BitmapItem){
			
			text = ((BitmapItem)slideItem).getName();
		}

		return text;
	  }

	  public int getLevel(SlideItem slideItem){

		int level = 1;
		if(slideItem instanceof TextItem){

			level = ((TextItem)slideItem).getLevel();

		}else if(slideItem instanceof BitmapItem){
			
			level = ((BitmapItem)slideItem).getLevel();
		}

		return level;
	  }

//	public void draw(Graphics g, Rectangle area, ImageObserver view) {
//		float scale = getScale(area);
//		int y = area.y;
//		// Title is handled separately
//		SlideItem slideItem = new TextItem(0, getTitle());
//		Style style = Style.getStyle(slideItem.getLevel());
//		slideItem.draw(area.x, y, scale, g, style, view);
//		y += slideItem.getBoundingBox(g, view, scale, style).height;
//		for (int number=0; number<getSize(); number++) {
//			slideItem = (SlideItem)getSlideItems().elementAt(number);
//			style = Style.getStyle(slideItem.getLevel());
//			slideItem.draw(area.x, y, scale, g, style, view);
//			y += slideItem.getBoundingBox(g, view, scale, style).height;
//		}
//	}


	// Give the scale for drawing
	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)WIDTH), ((float)area.height) / ((float)HEIGHT));
	}
}
