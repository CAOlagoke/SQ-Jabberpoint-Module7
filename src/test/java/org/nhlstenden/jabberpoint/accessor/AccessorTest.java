package org.nhlstenden.jabberpoint.accessor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccessorTest {
  @Test
  void testGetDemoAccessor() {
    Accessor accessor = Accessor.getDemoAccessor();
    assertInstanceOf(
        DemoPresentation.class,
        accessor,
        "getDemoAccessor should return an instance of DemoPresentation");
  }
}
