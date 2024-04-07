package org.nhlstenden.jabberpoint.accessor;

import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.Presentation;
import org.nhlstenden.jabberpoint.slide.Slide;

import static org.junit.jupiter.api.Assertions.*;

class DemoPresentationTest {
  @Test
  void testLoadFile() {
    DemoPresentation demoPresentation = new DemoPresentation();
    Presentation presentation = new Presentation();
    demoPresentation.loadFile(presentation, "");

    assertEquals("Demo Presentation", presentation.getTitle());
    assertEquals(3, presentation.getSize());

    Slide slide = presentation.getSlide(0);
    assertEquals("JabberPoint", slide.getTitle());

    slide = presentation.getSlide(1);
    assertEquals("Demonstration of levels and styles", slide.getTitle());

    slide = presentation.getSlide(2);
    assertEquals("The third slide", slide.getTitle());
  }

  @Test
  void testSaveFile() {
    DemoPresentation demoPresentation = new DemoPresentation();
    Presentation presentation = new Presentation();

    assertThrows(IllegalStateException.class, () -> demoPresentation.saveFile(presentation, ""), "Save As->Demo! should throw an IllegalStateException");
  }
}
