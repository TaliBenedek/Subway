import java.util.*;

public class Node implements Comparable<Node>
{
    private SubwayStations.Station station;
    private double distance = Integer.MAX_VALUE; //distance from source node
    private List<SubwayStations.Station> shortestPath = new LinkedList<>();
    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(SubwayStations.Station station)
    {
        this.station = station;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Node node = (Node) o;
        return Double.compare(node.distance, distance) == 0 && station.equals(node.station) && adjacentNodes.equals(node.adjacentNodes);
    }

//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(station, distance, shortestPath, adjacentNodes);
//    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setShortestPath(List<SubwayStations.Station> shortestPath)
    {
        this.shortestPath = shortestPath;
    }

    public void addToPath(Node node)
    {
        this.shortestPath.add(node.getStation());
    }

    public List<SubwayStations.Station> getShortestPath()
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

    @Override
    public int compareTo(Node o)
    {
        return Double.compare(this.distance, o.distance);
    }
}
