package org.nhlstenden.jabberpoint;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.nhlstenden.jabberpoint.command.QuitCommand;

/**
 * This is the org.nhlstenden.jabberpoint.KeyController (KeyListener)
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class KeyController extends KeyAdapter {
  private final Presentation presentation;

  public KeyController(Presentation p) {
    this.presentation = p;
  }

  public void keyPressed(KeyEvent keyEvent) {
    int keyCode = keyEvent.getKeyCode();
    QuitCommand quitCommand = new QuitCommand(this.presentation);

    if (this.isNextSlideKey(keyCode)) {
      this.presentation.nextSlide();
      return;
    }

    if (this.isPrevSlideKey(keyCode)) {
      this.presentation.prevSlide();
      return;
    }

    if (this.isQuitKey(keyCode)) {
      quitCommand.execute();
    }
  }

  private boolean isNextSlideKey(int keyCode) {
    return keyCode == KeyEvent.VK_PAGE_DOWN
        || keyCode == KeyEvent.VK_DOWN
        || keyCode == KeyEvent.VK_ENTER
        || keyCode == '+';
  }

  private boolean isPrevSlideKey(int keyCode) {
    return keyCode == KeyEvent.VK_PAGE_UP || keyCode == KeyEvent.VK_UP || keyCode == '-';
  }

  private boolean isQuitKey(int keyCode) {
    return keyCode == 'q' || keyCode == 'Q';
  }
}
