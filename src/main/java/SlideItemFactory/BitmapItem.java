package SlideItemFactory;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;

/**
 * De klasse voor een Bitmap item
 *
 * <p>Bitmap items have the responsibility to draw themselves.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class BitmapItem implements SlideItem {

  private BufferedImage bufferedImage;
  private String imageName;
  private int level;

  public BitmapItem(int level, String imageName) {

    this.level = level;
    this.imageName = imageName;

    try {
      bufferedImage = ImageIO.read(new File(imageName));
    } catch (IOException e) {
      System.err.println("File " + imageName + " not found");
    }
  }

  public int getLevel() {
    return this.level;
  }

  public String getImageName() {
    return this.imageName;
  }

  public BufferedImage getBufferedImage() {
    return this.bufferedImage;
  }

  public void setBufferedImage(BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
  }

  // give the  bounding box of the image
  public Rectangle getBoundingBox(
      Graphics graphics, ImageObserver observer, float scale, Style style) {

    int xCoordinate = (int) (style.getIndent() * scale);
    int yCoordiante = 0;
    int width = (int) (getBufferedImage().getWidth(observer) * scale);
    int height =
        ((int) (style.getLeading() * scale))
            + (int) (getBufferedImage().getHeight(observer) * scale);

    return new Rectangle(xCoordinate, yCoordiante, width, height);
  }

  // draw the image
  public void draw(
      int xCoordinate,
      int yCoordinate,
      float scale,
      Graphics graphics,
      Style style,
      ImageObserver observer) {

    int x = xCoordinate + (int) (style.getIndent() * scale);
    int y = yCoordinate + (int) (style.getLeading() * scale);
    int width = (int) (this.getBufferedImage().getWidth(observer) * scale);
    int height = (int) (this.getBufferedImage().getHeight(observer) * scale);

    graphics.drawImage(getBufferedImage(), x, y, width, height, observer);
  }

  public String toString() {
    return "SlideItem.BitmapItem[" + getLevel() + "," + getImageName() + "]";
  }
}
