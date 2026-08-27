package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class DifferTest {

  private static final String FIXTURES_PATH = "src/test/resources/fixtures/";

  private static final String EXPECTED_FLAT =
      """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

  private static final String EXPECTED_NESTED =
      """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

  private static final String EXPECTED_PLAIN =
      """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";

  @Test
  void testGenerateJson() throws Exception {
    String actual = Differ.generate(FIXTURES_PATH + "file1.json", FIXTURES_PATH + "file2.json");

    assertEquals(EXPECTED_FLAT, actual);
  }

  @Test
  void testGenerateYaml() throws Exception {
    String actual = Differ.generate(FIXTURES_PATH + "file1.yml", FIXTURES_PATH + "file2.yml");

    assertEquals(EXPECTED_FLAT, actual);
  }

  @Test
  void testGenerateNestedJsonStylish() throws Exception {
    String actual = Differ.generate(FIXTURES_PATH + "nested1.json", FIXTURES_PATH + "nested2.json");

    assertEquals(EXPECTED_NESTED, actual);
  }

  @Test
  void testGeneratePlain() throws Exception {
    String actual =
        Differ.generate(FIXTURES_PATH + "nested1.json", FIXTURES_PATH + "nested2.json", "plain");

    assertEquals(EXPECTED_PLAIN, actual);
  }

  @Test
  void testGenerateJsonFormat() throws Exception {
    String actual =
        Differ.generate(FIXTURES_PATH + "nested1.json", FIXTURES_PATH + "nested2.json", "json");

    ObjectMapper mapper = new ObjectMapper();
    var parsed = mapper.readTree(actual);

    assertTrue(parsed.isArray());
    assertTrue(parsed.size() > 0);

    assertEquals("chars1", parsed.get(0).get("key").asText());
    assertEquals("unchanged", parsed.get(0).get("status").asText());
  }
}
