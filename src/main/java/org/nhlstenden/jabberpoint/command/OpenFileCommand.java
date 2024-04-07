package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.accessor.Accessor;
import org.nhlstenden.jabberpoint.accessor.XMLAccessor;
import org.nhlstenden.jabberpoint.slide.SlideViewerComponent;
import org.nhlstenden.jabberpoint.util.Constants;

import javax.swing.*;
import java.io.IOException;

public class OpenFileCommand extends Command {

  public OpenFileCommand(Presentation presentation) {
    super(presentation);
  }

  @Override
  public void execute() {
    SlideViewerComponent parent = this.presentation.getParent();

    this.presentation.clear();
    Accessor xmlAccessor = new XMLAccessor();

    try {
      xmlAccessor.loadFile(this.presentation, Constants.TEST_FILE);
      this.presentation.setSlideNumber(0);
    } catch (IOException exc) {
      JOptionPane.showMessageDialog(
          parent, Constants.IO_ERR + exc, Constants.LOAD_ERR, JOptionPane.ERROR_MESSAGE);
    }

    parent.repaint();
  }
}
