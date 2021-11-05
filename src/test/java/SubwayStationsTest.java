import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SubwayStationsTest
{
    @Test
    public void connectStations() throws IOException
    {
        //given
        JsonToSubwayStations jsonConverter = new JsonToSubwayStations();
        SubwayStations subway = jsonConverter.readJsonObject();
        JsonToSubwayLines linesConverter = new JsonToSubwayLines();
        SubwayLines lines = linesConverter.readJsonAsMap();
        List<SubwayStations.Station> expected = Arrays.asList(subway.stations.get(456),subway.stations.get(104));

        //when
        subway.connectStations(lines);

        //then
        Assert.assertEquals(expected, subway.stations.get(0).connections);
    }

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
