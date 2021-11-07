import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonToSubwayLines
{
    public SubwayLines readJsonAsMap() throws IOException
    {
        Gson gson = new Gson();
        InputStream in = ClassLoader.getSystemResourceAsStream("SubwayLines.json");
        InputStreamReader reader = new InputStreamReader(in);
        SubwayLines lines = gson.fromJson(reader, SubwayLines.class);
        reader.close();
        return lines;
    }
}
