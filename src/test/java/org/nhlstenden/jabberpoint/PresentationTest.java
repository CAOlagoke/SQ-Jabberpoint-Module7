package org.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import org.nhlstenden.jabberpoint.slide.Slide;
import static org.junit.jupiter.api.Assertions.*;

class PresentationTest {
  @Test
  void testPresentation() {
    Presentation presentation = new Presentation();
    assertEquals(0, presentation.getSize(), "Presentation should be empty");
    assertNull(presentation.getCurrentSlide(), "Current slide should be null");
  }

  @Test
  void testAppendAndGetSlide() {
    Presentation presentation = new Presentation();
    Slide slide = new Slide("test title");
    presentation.append(slide);
    assertEquals(1, presentation.getSize(), "Presentation should have 1 slide");
    assertEquals(slide, presentation.getSlide(0), "Slide 0 should be the appended slide");
  }

  @Test
  void testSetAndGetSlideNumber() {
    Presentation presentation = new Presentation();
    Slide slide = new Slide("test title");
    presentation.append(slide);
    presentation.setSlideNumber(0);
    assertEquals(0, presentation.getSlideNumber(), "Slide number should be 0");
    assertEquals(slide, presentation.getCurrentSlide(), "Current slide should be the appended slide");
  }

  @Test
  void testPrevAndNextSlide() {
    Presentation presentation = new Presentation();
    Slide slide1 = new Slide("test title");
    Slide slide2 = new Slide("test title");
    presentation.append(slide1);
    presentation.append(slide2);
    presentation.setSlideNumber(1);
    presentation.prevSlide();
    assertEquals(0, presentation.getSlideNumber(), "Slide number should be 0");
    assertEquals(slide1, presentation.getCurrentSlide(), "Current slide should be slide1");
    presentation.nextSlide();
    assertEquals(1, presentation.getSlideNumber(), "Slide number should be 1");
    assertEquals(slide2, presentation.getCurrentSlide(), "Current slide should be slide2");
  }

  @Test
  void testClear() {
    Presentation presentation = new Presentation();
    Slide slide = new Slide("test title");
    presentation.append(slide);
    presentation.clear();
    assertEquals(0, presentation.getSize(), "Presentation should be empty");
    assertNull(presentation.getCurrentSlide(), "Current slide should be null");
  }
}
