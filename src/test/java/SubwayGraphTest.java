import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class SubwayGraphTest
{

    @Test
    public void setUpGraph() throws IOException
    {
        //given
        JsonToSubwayStations subwayConverter = new JsonToSubwayStations();
        SubwayStations subway = subwayConverter.readJsonObject();

        JsonToSubwayLines linesConverter = new JsonToSubwayLines();
        SubwayLines lines = linesConverter.readJsonAsMap();
        subway.connectStations(lines);

        //when
        SubwayGraph graph = new SubwayGraph(subway);

        //then
        int index = 0;
        for(Map.Entry<SubwayStations.Station, Node> entry: graph.map.entrySet())
        {
            Node expectedNode = new Node(subway.stations.get(index));
            assertEquals(expectedNode, entry.getValue());
            index++;
        }
    }

    @Test
    public void shortestPath() throws IOException
    {
        //given
        JsonToSubwayStations subwayConverter = new JsonToSubwayStations();
        SubwayStations subway = subwayConverter.readJsonObject();
        JsonToSubwayLines linesConverter = new JsonToSubwayLines();
        SubwayLines lines = linesConverter.readJsonAsMap();
        subway.connectStations(lines);
        SubwayGraph graph = new SubwayGraph(subway);
        SubwayStations.Station start = subway.stations.get(0);
        SubwayStations.Station destination = subway.stations.get(31);
        List<SubwayStations.Station> expected = Arrays.asList(subway.stations.get(1),
                subway.stations.get(104),
                subway.stations.get(91),
                subway.stations.get(199),
                subway.stations.get(31)
        );

        //when
        List<SubwayStations.Station> shortestPath = graph.shortestPath(start, destination);

        //then
        assertEquals(expected, shortestPath);
    }
}