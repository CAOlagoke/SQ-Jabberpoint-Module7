package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;

public class PreviousSlideCommand extends Command {
  public PreviousSlideCommand(Presentation presentation) {
    super(presentation);
  }

  @Override
  public void execute() {
    this.presentation.prevSlide();
  }
}
