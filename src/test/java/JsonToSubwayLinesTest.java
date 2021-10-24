import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class JsonToSubwayLinesTest
{
    @Test
    public void readJsonAsMap() throws IOException
    {
        //given
        JsonToSubwayLines jsonConverter = new JsonToSubwayLines();
        SubwayStations stations = mock(SubwayStations.class);
        doReturn("1").when(stations).getObjectId("Astor Pl");
        doReturn("23").when(stations).getObjectId("Mets - Willets Point");
        doReturn("Bleecker St").when(stations).getName("457");
        doReturn("Union Sq - 14th St").when(stations).getName("105");
        doReturn("Junction Blvd").when(stations).getName("24");
        doReturn("Flushing - Main St").when(stations).getName("25");
        doReturn("111th St").when(stations).getName("190");

        //when
        SubwayLines lines = jsonConverter.readJsonAsMap();

        //then
        Assert.assertArrayEquals(new String[]{"Bleecker St", "Union Sq - 14th St"}, lines.getConnectedStations("Astor Pl", stations));
        Assert.assertArrayEquals(new String[]{"Junction Blvd", "Flushing - Main St", "111th St"}, lines.getConnectedStations("Mets - Willets Point", stations));
    }
}