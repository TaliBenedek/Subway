import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node
{
    private SubwayStations.Station station;
    private double distance = Integer.MAX_VALUE; //distance from source node
    private List<Node> shortestPath = new LinkedList<>();
    Map<Node, Integer> adjacentNodes = new HashMap<>();
    private SubwayStations.Station previous;

    public Node(SubwayStations.Station station)
    {
        this.station = station;
        this.previous = null;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setShortestPath(List<Node> shortestPath)
    {
        this.shortestPath = shortestPath;
    }

    public List<Node> getShortestPath()
    {
        return shortestPath;
    }

    public Map<Node, Integer> getAdjacentNodes()
    {
        return adjacentNodes;
    }

    public void addNeighbor(Node neighbor, int distance)
    {
        adjacentNodes.put(neighbor, distance);
    }

    public SubwayStations.Station getStation()
    {
        return station;
    }

    public void setPrevious(SubwayStations.Station station)
    {
        this.previous = station;
    }

    public SubwayStations.Station getPrevious(SubwayStations.Station station)
    {
        return this.previous;
    }
}
