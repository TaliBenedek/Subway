import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class SubwayStationsTest
{
    @Test
    public void getDistance() throws IOException
    {
        //given
        JsonToSubwayStations jsonConverter = new JsonToSubwayStations();
        SubwayStations subway = jsonConverter.readJsonObject();

        //when
        double distance = subway.stations.get(0).getDistance(50, 50);

        //then
        Assert.assertEquals(124.33, distance, .5);
    }

    @Test
    public void getClosestStation() throws IOException
    {
        //given
        JsonToSubwayStations jsonConverter = new JsonToSubwayStations();
        SubwayStations subway = jsonConverter.readJsonObject();

        //when
        SubwayStations.Station closestStation = subway.getClosestStation(-73.99, 40.73);

        //then
        Assert.assertEquals(subway.stations.get(0), closestStation);
    }
}
