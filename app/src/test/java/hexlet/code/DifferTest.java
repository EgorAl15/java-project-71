package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class DifferTest {

  private static final String FIXTURES_PATH = "src/test/resources/fixtures/";

  private static String getFixturePath(String fileName) {
    return FIXTURES_PATH + fileName;
  }

  private static String readFixture(String fileName) throws Exception {
    return Files.readString(Path.of(getFixturePath(fileName)))
        .replace("\r\n", "\n")
        .replace("\r", "\n")
        .trim();
  }

  @Test
  void testJsonStylish() throws Exception {
    String expected = readFixture("expected-stylish.txt");

    String actual =
        Differ.generate(getFixturePath("nested1.json"), getFixturePath("nested2.json"), "stylish");

    assertEquals(expected, actual);
  }

  @Test
  void testYamlStylish() throws Exception {
    String expected = readFixture("expected-stylish.txt");

    String actual =
        Differ.generate(getFixturePath("nested1.yml"), getFixturePath("nested2.yml"), "stylish");

    assertEquals(expected, actual);
  }

  @Test
  void testJsonPlain() throws Exception {
    String expected = readFixture("expected-plain.txt");

    String actual =
        Differ.generate(getFixturePath("nested1.json"), getFixturePath("nested2.json"), "plain");

    assertEquals(expected, actual);
  }

  @Test
  void testYamlPlain() throws Exception {
    String expected = readFixture("expected-plain.txt");

    String actual =
        Differ.generate(getFixturePath("nested1.yml"), getFixturePath("nested2.yml"), "plain");

    assertEquals(expected, actual);
  }

  @Test
  void testJsonJsonFormat() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    var expected = mapper.readTree(readFixture("expected-json.json"));

    var actual =
        mapper.readTree(
            Differ.generate(
                getFixturePath("nested1.json"), getFixturePath("nested2.json"), "json"));

    assertEquals(expected, actual);
  }

  @Test
  void testYamlJsonFormat() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    var expected = mapper.readTree(readFixture("expected-json.json"));

    var actual =
        mapper.readTree(
            Differ.generate(getFixturePath("nested1.yml"), getFixturePath("nested2.yml"), "json"));

    assertEquals(expected, actual);
  }

  @Test
  void testJsonDefaultFormat() throws Exception {
    String expected = readFixture("expected-stylish.txt");

    String actual = Differ.generate(getFixturePath("nested1.json"), getFixturePath("nested2.json"));

    assertEquals(expected, actual);
  }

  @Test
  void testYamlDefaultFormat() throws Exception {
    String expected = readFixture("expected-stylish.txt");

    String actual = Differ.generate(getFixturePath("nested1.yml"), getFixturePath("nested2.yml"));

    assertEquals(expected, actual);
  }
}
