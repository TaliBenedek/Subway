import java.util.List;

public class SubwayStations
{
    List<Feature> features;

    public String getName(int objectId)
    {
        for(Feature feature: features)
        {
            if(feature.getObjectId() == objectId)
            {
                return feature.getName();
            }
        }
        return null;
    }

    public int getObjectId(String name)
    {
        for(Feature feature: features)
        {
            if(feature.getName().equals(name))
            {
                return feature.getObjectId();
            }
        }
        return -1;
    }

    public static class Feature
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
