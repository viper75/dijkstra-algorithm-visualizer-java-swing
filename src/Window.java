import javax.swing.*;

public class Window extends JFrame {

    public Window () {
        setSize(800, 660);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Grid grid = new Grid();
        add(grid);
        setVisible(true);
    }
}
