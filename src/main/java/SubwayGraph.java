import java.util.*;

public class SubwayGraph
{
    Set<Node> nodes = new HashSet<>();
    Map<SubwayStations.Station, Node> map = new HashMap<>();

    public void setUpGraph(SubwayStations subway)
    {
        for (SubwayStations.Station station : subway.stations)
        {
            Node node = null;
            if (!map.containsKey(station))
            {
                node = new Node(station);
                map.put(station, node);
                nodes.add(node);
                for (SubwayStations.Station connection : station.getConnections())
                {
                    if (!map.containsKey(connection))
                    {
                        Node connectionNode = new Node(connection);
                        map.put(connection, connectionNode);
                        nodes.add(connectionNode);
                        node.addNeighbor(connectionNode, 1);
                        connectionNode.addNeighbor(node, 1);
                    }
                }
            }
        }
    }

    public LinkedList<SubwayStations.Station> shortestPath(SubwayStations.Station source, SubwayStations.Station destination)
    {
        Node sourceNode = map.get(source);
        Node destinationNode = map.get(destination);
        sourceNode.setDistance(0);
        Set<Node> visitedNodes = new HashSet<>();
        PriorityQueue<Node> unvisitedQ = new PriorityQueue<>();
        unvisitedQ.add(sourceNode);
        while (!unvisitedQ.isEmpty())
        {
            Node currentNode = unvisitedQ.poll();
            for (Map.Entry<Node, Integer> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet())
            {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!visitedNodes.contains(adjacentNode))
                {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unvisitedQ.add(adjacentNode);
                }
            }
            visitedNodes.add(currentNode);
            if(visitedNodes.contains(destinationNode))
            {
                break;
            }
        }
        return (LinkedList<SubwayStations.Station>) destinationNode.getShortestPath();
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
