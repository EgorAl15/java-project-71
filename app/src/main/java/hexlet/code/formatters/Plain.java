package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plain {

  public static String format(List<Map<String, Object>> diff) {
    List<String> result = new ArrayList<>();

    for (Map<String, Object> node : diff) {
      String key = (String) node.get("key");
      String status = (String) node.get("status");

      switch (status) {
        case "added" -> {
          Object value = node.get("value");

          result.add("Property '" + key + "' was added with value: " + formatValue(value));
        }

        case "removed" -> result.add("Property '" + key + "' was removed");

        case "changed" -> {
          Object oldValue = node.get("oldValue");
          Object newValue = node.get("newValue");

          result.add(
              "Property '"
                  + key
                  + "' was updated. From "
                  + formatValue(oldValue)
                  + " to "
                  + formatValue(newValue));
        }

        case "unchanged" -> {
          // Неизменившиеся свойства в формате plain не выводятся.
        }

        default -> throw new IllegalStateException("Unknown status: " + status);
      }
    }

    return String.join("\n", result);
  }

  private static String formatValue(Object value) {
    if (value instanceof Map || value instanceof List) {
      return "[complex value]";
    }

    if (value instanceof String) {
      return "'" + value + "'";
    }

    return String.valueOf(value);
  }
}
