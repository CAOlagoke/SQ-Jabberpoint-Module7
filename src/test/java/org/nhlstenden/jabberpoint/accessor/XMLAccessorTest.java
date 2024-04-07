package org.nhlstenden.jabberpoint.accessor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;

public class XMLAccessorTest {
  @Test
  void testSaveFile() {
    DemoPresentation demoPresentation = new DemoPresentation();
    Presentation presentation = new Presentation();
    demoPresentation.loadFile(presentation, "");

    XMLAccessor accessor = new XMLAccessor();
    assertDoesNotThrow(() -> accessor.saveFile(presentation, "test.xml"));
  }
}
