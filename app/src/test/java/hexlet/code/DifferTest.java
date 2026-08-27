package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testGetData() throws Exception {
        Map<String, Object> data1 = Differ.getData(
                "src/test/resources/fixtures/file1.json"
        );

        assertEquals("hexlet.io", data1.get("host"));
        assertEquals(50, data1.get("timeout"));
        assertEquals("123.234.53.22", data1.get("proxy"));
        assertEquals(false, data1.get("follow"));

        Map<String, Object> data2 = Differ.getData(
                "src/test/resources/fixtures/file2.json"
        );

        assertEquals(20, data2.get("timeout"));
        assertEquals(true, data2.get("verbose"));
        assertEquals("hexlet.io", data2.get("host"));
    }

    @Test
    void testGenerate() throws Exception {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        String actual = Differ.generate(
                "src/test/resources/fixtures/file1.json",
                "src/test/resources/fixtures/file2.json"
        );

        assertEquals(expected, actual);
    }
}