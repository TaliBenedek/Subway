
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.io.IOException;

import static org.junit.Assert.*;

public class JsonToSubwayLinesTest
{
    @Test
    public void readJsonAsMap() throws IOException
    {
        //given
        JsonToSubwayLines jsonConverter = new JsonToSubwayLines();

        //when
        SubwayLines lines = jsonConverter.readJsonAsMap();

        //then
        assertEquals(Sets.newSet("A", "B", "C", "D", "E", "F", "G", "J",
                "L", "M", "N", "Q", "R", "W", "Z",
                "7 Express", "6 Express", "1", "2",
                "3", "4", "5", "6", "7"), lines.keySet());
        assertArrayEquals(new int[]{470, 197, 466, 204, 101, 95, 93, 103, 54, 24, 23, 25}, lines.get("7 Express"));
    }
}