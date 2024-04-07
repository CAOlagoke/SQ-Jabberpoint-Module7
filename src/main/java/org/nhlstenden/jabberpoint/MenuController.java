package org.nhlstenden.jabberpoint;

import org.nhlstenden.jabberpoint.accessor.Accessor;
import org.nhlstenden.jabberpoint.accessor.XMLAccessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

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

  protected static final String TESTFILE = "test.xml";
  protected static final String SAVEFILE = "dump.xml";

  protected static final String IOEX = "IO Exception: ";
  protected static final String LOADERR = "Load Error";
  protected static final String SAVEERR = "Save Error";

  public MenuController(Frame frame, Presentation presentation) {
    this.parent = frame;
    this.presentation = presentation;
    MenuItem menuItem;

    Menu fileMenu = new Menu("File");
    fileMenu.add(this.createMenuItem("Open", e -> this.openFile(), 'O'));
    fileMenu.add(this.createMenuItem("New", e -> this.newFile(), 'N'));
    fileMenu.add(this.createMenuItem("Save", e -> this.saveFile(), 'S'));
    fileMenu.addSeparator();
    fileMenu.add(this.createMenuItem("Exit", e -> System.exit(0), 'X'));
    this.add(fileMenu);

    Menu viewMenu = new Menu("View");
    viewMenu.add(this.createMenuItem("Next slide", e -> this.presentation.nextSlide()));
    viewMenu.add(this.createMenuItem("Previous slide", e -> this.presentation.prevSlide()));
    viewMenu.add(this.createMenuItem("Go to...", e -> this.goToSlide(), 'G'));
    this.add(viewMenu);

    Menu helpMenu = new Menu("Help");
    helpMenu.add(this.createMenuItem("About", e -> AboutBox.show(MenuController.this.parent), 'A'));
    this.setHelpMenu(helpMenu); // needed for portability (Motif, etc.).
  }

  // handlers for the menu items
  // file operations
  // TODO: perhaps refactor these into a separate class
  private void openFile() {
    MenuController.this.presentation.clear();
    Accessor xmlAccessor = new XMLAccessor();
    try {
      xmlAccessor.loadFile(MenuController.this.presentation, TESTFILE);
      MenuController.this.presentation.setSlideNumber(0);
    } catch (IOException exc) {
      JOptionPane.showMessageDialog(
          MenuController.this.parent, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
    }
    MenuController.this.parent.repaint();
  }

  private void newFile() {
    MenuController.this.presentation.clear();
    MenuController.this.parent.repaint();
  }

  private void saveFile() {
    Accessor xmlAccessor = new XMLAccessor();
    try {
      xmlAccessor.saveFile(MenuController.this.presentation, SAVEFILE);
    } catch (IOException exc) {
      JOptionPane.showMessageDialog(
          MenuController.this.parent, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
    }
  }

  // slide operations

  private void goToSlide() {
    String pageNumberStr = JOptionPane.showInputDialog("Page number?");
    int pageNumber = Integer.parseInt(pageNumberStr);
    MenuController.this.presentation.setSlideNumber(pageNumber - 1);
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
