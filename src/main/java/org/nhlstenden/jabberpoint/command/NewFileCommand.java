package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;

public class NewFileCommand extends Command {
  public NewFileCommand(Presentation presentation) {
    super(presentation);
  }

  @Override
  public void execute() {
    this.presentation.clear();
    this.presentation.setSlideNumber(0);
    this.presentation.getParent().repaint();
  }
}
