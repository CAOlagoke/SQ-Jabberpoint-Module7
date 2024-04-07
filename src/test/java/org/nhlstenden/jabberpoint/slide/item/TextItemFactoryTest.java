package org.nhlstenden.jabberpoint.slide.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TextItemFactoryTest {
  @Test
  void testCreateSlideItem() {
    TextItemFactory factory = new TextItemFactory();
    SlideItem item = factory.createSlideItem(1, "Hello World");

    assertInstanceOf(TextItem.class, item);

    TextItem textItem = (TextItem) item;

    assertEquals(1, textItem.getLevel());
    assertEquals("Hello World", textItem.getText());
  }
}
