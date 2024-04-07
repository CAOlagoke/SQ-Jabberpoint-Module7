package org.nhlstenden.jabberpoint.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConstantsTest {
  @Test
  void testConstants_validFile() {
    Constants.loadConstants();

    assertEquals("test.xml", Constants.TEST_FILE);
    assertEquals("dump.xml", Constants.DEFAULT_SAVE_PATH);
    assertEquals("IO Exception: ", Constants.IO_ERR);
    assertEquals("Load Error", Constants.LOAD_ERR);
    assertEquals("Save Error", Constants.SAVE_ERR);
    assertEquals("Jabberpoint Error", Constants.JAB_ERR);
    assertEquals("Jabberpoint 1.6 - OU version", Constants.TITLE_FRAME);
    assertEquals("Error parsing integer. Are you sure you entered a positive number?", Constants.INT_ERR);
  }
}
