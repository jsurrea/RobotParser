package lym.interprete;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import org.junit.Test;

public class ParserTest {

    @Test
    public void testCases() {

        String source;
        Parser parser;

        try {

            source = Files.readString(FileSystems.getDefault().getPath("test-cases", "Simple.txt"));
            parser = new Parser(source);
            assertTrue("Expected True", parser.parse());
    
            source = Files.readString(FileSystems.getDefault().getPath("test-cases", "Simple.txt"));
            parser = new Parser(source);
            assertTrue("Expected True", parser.parse());

        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(e.getStackTrace().toString(), false);
        }
    }
}
