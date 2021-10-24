import java.util.List;

public class SubwayStations
{
    List<Feature> features;

    public String getName(String objectId)
    {
        for(Feature feature: features)
        {
            if(feature.getObjectId().equals(objectId))
            {
                return feature.getName();
            }
        }
        return null;
    }

    public String getObjectId(String name)
    {
        for(Feature feature: features)
        {
            if(feature.getName().equals(name))
            {
                return feature.getObjectId();
            }
        }
        return null;
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

        public String getObjectId()
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
        String objectid;
    }

    public static class Geometry {
        List<Double> coordinates;
    }
}
