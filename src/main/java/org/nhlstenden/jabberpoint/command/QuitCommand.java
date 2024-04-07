package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;

public class QuitCommand extends Command {

  public QuitCommand(Presentation presentation) {
    super(presentation);
  }

  @Override
  public void execute() {
    System.exit(0);
  }
}
