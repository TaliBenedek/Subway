import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubwayStations
{
    @SerializedName("features")
    List<Station> stations;

    public Map<Integer, Station> getStations()
    {
        Map<Integer, SubwayStations.Station> stationMap= new HashMap<>();
        for(SubwayStations.Station station : this.stations)
        {
            stationMap.put(station.properties.objectid, station);
        }
        return stationMap;
    }

    public static class Station
    {
        FeatureProperties properties;
        Geometry geometry;

        public String getName()
        {
            return properties.name;
        }

        public String[] getLines()
        {
            return properties.line.split("-");
        }

        public int getObjectId()
        {
            return properties.objectid;
        }

        public double getLatitude()
        {
            return geometry.coordinates.get(0);
        }

        public double getLongitude()
        {
            return geometry.coordinates.get(1);
        }
    }

    public static class FeatureProperties {
        String name;
        String line;
        int objectid;
    }

    public static class Geometry {
        List<Double> coordinates;
    }
}
