import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class PathServlet extends HttpServlet {

    private final Gson gson;
    private final SubwayStations subway;
    private final SubwayGraph subwayGraph;

    public PathServlet() throws IOException {
        JsonToSubwayStations subwayConverter = new JsonToSubwayStations();
        subway = subwayConverter.readJsonObject();

        JsonToSubwayLines linesConverter = new JsonToSubwayLines();
        SubwayLines lines = linesConverter.readJsonAsMap();
        subway.connectStations(lines);

        subwayGraph = new SubwayGraph(subway);
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException
    {
        PrintWriter out = response.getWriter();

        double lat1 = Double.parseDouble(request.getParameter("lat1"));
        double lon1 = Double.parseDouble(request.getParameter("lon1"));
        double lat2 = Double.parseDouble(request.getParameter("lat2"));
        double lon2 = Double.parseDouble(request.getParameter("lon2"));
        SubwayStations.Station start = subway.getClosestStation(lat1, lon1);
        SubwayStations.Station end = subway.getClosestStation(lat2, lon2);

        List<SubwayStations.Station> stationList = subwayGraph.shortestPath(start, end);
        String json = gson.toJson(stationList);

        out.println(json);
    }

}
