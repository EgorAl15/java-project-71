package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
