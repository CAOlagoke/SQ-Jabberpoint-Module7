package org.nhlstenden.jabberpoint.slide;

import org.nhlstenden.jabberpoint.Presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * org.nhlstenden.jabberpoint.slide.SlideViewerComponent is a graphical component that can show
 * slides.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class SlideViewerComponent extends JComponent {

  private Slide slide; // current slide
  private final Font labelFont; // font for labels
  private Presentation presentation; // the presentation
  private final JFrame frame;

  private static final long serialVersionUID = 227L;

  private static final Color BGCOLOR = Color.white;
  private static final Color COLOR = Color.black;
  private static final String FONTNAME = "Dialog";
  private static final int FONTSTYLE = Font.BOLD;
  private static final int FONTHEIGHT = 10;
  private static final int XPOS = 1100;
  private static final int YPOS = 20;

  public SlideViewerComponent(Presentation pres, JFrame frame) {
    this.setBackground(BGCOLOR);
    this.presentation = pres;
    this.labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
    this.frame = frame;
  }

  public Dimension getPreferredSize() {
    return new Dimension(Slide.WIDTH, Slide.HEIGHT);
  }

  public void update(Presentation presentation, Slide data) {
    if (data == null) {
      this.repaint();
      return;
    }
    this.presentation = presentation;
    this.slide = data;
    this.repaint();
    this.frame.setTitle(presentation.getTitle());
  }

  // draw the slide
  public void paintComponent(Graphics g) {
    g.setColor(BGCOLOR);
    g.fillRect(0, 0, this.getSize().width, this.getSize().height);
    if (this.presentation.getSlideNumber() < 0 || this.slide == null) {
      return;
    }
    g.setFont(this.labelFont);
    g.setColor(COLOR);
    g.drawString(
        "Slide " + (1 + this.presentation.getSlideNumber()) + " of " + this.presentation.getSize(),
        XPOS,
        YPOS);
    Rectangle area = new Rectangle(0, YPOS, this.getWidth(), (this.getHeight() - YPOS));
    this.slide.draw(g, area, this);
  }
}
