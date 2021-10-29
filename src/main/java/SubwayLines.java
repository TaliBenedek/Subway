import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubwayLines extends HashMap<String, int[]>
{
    public ArrayList<SubwayStations.Station> getConnectedStations(SubwayStations subway, SubwayStations.Station station)
    {
        ArrayList<SubwayStations.Station> connections = new ArrayList<>();
        Map<Integer, SubwayStations.Station> map = subway.getStations();
        for(Map.Entry<String, int[]> entry : this.entrySet())
        {
            int[] stations = entry.getValue();
            for(int index = 0; index < stations.length; index++)
            {
                if(stations[index] == station.getObjectId())
                {
                    //not first
                    if(index != 0)
                    {
                        SubwayStations.Station connection = (SubwayStations.Station)map.get(stations[index - 1]);
                        if(!connections.contains(connection))
                        {
                            connections.add(connection);
                        }
                    }

                    //not last
                    if((index + 1) != stations.length)
                    {
                        SubwayStations.Station connection = (SubwayStations.Station)map.get(stations[index + 1]);
                        if(!connections.contains(connection))
                        {
                            connections.add(connection);
                        }
                    }
                }
            }
        }
        return connections;
    }
}
