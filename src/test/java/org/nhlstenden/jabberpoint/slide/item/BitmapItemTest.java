package org.nhlstenden.jabberpoint.slide.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BitmapItemTest {
  @Test
  void BitMapItem_toString() {
    BitmapItem item = new BitmapItem(1, "test.png");

    assertEquals("BitmapItem[1,test.png]", item.toString());
  }
}
