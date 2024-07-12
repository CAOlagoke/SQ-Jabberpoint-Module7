package org.nhlstenden.jabberpoint.command;

import org.nhlstenden.jabberpoint.Presentation;


public abstract class Command {

  Presentation presentation;

  public Command(Presentation presentation) {
    this.presentation = presentation;
  }

  public abstract void execute();
}
