package org.nhlstenden.jabberpoint.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


public class ResourceAccessor {
  public static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

  public static InputStream getResource(String resource) {
    if (resource == null) {
      System.out.println("Resource file name is null.");
      System.out.println(Constants.RESOURCE_LOADING_ERR);
      System.exit(0);
    }

    InputStream stream = classLoader.getResourceAsStream(resource);

    if (stream == null) {
      System.out.println("Resource file not found: " + resource);
      System.out.println(Constants.RESOURCE_LOADING_ERR);
      System.exit(0);
    }

    return stream;
  }

  public static String getResourceAsString(String resource) {
    InputStream resourceStream = getResource(resource);
    return new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8))
        .lines()
        .collect(Collectors.joining("\n"));
  }
}
