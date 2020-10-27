import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JFrame {

    private final int NODE_SIZE = 20;
    private final int OFFSET = 20;

    private Node[][] grid = new Node[30][30];
    private Node start;
    private Node end;
    private ArrayList<Node> visitedNodesInOrder = new ArrayList<>();

    public Grid () {
        initGrid();
        setSize((NODE_SIZE * grid[0].length) + (OFFSET * 2), (NODE_SIZE * grid.length) + (OFFSET * 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        start = grid[1][29];
        end = grid[29][20];
        Dijkstra.findShortestPath(grid, start, end, visitedNodesInOrder);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                g.setColor(Color.BLACK);
                g.drawRect((NODE_SIZE * col) + OFFSET, (NODE_SIZE * row) + (OFFSET * 2), NODE_SIZE, NODE_SIZE);
            }
        }

        //Paint visited path
        g.setColor(Color.ORANGE);
        visitedNodesInOrder.forEach(node -> {
            g.fillRect((NODE_SIZE * node.getCol()) + OFFSET, (NODE_SIZE * node.getRow()) + (OFFSET * 2), NODE_SIZE, NODE_SIZE);
        });

        //Paint path
        g.setColor(Color.YELLOW);
        Dijkstra.getNodesInShortestPathOrder(end).forEach(node -> {
            g.fillRect((NODE_SIZE * node.getCol()) + OFFSET, (NODE_SIZE * node.getRow()) + (OFFSET * 2), NODE_SIZE, NODE_SIZE);
        });

        //Draw start node
        g.setColor(Color.RED);
        g.fillRect((NODE_SIZE * start.getCol()) + OFFSET, (NODE_SIZE * start.getRow()) + (OFFSET * 2), NODE_SIZE, NODE_SIZE);

        //Draw end node
        g.setColor(Color.GREEN);
        g.fillRect((NODE_SIZE * end.getCol()) + OFFSET, (NODE_SIZE * end.getRow()) + (OFFSET * 2), NODE_SIZE, NODE_SIZE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Grid grid = new Grid();
            grid.setVisible(true);
        });
    }

    private void initGrid() {
        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Node(row, col);
            }
        }
    }
}
