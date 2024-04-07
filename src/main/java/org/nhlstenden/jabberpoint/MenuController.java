package org.nhlstenden.jabberpoint;

import org.nhlstenden.jabberpoint.command.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The controller for the menu
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {

  private final Frame parent; // the frame, only used as parent for the Dialogs
  private final Presentation presentation; // Commands are given to the presentation

  private static final long serialVersionUID = 227L;

  public MenuController(Frame frame, Presentation presentation) {
    this.parent = frame;
    this.presentation = presentation;

    QuitCommand quitCommand = new QuitCommand(this.presentation);
    GoToCommand goToCommand = new GoToCommand(this.presentation);
    OpenFileCommand openFileCommand = new OpenFileCommand(this.presentation);
    SaveFileCommand saveFileCommand = new SaveFileCommand(this.presentation);
    NewFileCommand newFileCommand = new NewFileCommand(this.presentation);

    Menu fileMenu = new Menu("File");
    fileMenu.add(this.createMenuItem("Open", e -> openFileCommand.execute(), 'O'));
    fileMenu.add(this.createMenuItem("New", e -> newFileCommand.execute(), 'N'));
    fileMenu.add(this.createMenuItem("Save", e -> saveFileCommand.execute(), 'S'));
    fileMenu.addSeparator();
    fileMenu.add(this.createMenuItem("Exit", e -> quitCommand.execute()));
    this.add(fileMenu);

    Menu viewMenu = new Menu("View");
    viewMenu.add(this.createMenuItem("Next slide", e -> this.presentation.nextSlide()));
    viewMenu.add(this.createMenuItem("Previous slide", e -> this.presentation.prevSlide()));
    viewMenu.add(this.createMenuItem("Go to...", e -> goToCommand.execute(), 'G'));
    this.add(viewMenu);

    Menu helpMenu = new Menu("Help");
    helpMenu.add(this.createMenuItem("About", e -> AboutBox.show(MenuController.this.parent), 'A'));
    this.setHelpMenu(helpMenu); // needed for portability (Motif, etc.).
  }

  // create a menu item
  public MenuItem createMenuItem(String name, ActionListener action) {
    MenuItem menuItem;
    menuItem = new MenuItem(name);
    menuItem.addActionListener(action);
    return menuItem;
  }

  public MenuItem createMenuItem(String name, ActionListener action, char shortcutKey) {
    MenuItem menuItem;
    menuItem = new MenuItem(name, new MenuShortcut(shortcutKey));
    menuItem.addActionListener(action);
    return menuItem;
  }
}
