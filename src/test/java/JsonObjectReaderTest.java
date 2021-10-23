import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonObjectReaderTest
{
    @Test
    void readJsonObject() throws IOException
    {
        //given
        JsonObjectReader reader = new JsonObjectReader();

        //when
        Subway stations = reader.readJsonObject();

        //then
        assertEquals(stations.features.get(0).getName(), "Astor Pl");
        assertArrayEquals(stations.features.get(0).getLines(), new String[]{"4", "6", "6 Express"});
        assertEquals(stations.features.get(0).getObjectId(), 1);
        assertEquals(stations.features.get(0).getLatitude(), -73.99, .01);
        assertEquals(stations.features.get(0).getLongitude(), 40.73, .01);
    }
}