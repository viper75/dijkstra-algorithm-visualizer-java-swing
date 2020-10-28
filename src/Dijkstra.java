
import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {
    public static void findShortestPath(Node[][] grid, Node start, Node end, ArrayList<Node> visitedNodesInOrder, int delay) {
        start.setDistance(0);
        ArrayList<Node> unvisitedNodes = getAllNodes(grid);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SchedulerTimerTask(unvisitedNodes, visitedNodesInOrder, grid, end),
                (long) delay, (long) delay);
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

class SchedulerTimerTask extends TimerTask {
    private ArrayList<Node> unvisitedNodes;
    private ArrayList<Node> visitedNodesInOrder;
    private Node[][] grid;
    private Node end;

    public SchedulerTimerTask(ArrayList<Node> unvisitedNodes, ArrayList<Node> visitedNodesInOrder, Node[][] grid, Node end) {
        this.unvisitedNodes = unvisitedNodes;
        this.visitedNodesInOrder = visitedNodesInOrder;
        this.grid = grid;
        this.end = end;
    }

    @Override
    public void run() {
        visitClosestNode();
    }

    private void visitClosestNode() {
        if (unvisitedNodes.size() != 0) {
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
}
