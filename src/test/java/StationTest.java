import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class StationTest
{
    @Test
    public void getName() throws IOException
    {
        //given
        //create Gson instance
        Gson gson = new Gson();

        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get("SubwayStations.json"));
        // convert JSON string to User object
        Station station = gson.fromJson(reader, Station.class);

        //when
        //name of the first station
        String name0 = station.features.get(0).getName();
        //name of the last station
        String name472 = station.features.get(472).getName();

        //then
        assertEquals(name0, "Astor Pl");
        assertEquals(name472, "96th St");

        // close reader
        reader.close();
    }

    @Test
    public void getLines() throws IOException
    {
        //given
        //create Gson instance
        Gson gson = new Gson();

        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get("SubwayStations.json"));
        // convert JSON string to User object
        Station station = gson.fromJson(reader, Station.class);

        //when
        //lines of the first station
        String[] lines0 = station.features.get(0).getLines();
        //lines of the last station
        String[] lines472 = station.features.get(472).getLines();

        //then
        assertArrayEquals(lines0, new String[]{"4", "6", "6 Express"});
        assertArrayEquals(lines472, new String[]{"Q"});

        // close reader
        reader.close();
    }

    @Test
    public void getObjectId() throws IOException
    {
        //given
        //create Gson instance
        Gson gson = new Gson();

        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get("SubwayStations.json"));
        // convert JSON string to User object
        Station station = gson.fromJson(reader, Station.class);

        //when
        //objectId of the first station
        int objectId0 = station.features.get(0).getObjectId();
        //objectId of the last station
        int objectId472 = station.features.get(472).getObjectId();

        //then
        assertEquals(objectId0, 1);
        assertEquals(objectId472, 643);

        // close reader
        reader.close();
    }

    @Test
    public void getLatitude() throws IOException
    {
        //given
        //create Gson instance
        Gson gson = new Gson();

        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get("SubwayStations.json"));
        // convert JSON string to User object
        Station station = gson.fromJson(reader, Station.class);

        //when
        //latitude of the first station
        double latitude1 = station.features.get(0).getLatitude();
        //name of the last station
        double latitude472 = station.features.get(472).getLatitude();

        //then
        assertEquals(latitude1, -73.99, .01);
        assertEquals(latitude472, -73.95, .01);

        // close reader
        reader.close();
    }

    @Test
    public void getLongitude() throws IOException
    {
        //given
        //create Gson instance
        Gson gson = new Gson();

        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get("SubwayStations.json"));
        // convert JSON string to User object
        Station station = gson.fromJson(reader, Station.class);

        //when
        //latitude of the first station
        double longitude1 = station.features.get(0).getLongitude();
        //name of the last station
        double longitude472 = station.features.get(472).getLongitude();

        //then
        assertEquals(longitude1, 40.73, .01);
        assertEquals(longitude472, 40.78, .01);

        // close reader
        reader.close();
    }
}