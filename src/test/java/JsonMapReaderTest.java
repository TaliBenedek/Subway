import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapReaderTest
{
    @Test
    void readJsonAsMap() throws IOException
    {
        //given
        JsonMapReader reader = new JsonMapReader();

        //when
        SubwayLines lines = reader.readJsonAsMap();

        //then
        assertEquals(lines.containsKey("A"), true);
        assertEquals(lines.containsKey("6 Express"), true);
        assertEquals(lines.get("A")[0], "55");
        assertEquals(lines.get("6 Express")[4], "1");
    }
}