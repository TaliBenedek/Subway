import java.util.List;

public class Subway
{
    List<Feature> features;

    public String getName(String objectId)
    {
        for(int index = 0; index < features.size(); index++)
        {
            if(this.features.get(index).getObjectId().equals(objectId))
            {
                return this.features.get(index).getName();
            }
        }
        return null;
    }

    public String getObjectId(String name)
    {
        for(int index = 0; index < features.size(); index++)
        {
            if(this.features.get(index).getName().equals(name))
            {
                return this.features.get(index).getObjectId();
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
