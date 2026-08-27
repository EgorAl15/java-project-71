package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static Map<String, Object> getData(String filePath) throws Exception {
        String content = readFile(filePath);
        return parse(content);
    }

    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    private static Map<String, Object> parse(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                content,
                new TypeReference<Map<String, Object>>() {
                }
        );
    }
}