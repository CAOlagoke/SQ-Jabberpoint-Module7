package org.nhlstenden.jabberpoint.util;

import static org.junit.jupiter.api.Assertions.*;

import com.ginsberg.junit.exit.ExpectSystemExit;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

public class ResourceAccessorTest {
  @Test
  @ExpectSystemExit
  void getResource_nullSource() {
    ResourceAccessor.getResource(null);
  }

  @Test
  @ExpectSystemExit
  void getResource_invalidSource() {
    ResourceAccessor.getResource("does_not_exist.txt");
  }

  @Test
  void testGetResource_validSource() {
    InputStream resource = ResourceAccessor.getResource("test.txt");

    assertNotNull(resource);
  }

  @Test
  void getResourceAsString_validSource() {
    String resource = ResourceAccessor.getResourceAsString("test.txt");

    assertEquals("hello", resource);
  }
}
