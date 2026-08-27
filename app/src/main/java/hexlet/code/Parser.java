package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {

  public static Map<String, Object> parse(String content, String format) throws Exception {
    ObjectMapper mapper;

    switch (format) {
      case "json":
        mapper = new ObjectMapper();
        break;
      case "yml":
      case "yaml":
        mapper = new ObjectMapper(new YAMLFactory());
        break;
      default:
        throw new Exception("Unsupported format: " + format);
    }

    return mapper.readValue(content, new TypeReference<Map<String, Object>>() {});
  }
}
