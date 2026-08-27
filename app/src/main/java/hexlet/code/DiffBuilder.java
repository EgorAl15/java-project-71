package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {

  public static List<Map<String, Object>> build(
      Map<String, Object> data1, Map<String, Object> data2) {

    List<Map<String, Object>> diff = new ArrayList<>();

    Set<String> keys = new TreeSet<>();
    keys.addAll(data1.keySet());
    keys.addAll(data2.keySet());

    for (String key : keys) {
      boolean inFirst = data1.containsKey(key);
      boolean inSecond = data2.containsKey(key);

      Object value1 = data1.get(key);
      Object value2 = data2.get(key);

      Map<String, Object> node = new HashMap<>();
      node.put("key", key);

      if (inFirst && !inSecond) {
        node.put("status", "removed");
        node.put("value", value1);

      } else if (!inFirst && inSecond) {
        node.put("status", "added");
        node.put("value", value2);

      } else if (value1 == null ? value2 == null : value1.equals(value2)) {
        node.put("status", "unchanged");
        node.put("value", value1);

      } else {
        node.put("status", "changed");
        node.put("oldValue", value1);
        node.put("newValue", value2);
      }

      diff.add(node);
    }

    return diff;
  }
}
