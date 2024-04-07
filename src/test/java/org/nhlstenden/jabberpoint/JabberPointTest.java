package org.nhlstenden.jabberpoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JabberPointTest {
  @Test
  void testMain() {
    assertDoesNotThrow(() -> JabberPoint.main(new String[] {}));
  }

  @Test
  void testMainWithArguments() {
    assertDoesNotThrow(() -> JabberPoint.main(new String[] { "/Users/paul/repos/gh/sq/SQ-Jabberpoint-Module7/test.xml" }));
  }
}
