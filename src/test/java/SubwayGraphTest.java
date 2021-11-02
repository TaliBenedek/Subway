import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class SubwayGraphTest
{

    @Test
    public void setUpGraph() throws IOException
    {
        //given
        SubwayGraph graph = new SubwayGraph();
        JsonToSubwayStations subwayConverter = new JsonToSubwayStations();
        SubwayStations subway = subwayConverter.readJsonObject();
        JsonToSubwayLines linesConverter = new JsonToSubwayLines();
        SubwayLines lines = linesConverter.readJsonAsMap();
        subway.connectStations(lines);

        //when
        graph.setUpGraph(subway);

        //then
        Iterator it = graph.nodes.iterator();
        int index = 0;
        while (it.hasNext())
        {
            Node node = new Node(subway.stations.get(index));
            assertEquals(node, it.next());
            index++;
        }
    }

    @Test
    public void shortestPath() throws IOException
    {
        //given
        SubwayGraph graph = new SubwayGraph();
        JsonToSubwayStations subwayConverter = new JsonToSubwayStations();
        SubwayStations subway = subwayConverter.readJsonObject();
        JsonToSubwayLines linesConverter = new JsonToSubwayLines();
        SubwayLines lines = linesConverter.readJsonAsMap();
        subway.connectStations(lines);
        graph.setUpGraph(subway);
        SubwayStations.Station start = subway.stations.get(0);
        SubwayStations.Station destination = subway.stations.get(32);
        LinkedList<SubwayStations.Station> expected = new LinkedList<>();
        expected.add(subway.stations.get(1));
        expected.add(subway.stations.get(105));
        expected.add(subway.stations.get(92));
        expected.add(subway.stations.get(200));
        expected.add(subway.stations.get(32));

        //when
        LinkedList<SubwayStations.Station> shortestPath = graph.shortestPath(start, destination);

        //then
        for (int index = 0; index < shortestPath.size(); index++)
        {
            assertEquals(expected.get(index), shortestPath.get(index));
        }
    }
}