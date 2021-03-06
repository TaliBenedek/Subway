import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonToSubwayStations
{
    public SubwayStations readJsonObject() throws IOException
    {
        Gson gson = new Gson();
        InputStream in = ClassLoader.getSystemResourceAsStream("SubwayStations.json");
        InputStreamReader reader = new InputStreamReader(in);
        SubwayStations stations = gson.fromJson(reader, SubwayStations.class);
        reader.close();
        return stations;
    }
}
