package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

  public static String generate(String filePath1, String filePath2) throws Exception {
    Map<String, Object> data1 = getData(filePath1);
    Map<String, Object> data2 = getData(filePath2);

    Set<String> keys = new TreeSet<>();
    keys.addAll(data1.keySet());
    keys.addAll(data2.keySet());

    StringBuilder result = new StringBuilder();
    result.append("{\n");

    for (String key : keys) {
      boolean inFirst = data1.containsKey(key);
      boolean inSecond = data2.containsKey(key);

      Object value1 = data1.get(key);
      Object value2 = data2.get(key);

      if (inFirst && !inSecond) {
        result.append("  - ").append(key).append(": ").append(value1).append("\n");

      } else if (!inFirst && inSecond) {
        result.append("  + ").append(key).append(": ").append(value2).append("\n");

      } else if (value1 == null ? value2 == null : value1.equals(value2)) {
        result.append("    ").append(key).append(": ").append(value1).append("\n");

      } else {
        result.append("  - ").append(key).append(": ").append(value1).append("\n");

        result.append("  + ").append(key).append(": ").append(value2).append("\n");
      }
    }

    result.append("}");

    return result.toString();
  }

  private static Map<String, Object> getData(String filePath) throws Exception {
    String content = readFile(filePath);
    String format = getFormat(filePath);

    return Parser.parse(content, format);
  }

  private static String readFile(String filePath) throws Exception {
    Path path = Paths.get(filePath).toAbsolutePath().normalize();
    return Files.readString(path);
  }

  private static String getFormat(String filePath) {
    int dotIndex = filePath.lastIndexOf(".");
    return filePath.substring(dotIndex + 1);
  }
}
