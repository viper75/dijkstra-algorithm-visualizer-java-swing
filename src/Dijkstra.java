import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Dijkstra {
    public static void findShortestPath (Node[][] grid, Node start, Node end, ArrayList<Node> visitedNodesInOrder) {
        start.setDistance(0);
        ArrayList<Node> unvisitedNodes = getAllNodes(grid);

        while (unvisitedNodes.size() != 0) {
            Collections.sort(unvisitedNodes);
            Node closetNode = unvisitedNodes.get(0);

            if (closetNode.getDistance() == Integer.MAX_VALUE)
                return;

            closetNode.setVisited(true);
            visitedNodesInOrder.add(closetNode);

            if (closetNode == end)
                return;

            unvisitedNodes.remove(closetNode);
            updateUnvisitedNeighbours(closetNode, grid);
        }
    }

    private static void updateUnvisitedNeighbours(Node node, Node[][] grid) {
        ArrayList<Node> unvisitedNeighbours = getUnvisitedNeighbours(node, grid);

        unvisitedNeighbours.forEach(unvisitedNeighbour -> {
            unvisitedNeighbour.setDistance(node.getDistance() + 1);
            unvisitedNeighbour.setPrevNode(node);
        });
    }

    private static ArrayList<Node> getUnvisitedNeighbours(Node node, Node[][] grid) {
        ArrayList<Node> neighbours = new ArrayList<>();

        if (node.getRow() > 0)
            neighbours.add(grid[node.getRow() - 1][node.getCol()]);

        if (node.getRow() < grid.length - 1)
            neighbours.add(grid[node.getRow() + 1][node.getCol()]);

        if (node.getCol() > 0)
            neighbours.add(grid[node.getRow()][node.getCol() - 1]);

        if (node.getCol() < grid[0].length - 1)
            neighbours.add(grid[node.getRow()][node.getCol() + 1]);

        return (ArrayList<Node>) neighbours.stream().filter(neighbour -> !neighbour.isVisited())
                .collect(Collectors.toList());
    }

    private static ArrayList<Node> getAllNodes(Node[][] grid) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node[] node : grid) {
            nodes.addAll(Arrays.asList(node));
        }
        return nodes;
    }

    public static ArrayList<Node> getNodesInShortestPathOrder(Node end) {
        ArrayList<Node> nodesInShortestPathOrder = new ArrayList<>();
        Node current = end;
        while (current != null) {
            nodesInShortestPathOrder.add(current);
            current = current.getPrevNode();
        }
        return nodesInShortestPathOrder;
    }
}
