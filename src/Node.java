public class Node implements Comparable<Node> {
    private int row;
    private int col;
    private Integer distance;
    private Node prevNode;
    private boolean visited;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        distance = Integer.MAX_VALUE;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public int compareTo(Node node) {
        return distance.compareTo(node.distance);
    }
}
