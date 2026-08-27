package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

  public static String generate(String filePath1, String filePath2) throws Exception {
    return generate(filePath1, filePath2, "stylish");
  }

  public static String generate(String filePath1, String filePath2, String formatName)
      throws Exception {

    Map<String, Object> data1 = getData(filePath1);
    Map<String, Object> data2 = getData(filePath2);

    var diff = DiffBuilder.build(data1, data2);

    return Formatter.format(diff, formatName);
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
