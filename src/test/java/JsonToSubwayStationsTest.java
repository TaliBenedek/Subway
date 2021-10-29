import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;


public class JsonToSubwayStationsTest
{
    @Test
    public void readJsonObject() throws IOException
    {
        //given
        JsonToSubwayStations jsonConverter = new JsonToSubwayStations();

        //when
        SubwayStations stations = jsonConverter.readJsonObject();

        //then
        assertEquals(stations.stations.get(0).getName(), "Astor Pl");
        assertArrayEquals(stations.stations.get(0).getLines(), new String[]{"4", "6", "6 Express"});
        assertEquals(stations.stations.get(0).getObjectId(), 1);
        assertEquals(stations.stations.get(0).getLatitude(), -73.99, .01);
        assertEquals(stations.stations.get(0).getLongitude(), 40.73, .01);
    }
}