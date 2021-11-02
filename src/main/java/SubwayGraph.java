import java.util.*;

public class SubwayGraph
{
    private Set<Node> nodes = new HashSet<>();
    private Map<SubwayStations.Station, Node> map = new HashMap<>();

    public SubwayGraph()
    {

    }

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
            }
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

    public LinkedList<SubwayStations.Station> shortestPath(SubwayStations.Station source, SubwayStations.Station destination)
    {
        Node sourceNode = map.get(source);
        Node destinationNode = map.get(destination);
        sourceNode.setDistance(0);
        Set<Node> visitedNodes = new HashSet<>();
        PriorityQueue<Node> unvisitedQ = new PriorityQueue();
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

    private static void calculateMinimumDistance(Node evaluationNode, double edgeWeight, Node sourceNode)
    {
        double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance())
        {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            evaluationNode.addToPath(sourceNode);
        }
    }
}
