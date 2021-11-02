import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

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
        while(it.hasNext())
        {
            Node node = new Node(subway.stations.get(index));
            assertEquals(node, it.next());
            index++;
        }
    }

    @Test
    public void shortestPath()
    {
        //given

        //when

        //then
    }
}