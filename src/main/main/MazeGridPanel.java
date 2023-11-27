package main;

import generator.*;
import solver.BFSSolve;
import solver.BiDFSSolve;
import solver.DFSSolve;
import solver.DijkstraSolve;
import util.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazeGridPanel extends JPanel {

    private static final long serialVersionUID = 7237062514425122227L;
    private final List<Cell> grid = new ArrayList<Cell>();
    private List<Cell> currentCells = new ArrayList<Cell>();


    /*@
        @ requires rows > 0 && cols > 0;
        @ ensures grid.size() == rows * cols;
     @*/
    /**
     * Creates a new MazeGridPanel with the specified number of rows and columns.
     *
     * @param rows the number of rows in the grid
     * @param cols the number of columns in the grid
     */
    public MazeGridPanel(int rows, int cols) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                grid.add(new Cell(x, y));
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // +1 pixel on width and height so bottom and right borders can be drawn.
        return new Dimension(Maze.COLUMNS + 1, Maze.ROWS + 1);
    }


    /*@
        @ requires index >= 0 && index <= 14;
        @ ensures \old(grid) != grid;
     @*/
    /**
     * Generates the maze using the specified algorithm.
     * @param index
     */
    public void generate(int index) {
        // switch statement for gen method read from combobox in Maze.java
        switch (index) {
            case 0:
                new AldousBroderGen(grid, this);
                break;
            case 1:
                new BinaryTreeGen(grid, this);
                break;
            case 2:
                new DFSGen(grid, this);
                break;
            case 3:
                new EllersGen(grid, this);
                break;
            case 4:
                new GrowingForestGen(grid, this);
                break;
            case 5:
                new GrowingTreeGen(grid, this);
                break;
            case 6:
                new HoustonGen(grid, this);
                ;
                break;
            case 7:
                new HuntAndKillGen(grid, this);
                break;
            case 8:
                new KruskalsGen(grid, this);
                ;
                break;
            case 9:
                new PrimsGen(grid, this);
                break;
            case 10:
                new QuadDFSGen(grid, this);
                break;
            case 11:
                new SidewinderGen(grid, this);
                break;
            case 12:
                new SpiralBacktrackerGen(grid, this);
                break;
            case 13:
                new WilsonsGen(grid, this);
                break;
            case 14:
                new ZigZagGen(grid, this);
                break;
            default:
                new GrowingTreeGen(grid, this);
                break;
        }
    }


    /*@
        @ requires index >= 0 && index <= 3;
        @ ensures \old(grid) != grid;
     @*/
    /**
     * Solves the maze using the specified algorithm.
     * @param index
     */
    public void solve(int index) {
        switch (index) {
            case 0:
                new BiDFSSolve(grid, this);
                break;
            case 1:
                new BFSSolve(grid, this);
                break;
            case 2:
                new DFSSolve(grid, this);
                break;
            case 3:
                new DijkstraSolve(grid, this);
                break;
            default:
                new DijkstraSolve(grid, this);
                break;
        }
    }


    /*@
        @ requires \old(grid) != grid;
     @*/
    /**
     * Resets the maze to its original state.
     */
    public void resetSolution() {
        for (Cell c : grid) {
            c.setDeadEnd(false);
            c.setPath(false);
            c.setDistance(-1);
            c.setParent(null);
        }
        repaint();
    }


    /*@
        @ requires current != null;
        @ ensures \old(current) != current;
     @*/
    /**
     * Sets the current cell to the specified cell.
     * @param current
     */
    public void setCurrent(Cell current) {
        if (currentCells.size() == 0) {
            currentCells.add(current);
        } else {
            currentCells.set(0, current);
        }
    }


    /*@
        @ requires currentCells != null;
        @ ensures \old(currentCells) != currentCells;
     @*/
    /**
     * Sets the current cells to the specified list of cells.
     * @param currentCells
     */
    public void setCurrentCells(List<Cell> currentCells) {
        this.currentCells = currentCells;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Cell c : grid) {
            c.draw(g);
        }
        for (Cell c : currentCells) {
            if (c != null) c.displayAsColor(g, Color.ORANGE);
        }
        grid.get(0).displayAsColor(g, Color.GREEN); // start cell
        grid.get(grid.size() - 1).displayAsColor(g, Color.YELLOW); // end or goal cell
    }
}
