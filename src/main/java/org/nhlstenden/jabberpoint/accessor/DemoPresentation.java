package org.nhlstenden.jabberpoint.accessor;

import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.Slide;

/**
 * A built in demo-presentation
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
class DemoPresentation extends Accessor {

  public void loadFile(Presentation presentation, String unusedFilename) {
    presentation.setTitle("Demo Presentation");
    Slide slide;
    slide = new Slide("JabberPoint");
    slide.addTextItem(1, "The Java Presentation Tool");
    slide.addTextItem(2, "Copyright (c) 1996-2000: Ian Darwin");
    slide.addTextItem(2, "Copyright (c) 2000-now:");
    slide.addTextItem(2, "Gert Florijn andn Sylvia Stuurman");
    slide.addTextItem(4, "Starting JabberPoint without a filename");
    slide.addTextItem(4, "shows this presentation");
    slide.addTextItem(1, "Navigate:");
    slide.addTextItem(3, "Next slide: PgDn or Enter");
    slide.addTextItem(3, "Previous slide: PgUp or up-arrow");
    slide.addTextItem(3, "Quit: q or Q");
    presentation.append(slide);

    slide = new Slide("Demonstration of levels and styles");
    slide.addTextItem(1, "Level 1");
    slide.addTextItem(2, "Level 2");
    slide.addTextItem(1, "Again level 1");
    slide.addTextItem(1, "Level 1 has style number 1");
    slide.addTextItem(2, "Level 2 has style number  2");
    slide.addTextItem(3, "This is how level 3 looks like");
    slide.addTextItem(4, "And this is level 4");
    presentation.append(slide);

    slide = new Slide("The third slide");
    slide.addTextItem(1, "To open a new presentation,");
    slide.addTextItem(2, "use File->Open from the menu.");
    slide.addTextItem(1, " ");
    slide.addTextItem(1, "This is the end of the presentation.");
    slide.addBitmapItem(1, "JabberPoint.jpg");
    presentation.append(slide);
  }

  public void saveFile(Presentation presentation, String unusedFilename) {
    throw new IllegalStateException("Save As->Demo! called");
  }
}
