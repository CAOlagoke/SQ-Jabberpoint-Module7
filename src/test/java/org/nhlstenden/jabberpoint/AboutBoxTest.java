package org.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import javax.swing.JFrame;

import static org.junit.jupiter.api.Assertions.*;

class AboutBoxTest {
  @Test
  void testShow() {
    assertDoesNotThrow(() -> AboutBox.show(new JFrame()));
  }
}
