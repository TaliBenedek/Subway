import com.google.gson.annotations.SerializedName;

import java.util.*;
import java.util.function.ToDoubleFunction;

public class SubwayStations
{
    @SerializedName("features")
    List<Station> stations;

    public static class Station
    {
        FeatureProperties properties;
        Geometry geometry;
        List<Station> connections;

        public Station()
        {
            this.connections = new ArrayList<>();
        }

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

        public double getDistance(double latitude, double longitude)
        {
            return Math.sqrt(Math.pow((this.getLatitude() - latitude), 2) +
                    Math.pow((this.getLongitude() - longitude), 2));
        }

        public List<Station> getConnections()
        {
            return connections;
        }

        public void connect(Station station)
        {
            if(!this.connections.contains(station))
            {
                this.connections.add(station);
            }
            if(!station.connections.contains(this))
            {
                station.connections.add(this);
            }
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }
            Station station = (Station) o;
            return this.getObjectId()  == station.getObjectId();
        }

        @Override
        public String toString()
        {
            return "Name: " + properties.name + " ObjectId: " + properties.objectid;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(properties.objectid);
        }
    }

    public static class FeatureProperties
    {
        String name;
        String line;
        int objectid;
    }

    public static class Geometry
    {
        List<Double> coordinates;
    }

    public Map<Integer, Station> getStations()
    {
        Map<Integer, SubwayStations.Station> stationMap = new HashMap<>();
        for (SubwayStations.Station station : this.stations)
        {
            stationMap.put(station.properties.objectid, station);
        }
        return stationMap;
    }

    public void connectStations(SubwayLines lines)
    {
        Map<Integer, Station> map = this.getStations();
        for (String line : lines.keySet())
        {
            int[] stations = lines.get(line);
            for (int i = 0; i < stations.length - 1; i++)
            {
                map.get(stations[i]).connect(map.get(stations[i + 1]));
            }
        }
    }

    public Station getClosestStation(double latitude, double longitude)
    {
//        Station closestStation = stations.stream()
//                .min(Comparator.comparingDouble(station -> station.getDistance(latitude, longitude)))
//                .get();
        Station closestStation = null;
        double minDistance = Double.MAX_VALUE;
        for(Station station: this.stations)
        {
            double distance = station.getDistance(latitude, longitude);
            if(distance < minDistance)
            {
                closestStation = station;
                minDistance = distance;
            }
        }
        return closestStation;
    }
}
