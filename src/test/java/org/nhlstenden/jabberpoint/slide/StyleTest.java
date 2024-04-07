package org.nhlstenden.jabberpoint.slide;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StyleTest {
  @Test
  void generateStyles() {
    Style.createStyles();
    assertEquals(0, Style.getStyle(0).getIndent());
  }
}
