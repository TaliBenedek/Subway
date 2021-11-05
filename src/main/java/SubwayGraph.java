import java.util.*;

public class SubwayGraph
{
    Map<SubwayStations.Station, Node> map;
    private final int DISTANCE_BETWEEN_STATIONS = 1;

    public SubwayGraph(SubwayStations subway)
    {
        map = new HashMap<>();
        for (SubwayStations.Station station : subway.stations)
        {
            Node node = new Node(station);
            map.put(station, node);
        }
        for(Map.Entry<SubwayStations.Station, Node> entry: map.entrySet())
        {
            SubwayStations.Station currentStation = entry.getKey();
            Node currentNode = entry.getValue();
            for(SubwayStations.Station connection: currentStation.getConnections())
            {
                Node connectionNode = map.get(connection);
                if(!currentNode.getNeighbors().contains(connectionNode))
                {
                    currentNode.addNeighbor(connectionNode);
                }
            }
        }
    }

    public List<SubwayStations.Station> shortestPath(SubwayStations.Station source, SubwayStations.Station destination)
    {
        Node sourceNode = map.get(source);
        Node destinationNode = map.get(destination);
        sourceNode.setDistance(0);
        List<Node> visitedNodes = new ArrayList<>();
        PriorityQueue<Node> unvisitedQ = new PriorityQueue<>();
        unvisitedQ.add(sourceNode);
        while (!unvisitedQ.isEmpty())
        {
            Node currentNode = unvisitedQ.poll();
            for (Node neighbor : currentNode.getNeighbors())
            {
                if (!visitedNodes.contains(neighbor))
                {
                    calculateMinimumDistance(neighbor, DISTANCE_BETWEEN_STATIONS, currentNode);
                    unvisitedQ.add(neighbor);
                }
            }
            visitedNodes.add(currentNode);
            if(visitedNodes.contains(destinationNode))
            {
                break;
            }
        }
        return destinationNode.getShortestPath();
    }

    private static void calculateMinimumDistance(Node adjacentNode, double edgeWeight, Node currentNode)
    {
        double sourceDistance = currentNode.getDistance();
        if (sourceDistance + edgeWeight < adjacentNode.getDistance())
        {
            adjacentNode.setDistance(sourceDistance + edgeWeight);
            adjacentNode.addToPath(currentNode);
        }
    }
}
