package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DifferTest {

  private static final String EXPECTED =
      """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

  @Test
  void testGenerateJson() throws Exception {
    String actual =
        Differ.generate(
            "src/test/resources/fixtures/file1.json", "src/test/resources/fixtures/file2.json");

    assertEquals(EXPECTED, actual);
  }

  @Test
  void testGenerateYaml() throws Exception {
    String actual =
        Differ.generate(
            "src/test/resources/fixtures/file1.yml", "src/test/resources/fixtures/file2.yml");

    assertEquals(EXPECTED, actual);
  }
}
