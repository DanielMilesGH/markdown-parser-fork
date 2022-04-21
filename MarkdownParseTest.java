// import assert function and junit
import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

// creating class
public class MarkdownParseTest {
    //signifying test
    @Test
    //method to test addition
    public void addition() {
        // checks if 2 equals 1+1
        assertEquals(2, 1 + 1);
    }
    @Test
    public void testFile1() throws IOException {
        ArrayList<String> testOutput1 = MarkdownParse.getLinks(Files.readString(
                                                      Path.of("test-file.md")));
        assertEquals(testOutput1, List.of("https://something.com", 
                                          "some-thing.html"));
    }
}

