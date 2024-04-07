package org.nhlstenden.jabberpoint.slide.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TextItemTest {
  @Test
  void TextItem_toString() {
    TextItem item = new TextItem(1, "hello");

    assertEquals("TextItem[1,hello]", item.toString());
  }
}
