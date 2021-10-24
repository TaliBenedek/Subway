import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonToSubwayStations
{
    SubwayStations stations;

    public SubwayStations readJsonObject() throws IOException
    {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("SubwayStations.json"));
        stations = gson.fromJson(reader, SubwayStations.class);
        reader.close();
        return stations;
    }
}
