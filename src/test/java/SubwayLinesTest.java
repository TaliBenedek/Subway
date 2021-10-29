import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class SubwayLinesTest
{
    @Test
    public void getConnectedStations_firstInLine() throws IOException
    {
        //given
        JsonToSubwayLines toSubwayLines = new JsonToSubwayLines();
        SubwayLines lines = toSubwayLines.readJsonAsMap();
        JsonToSubwayStations toSubwayStations = new JsonToSubwayStations();
        SubwayStations subway = toSubwayStations.readJsonObject();
        Map<Integer, SubwayStations.Station> map = subway.getStations();

        //when
        ArrayList<SubwayStations.Station> connections = lines.getConnectedStations(subway, map.get(29));

        //then
        Assert.assertEquals(map.get(2), connections.get(0));
        Assert.assertEquals(map.get(427), connections.get(1));
        Assert.assertEquals(map.get(105), connections.get(2));
    }

    @Test
    public void getConnectedStations_lastInLine() throws IOException
    {
        //given
        JsonToSubwayLines toSubwayLines = new JsonToSubwayLines();
        SubwayLines lines = toSubwayLines.readJsonAsMap();
        JsonToSubwayStations toSubwayStations = new JsonToSubwayStations();
        SubwayStations subway = toSubwayStations.readJsonObject();
        Map<Integer, SubwayStations.Station> map = subway.getStations();

        //when
        ArrayList<SubwayStations.Station> connections = lines.getConnectedStations(subway, map.get(133));

        //then
        Assert.assertEquals(map.get(132), connections.get(0));
    }
}
