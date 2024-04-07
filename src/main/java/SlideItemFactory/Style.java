package SlideItemFactory;

import java.awt.Color;
import java.awt.Font;

/**
 * Style.Style is for Indent, Color, Font and Leading.
 *
 * <p>Direct relation between style-number and item-level: in SlideItemFactory.Slide style if
 * fetched for an item with style-number as item-level.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Style {

  private static Style[] styles;
  private static final String FONTNAME = "Helvetica";

  int indent;
  int fontSize;
  int leading;
  Font font;
  Color color;

  public Style(int indent, Color color, int points, int leading) {
    this.indent = indent;
    this.color = color;
    this.fontSize = points;
    this.font = new Font(FONTNAME, Font.BOLD, this.fontSize);
    this.leading = leading;
  }

  public static Style getStyle(int level) {
    if (level >= styles.length) {
      level = styles.length - 1;
    }
    return styles[level];
  }

  public Font getFont(float scale) {

    return this.font.deriveFont(this.fontSize * scale);
  }

  public int getIndent() {
    return this.indent;
  }

  public Color getColor() {
    return this.color;
  }

  public int getFontSize() {
    return this.fontSize;
  }

  public int getLeading() {
    return this.leading;
  }

  public void setIndent(int indent) {
    this.indent = indent;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setFont(Font font) {
    this.font = font;
  }

  public void setFontSize(int fontSize) {
    this.fontSize = fontSize;
  }

  public void setLeading(int leading) {
    this.leading = leading;
  }

  public static void createStyles() {
    styles = new Style[5];
    // The styles are fixed.
    styles[0] = new Style(0, Color.red, 48, 20); // style for item-level 0
    styles[1] = new Style(20, Color.blue, 40, 10); // style for item-level 1
    styles[2] = new Style(50, Color.black, 36, 10); // style for item-level 2
    styles[3] = new Style(70, Color.black, 30, 10); // style for item-level 3
    styles[4] = new Style(90, Color.black, 24, 10); // style for item-level 4
  }

  public String toString() {
    return "["
        + this.indent
        + ","
        + this.color
        + "; "
        + this.fontSize
        + " on "
        + this.leading
        + "]";
  }
}
