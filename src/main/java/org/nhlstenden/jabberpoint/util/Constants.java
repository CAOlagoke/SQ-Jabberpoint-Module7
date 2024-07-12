package org.nhlstenden.jabberpoint.util;

import org.json.JSONException;
import org.json.JSONObject;

public class Constants {
  private static final String JSON_FILE = "constants.json";

  public static final String RESOURCE_LOADING_ERR =
      "Error loading resource file. Please, run the built .jar file or build one with `mvn package`"
          + " to resolve the issue.";

  public static String TEST_FILE;
  public static String DEFAULT_SAVE_PATH;
  public static String IO_ERR;
  public static String LOAD_ERR;
  public static String SAVE_ERR;
  public static String JAB_ERR;
  public static String TITLE_FRAME;
  public static String INT_ERR;

  public static void loadConstants() {
    JSONObject json = new JSONObject(ResourceAccessor.getResourceAsString(JSON_FILE));

    try {
      TEST_FILE = json.getString("testFile");
      DEFAULT_SAVE_PATH = json.getString("defaultSavePath");
      IO_ERR = json.getString("exceptionIO");
      LOAD_ERR = json.getString("errorLoadingFile");
      SAVE_ERR = json.getString("errorSavingFile");
      JAB_ERR = json.getString("errorGeneric");
      TITLE_FRAME = json.getString("titleFrame");
      INT_ERR = json.getString("errorParsingInt");
    } catch (JSONException e) {
      System.out.println("Error parsing JSON file: " + JSON_FILE);
      System.exit(0);
    }
  }
}
