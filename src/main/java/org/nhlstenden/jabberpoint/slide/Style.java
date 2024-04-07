package org.nhlstenden.jabberpoint.slide;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.nhlstenden.jabberpoint.util.Constants;
import org.nhlstenden.jabberpoint.util.ResourceAccessor;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.io.*;

/**
 * Style is for Indent, Color, Font and Leading.
 *
 * <p>Direct relation between style-number and item-level: in Slide style if fetched for an item
 * with style-number as item-level.
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
  Color color;
  Font font;
  int fontSize;
  int leading;

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
    try {
      JSONArray styles = readStyles();
      Style.styles = new Style[styles.length()];

      for (int i = 0; i < styles.length(); ++i) {
        JSONObject style = styles.getJSONObject(i);
        Style.styles[i] =
            new Style(
                style.getInt("indent"),
                Color.decode(style.getString("color")),
                style.getInt("fontSize"),
                style.getInt("leading"));
      }

    } catch (JSONException | IOException ex) {
      JOptionPane.showMessageDialog(
          null, Constants.IO_ERR + ex, Constants.JAB_ERR, JOptionPane.ERROR_MESSAGE);
    }
  }

  private static JSONArray readStyles() throws IOException, JSONException {
    String styles = ResourceAccessor.getResourceAsString("styles.json");
    return new JSONArray(styles);
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
