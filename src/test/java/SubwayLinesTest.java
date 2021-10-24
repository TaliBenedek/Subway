import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class SubwayLinesTest
{
    @Test
    public void getConnectedStations() throws IOException
    {
        //given
        JsonToSubwayLines jsonConverter = mock(JsonToSubwayLines.class);
        SubwayLines lines = mock(SubwayLines.class);
        doReturn(lines).when(jsonConverter).readJsonAsMap();
        //TODO doReturn for lines.entrySet
        SubwayStations stations = mock(SubwayStations.class);
        doReturn("1").when(stations).getObjectId("Astor Pl");
        doReturn("23").when(stations).getObjectId("Mets - Willets Point");
        doReturn("Bleecker St").when(stations).getName(457);
        doReturn("Union Sq - 14th St").when(stations).getName(105);
        doReturn("Junction Blvd").when(stations).getName(24);
        doReturn("Flushing - Main St").when(stations).getName(25);
        doReturn("111th St").when(stations).getName(190);

        //when
        String[] astorConnections = lines.getConnectedStations("Astor Pl", stations);
        String[] metsConnections = lines.getConnectedStations("Mets - Willets Point", stations);

        //then
        Assert.assertArrayEquals(new String[]{"Bleecker St", "Union Sq - 14th St"}, astorConnections);
        Assert.assertArrayEquals(new String[]{"Junction Blvd", "Flushing - Main St", "111th St"}, metsConnections);
    }
}
