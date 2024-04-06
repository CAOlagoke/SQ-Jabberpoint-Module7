import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class MenuControllerTest {

  @Test
  public void MenuItemCreation_withoutShortcut() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    Frame testFrame = new Frame();
    Presentation testPresentation = new Presentation();
    MenuController testMenuController = new MenuController(testFrame, testPresentation);

    MenuItem testMenuItem = testMenuController.createMenuItem("Sample", e -> {});
    MenuItem anotherMenuItem = new MenuItem("Sample");

    assertNotNull(testMenuItem);
    assertEquals(testMenuItem.getLabel(), "Sample");

    // As the shortcut is not set, it should be null
    assertNull(testMenuItem.getShortcut());
  }

  @Test
  public void MenuItemCreation_withShortcut() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Headless environment detected. Skipping test.");
      return;
    }

    Frame testFrame = new Frame();
    Presentation testPresentation = new Presentation();
    MenuController testMenuController = new MenuController(testFrame, testPresentation);

    MenuItem testMenuItem = testMenuController.createMenuItem("Sample", e -> {}, 'S');

    assertNotNull(testMenuItem);
    assertEquals(testMenuItem.getLabel(), "Sample");
    assertEquals(testMenuItem.getShortcut(), new MenuShortcut('S'));
  }
}
