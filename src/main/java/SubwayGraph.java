import java.util.*;

public class SubwayGraph
{
    Map<SubwayStations.Station, Node> map = new HashMap<>();
    private final int DISTANCE_BETWEEN_STATIONS = 1;

    public SubwayGraph(SubwayStations subway)
    {
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
        List<Node> unvisitedNodes = new ArrayList<>();
        unvisitedNodes.add(sourceNode);
        while (!unvisitedNodes.isEmpty())
        {
            Node currentNode = getShortestDistanceNode(unvisitedNodes);
            unvisitedNodes.remove(currentNode);
            for (Node neighbor : currentNode.getNeighbors())
            {
                if (!visitedNodes.contains(neighbor))
                {
                    calculateMinimumDistance(neighbor, DISTANCE_BETWEEN_STATIONS, currentNode);
                    unvisitedNodes.add(neighbor);
                }
            }
            visitedNodes.add(currentNode);
            if(visitedNodes.contains(destinationNode))
            {
                break;
            }
        }
        return backtrack(sourceNode, destinationNode);
    }

    private Node getShortestDistanceNode(List<Node> unvisitedNodes)
    {
        Node minNode = null;
        double minDistance = Double.MAX_VALUE;
        for(Node node: unvisitedNodes)
        {
            double distance = node.getDistance();
            if(distance < minDistance)
            {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    private static void calculateMinimumDistance(Node adjacentNode, double edgeWeight, Node sourceNode)
    {
        double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < adjacentNode.getDistance())
        {
            adjacentNode.setDistance(sourceDistance + edgeWeight);
            adjacentNode.setPrevious(sourceNode);
        }
    }

    private List<SubwayStations.Station> backtrack(Node source, Node destination)
    {
        List<SubwayStations.Station> shortestPath = new ArrayList<>();
        Node previous = destination.getPrevious();
        shortestPath.add(previous.getStation());
        while(previous != source)
        {
            previous = previous.getPrevious();
            shortestPath.add(previous.getStation());
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}
