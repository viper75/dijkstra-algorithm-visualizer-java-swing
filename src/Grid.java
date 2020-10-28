import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Grid extends JPanel implements ActionListener {

    private static final int DELAY = 50;
    private final Timer timer = new Timer(DELAY, this);
    private final int NODE_SIZE = 20;

    private Node[][] grid = new Node[30][30];
    private Node start;
    private Node end;
    private ArrayList<Node> visitedNodesInOrder = new ArrayList<>();

    public Grid () {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initGrid();
        timer.start();

        start = grid[28][1];
        end = grid[20][28];
        Dijkstra.findShortestPath(grid, start, end, visitedNodesInOrder, DELAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Paint visited path
        g.setColor(Color.CYAN);
        for (int i = 0; i < visitedNodesInOrder.size(); i++) {
            g.fillRect(NODE_SIZE * visitedNodesInOrder.get(i).getCol(), NODE_SIZE * visitedNodesInOrder.get(i).getRow(), NODE_SIZE, NODE_SIZE);
        }

        //Paint path
        g.setColor(Color.YELLOW);
        Dijkstra.getNodesInShortestPathOrder(end).forEach(node -> {
            g.fillRect(NODE_SIZE * node.getCol(), NODE_SIZE * node.getRow(), NODE_SIZE, NODE_SIZE);
        });

        //Draw start node
        g.setColor(Color.RED);
        g.fillRect(NODE_SIZE * start.getCol(), NODE_SIZE * start.getRow(), NODE_SIZE, NODE_SIZE);

        //Draw end node
        g.setColor(Color.GREEN);
        g.fillRect(NODE_SIZE * end.getCol(), NODE_SIZE * end.getRow(), NODE_SIZE, NODE_SIZE);

        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                g.setColor(Color.BLACK);
                g.drawRect(NODE_SIZE * col, NODE_SIZE * row, NODE_SIZE, NODE_SIZE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    private void initGrid() {
        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Node(row, col);
            }
        }
    }
}
