import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceReader {
  public static void readJson(String resource) throws FileNotFoundException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream resourceStream = classLoader.getResourceAsStream(resource);

    if (resourceStream == null) {
      throw new FileNotFoundException("Resource file not found.");
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream));
    // convert to json object with gson

  }
}
