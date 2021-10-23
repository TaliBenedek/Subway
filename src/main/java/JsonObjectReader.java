import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonObjectReader
{
    Subway stations;

    public Subway readJsonObject() throws IOException
    {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("SubwayStations.json"));
        stations = gson.fromJson(reader, Subway.class);
        reader.close();
        return stations;
    }
}
