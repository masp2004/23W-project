package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Hashtable;

public class Maze {

    public static final int COLUMNS = 800;
    public static final int ROWS = COLUMNS; // best to keep these the same. variable is only created for readability.
    public static final int CELL_SIZE = 20;
    private static final String[] GENERATION_METHODS = {"0. Aldous-Broder", "1. Binary Tree",
            "2. DFS", "3. Eller's", "4. Growing Forest", "5. Growing Tree", "6. Houston's",
            "7. Hunt & Kill", "8. Kruskal's", "9. Prim's", "10. Quad-directional DFS", "11. Sidewinder",
            "12. Spiral Backtracker", "13. Wilson's", "14. Zig-Zag"};
    private static final String[] SOLVING_METHODS = {"0. Bi-directional DFS", "1. BFS", "2. DFS", "3. Dijkstra's"};
    public static int speed = 1;
    public static boolean generated, solved;
    private int cols, rows;
    private MazeGridPanel grid;

    public Maze() {
        cols = Math.floorDiv(COLUMNS, CELL_SIZE);
        rows = cols;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                         | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                createAndShowGUI();
            }
        });
    }

    public static void main(String[] args) {
        new Maze();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Java Mazes");

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        frame.setContentPane(container);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mazeBorder = getjPanel();

        container.add(mazeBorder);

        createControlsArea(container, grid, frame);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel getjPanel() {
        grid = new MazeGridPanel(rows, cols);
        grid.setBackground(Color.BLACK);

        JPanel mazeBorder = new JPanel();
        final int BORDER_SIZE = 20;
        mazeBorder.setBounds(0, 0, COLUMNS + BORDER_SIZE, ROWS + BORDER_SIZE);
        mazeBorder.setBackground(Color.BLACK);
        mazeBorder.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        mazeBorder.add(grid);
        return mazeBorder;
    }

    private void createControlsArea(JPanel container, MazeGridPanel grid, JFrame frame) {
        CardLayout cardLayout = new CardLayout();

        JButton runButton = new JButton("Run");
        JButton solveButton = new JButton("Solve");
        JButton resetButton = new JButton("Reset");
        JButton solveAgainButton = new JButton("Solve Again");

        JComboBox<String> genMethodsComboBox = new JComboBox<>(GENERATION_METHODS);
        JComboBox<String> solveMethodsComboBox = new JComboBox<>(SOLVING_METHODS);

        // may need to comment these out if running on small resolution!!!
        genMethodsComboBox.setMaximumRowCount(genMethodsComboBox.getModel().getSize());
        solveMethodsComboBox.setMaximumRowCount(solveMethodsComboBox.getModel().getSize());

        JSlider initialSpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 40, 1);
        JSlider genSpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 40, 1);
        JSlider solveSpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 40, 1);

        Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put(1, new JLabel("Fast"));
        labels.put(40, new JLabel("Slow"));

        initialSpeedSlider.setLabelTable(labels);
        initialSpeedSlider.setInverted(true);
        initialSpeedSlider.setPaintLabels(true);

        genSpeedSlider.setLabelTable(labels);
        genSpeedSlider.setInverted(true);
        genSpeedSlider.setPaintLabels(true);

        solveSpeedSlider.setLabelTable(labels);
        solveSpeedSlider.setInverted(true);
        solveSpeedSlider.setPaintLabels(true);

        // Create the card panels.

        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();
        card1.setLayout(new GridBagLayout());
        card2.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        ;

        c.insets = new Insets(5, 0, 5, 18);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.7;
        c.gridx = 0;
        c.gridy = 0;
        card1.add(genMethodsComboBox, c);
        card2.add(solveMethodsComboBox, c);


        c.gridheight = 2;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 0;
        card1.add(runButton, c);
        card2.add(solveButton, c);

        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        card1.add(initialSpeedSlider, c);
        card2.add(genSpeedSlider, c);

        JPanel card3 = new JPanel();
        card3.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        card3.add(solveAgainButton, c);
        c.gridx = 0;
        c.gridy = 1;
        card3.add(resetButton, c);
        c.gridx = 0;
        c.gridy = 2;
        card3.add(solveSpeedSlider, c);

        // Create the panel that contains the cards.
        JPanel cards = new JPanel(cardLayout);
        cards.setBorder(new EmptyBorder(0, 20, 0, 0));
        cards.setOpaque(false);
        cards.add(card1, "gen");
        cards.add(card2, "solve");
        cards.add(card3, "reset");

        container.add(cards);

        genSpeedSlider.addChangeListener(event -> {
            speed = genSpeedSlider.getValue();
        });

        solveSpeedSlider.addChangeListener(event -> {
            speed = solveSpeedSlider.getValue();
        });

        runButton.addActionListener(event -> {
            speed = initialSpeedSlider.getValue();
            generated = false;
            solved = false;
            grid.generate(genMethodsComboBox.getSelectedIndex());
            genSpeedSlider.setValue(speed);
            cardLayout.next(cards);
        });

        solveButton.addActionListener(event -> {
            if (generated) {
                grid.solve(solveMethodsComboBox.getSelectedIndex());
                cardLayout.last(cards);
                solveSpeedSlider.setValue(speed);
            } else {
                JOptionPane.showMessageDialog(frame, "Please wait until the maze has been generated.");
            }
        });

        solveAgainButton.addActionListener(event -> {
            if (solved) {
                grid.resetSolution();
                cardLayout.show(cards, "solve");
            } else {
                JOptionPane.showMessageDialog(frame, "Please wait until the maze has been solved.");
            }
        });

        resetButton.addActionListener(event -> createAndShowGUI());
    }
}
