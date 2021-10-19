import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SubwayTest
{
    @Test
    public void getters() throws IOException
    {
        //given
        //create Gson instance
        Gson gson = new Gson();

        //when
        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get("SubwayStations.json"));

        // convert JSON string to User object
        Subway subway = gson.fromJson(reader, Subway.class);

        // first station
        String name0 = subway.features.get(0).getName();
        String[] lines0 = subway.features.get(0).getLines();
        int objectId0 = subway.features.get(0).getObjectId();
        double latitude1 = subway.features.get(0).getLatitude();
        double longitude1 = subway.features.get(0).getLongitude();

        // last station
        String name472 = subway.features.get(472).getName();
        String[] lines472 = subway.features.get(472).getLines();
        int objectId472 = subway.features.get(472).getObjectId();
        double latitude472 = subway.features.get(472).getLatitude();
        double longitude472 = subway.features.get(472).getLongitude();

        //then
        assertEquals(name0, "Astor Pl");
        assertArrayEquals(lines0, new String[]{"4", "6", "6 Express"});
        assertEquals(objectId0, 1);
        assertEquals(latitude1, -73.99, .01);
        assertEquals(longitude1, 40.73, .01);

        assertEquals(name472, "96th St");
        assertArrayEquals(lines472, new String[]{"Q"});
        assertEquals(objectId472, 643);
        assertEquals(latitude472, -73.95, .01);
        assertEquals(longitude472, 40.78, .01);

        // close reader
        reader.close();
    }
}