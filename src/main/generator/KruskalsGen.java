package generator;

import main.Maze;
import main.MazeGridPanel;
import util.Cell;
import util.DisjointSets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


// Slightly different as it loops through cells randomly and then each wall of the cell. Not through each wall randomly.

public class KruskalsGen {

    private final Stack<Cell> stack = new Stack<Cell>();
    private final DisjointSets disjointSet = new DisjointSets();
    private final List<Cell> grid;
    private Cell current;

    public KruskalsGen(List<Cell> grid, MazeGridPanel panel) {
        this.grid = grid;
        current = grid.get(0);

        for (int i = 0; i < grid.size(); i++) {
            grid.get(i).setId(i);
            disjointSet.createSet(grid.get(i).getId());
        }

        stack.addAll(grid);

        final Timer timer = new Timer(Maze.speed, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!grid.parallelStream().allMatch(c -> c.isVisited())) {
                    carve();
                } else {
                    current = null;
                    Maze.generated = true;
                    timer.stop();
                }
                panel.setCurrent(current);
                panel.repaint();
                timer.setDelay(Maze.speed);
            }
        });
        timer.start();
    }

    private void carve() {
        current = stack.pop();
        current.setVisited(true);

        List<Cell> neighs = current.getAllNeighbours(grid);

        for (Cell n : neighs) {
            if (disjointSet.findSet(current.getId()) != disjointSet.findSet(n.getId())) {
                current.removeWalls(n);
                disjointSet.union(current.getId(), n.getId());
            }
        }

        Collections.shuffle(stack);
    }
}