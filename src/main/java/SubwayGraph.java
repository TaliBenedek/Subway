import java.util.*;

public class SubwayGraph
{
    private Set<Node> nodes = new HashSet<>();
    private Map<SubwayStations.Station, Node> map = new HashMap<>();

    public SubwayGraph(SubwayStations subway)
    {
        for(SubwayStations.Station station: subway.stations)
        {
            Node node = null;
            if(!map.containsKey(station))
            {
                node = new Node(station);
                map.put(station, node);
                nodes.add(node);
            }
            for(SubwayStations.Station connection: station.getConnections())
            {
                if(!map.containsKey(connection))
                {
                    Node connectionNode = new Node(connection);
                    map.put(connection, connectionNode);
                    nodes.add(connectionNode);
                    node.addNeighbor(connectionNode,1);
                    connectionNode.addNeighbor(node,1);
                }
            }
        }
    }

    public void addNode(Node node)
    {
        nodes.add(node);
    }

    public void Dijkstra(SubwayStations.Station source, SubwayStations.Station destination)
    {
        Node sourceNode = map.get(source);
        Node destinationNode = map.get(destination);
        sourceNode.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        PriorityQueue<Node> pQueue = new PriorityQueue();
        pQueue.add(sourceNode);
        while (!pQueue.isEmpty())
        {
            Node currentNode = pQueue.poll();
            for (Map.Entry<Node, Integer> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet())
            {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode))
                {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    pQueue.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private static void calculateMinimumDistance(Node evaluationNode, double edgeWeigh, Node sourceNode)
    {
        double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance())
        {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
