import java.util.List;

public class Station
{
    List<Feature> features;

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
