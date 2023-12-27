package util;

import main.Maze;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a cell in the maze.
 */
public class Cell {

    private int x, y, distance, id;

    private Cell parent;

    private boolean visited = false;
    private boolean path = false;
    private boolean deadEnd = false;

    private boolean[] walls = {true, true, true, true};

    /**
     * Constructor for Cell
     *
     * @param x
     * @param y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = -1;
    }

    /**
     * getWalls returns the walls of the cell
     * @return boolean[]
     */
    public boolean[] getWalls() {
        return walls;
    }

    /**
     * setWalls sets the walls of the cell
     * @param walls
     */
    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    /**
     * getX returns the x coordinate of the cell
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * getY returns the y coordinate of the cell
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * setX sets the x coordinate of the cell
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * setId sets the id of the cell
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setX sets the x coordinate of the cell
     * @return boolean
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * setVisited sets the visited status of the cell
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * isDeadEnd returns the deadEnd status of the cell
     * @return boolean
     */
    public boolean isDeadEnd() {
        return deadEnd;
    }

    /**
     * setDeadEnd sets the deadEnd status of the cell
     * @param deadEnd
     */
    public void setDeadEnd(boolean deadEnd) {
        this.deadEnd = deadEnd;
    }

    /**
     * isPath returns the path status of the cell
     * @return boolean
     */
    public boolean isPath() {
        return path;
    }

    /**
     * setPath sets the path status of the cell
     * @param path
     */
    public void setPath(boolean path) {
        this.path = path;
    }

    /**
     * getDistance returns the distance of the cell
     * @return int
     */
    public int getDistance() {
        return distance;
    }

    /**
     * setDistance sets the distance of the cell
     * @param distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * getParent returns the parent of the cell
     * @return parent
     */
    public Cell getParent() {
        return parent;
    }

    /**
     * setParent sets the parent of the cell
     * @param parent
     */
    public void setParent(Cell parent) {
        this.parent = parent;
    }

    /**
     * draw draws the cell
     * @param g
     */
    public void draw(Graphics g) {
        int x2 = x * Maze.CELL_SIZE;
        int y2 = y * Maze.CELL_SIZE;

        if (visited) {
            g.setColor(Color.MAGENTA);
            g.fillRect(x2, y2, Maze.CELL_SIZE, Maze.CELL_SIZE);
        }

        if (path) {
            g.setColor(Color.BLUE);
            g.fillRect(x2, y2, Maze.CELL_SIZE, Maze.CELL_SIZE);
        } else if (deadEnd) {
            g.setColor(Color.RED);
            g.fillRect(x2, y2, Maze.CELL_SIZE, Maze.CELL_SIZE);
        }

        g.setColor(Color.WHITE);
        if (walls[0]) {
            g.drawLine(x2, y2, x2 + Maze.CELL_SIZE, y2);
        }
        if (walls[1]) {
            g.drawLine(x2 + Maze.CELL_SIZE, y2, x2 + Maze.CELL_SIZE, y2 + Maze.CELL_SIZE);
        }
        if (walls[2]) {
            g.drawLine(x2 + Maze.CELL_SIZE, y2 + Maze.CELL_SIZE, x2, y2 + Maze.CELL_SIZE);
        }
        if (walls[3]) {
            g.drawLine(x2, y2 + Maze.CELL_SIZE, x2, y2);
        }
    }

    /**
     * displayAsColor displays the cell as a color
     * @param g
     * @param color
     */
    public void displayAsColor(Graphics g, Color color) {
        int x2 = x * Maze.CELL_SIZE;
        int y2 = y * Maze.CELL_SIZE;
        g.setColor(color);
        g.fillRect(x2, y2, Maze.CELL_SIZE, Maze.CELL_SIZE);
    }

    /**
     * removeWalls removes the walls between two cells
     * @param next
     */
    public void removeWalls(Cell next) {
        int x = this.x - next.x;
        // top 0, right 1, bottom 2, left 3

        if (x == 1) {
            walls[3] = false;
            next.walls[1] = false;
        } else if (x == -1) {
            walls[1] = false;
            next.walls[3] = false;
        }

        int y = this.y - next.y;

        if (y == 1) {
            walls[0] = false;
            next.walls[2] = false;
        } else if (y == -1) {
            walls[2] = false;
            next.walls[0] = false;
        }
    }

    /**
     * randomNeighbour returns a random neighbour of the cell
     * @param neighbours
     * @return Cell
     */
    private Cell randomNeighbour(List<Cell> neighbours) {
        if (neighbours.size() > 0) {
            return neighbours.get(new Random().nextInt(neighbours.size()));
        } else {
            return null;
        }
    }

    /**
     * checkNeighbourInGridBounds checks if a neighbour is in the grid bounds
     * @param grid
     * @param neighbour
     * @return Cell
     */
    private Cell checkNeighbourInGridBounds(List<Cell> grid, Cell neighbour) {
        if (grid.contains(neighbour)) {
            return grid.get(grid.indexOf(neighbour));
        } else {
            return null;
        }
    }

    // Used for Wilson's algorithm
    public Cell getNonPathNeighbour(List<Cell> grid) {

        List<Cell> neighbours = new ArrayList<Cell>(4);

        Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
        Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
        Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
        Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

        if (top != null) if (!top.path) neighbours.add(top);
        if (right != null) if (!right.path) neighbours.add(right);
        if (bottom != null) if (!bottom.path) neighbours.add(bottom);
        if (left != null) if (!left.path) neighbours.add(left);

        if (neighbours.size() == 1) {
            return neighbours.get(0);
        }
        return randomNeighbour(neighbours);
    }


    public Cell getUnvisitedNeighbour(List<Cell> grid) {

        List<Cell> neighbours = getUnvisitedNeighboursList(grid);

        if (neighbours.size() == 1) {
            return neighbours.get(0);
        }
        return randomNeighbour(neighbours);
    }


    public List<Cell> getUnvisitedNeighboursList(List<Cell> grid) {

        List<Cell> neighbours = new ArrayList<Cell>(4);

        Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
        Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
        Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
        Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

        if (top != null) if (!top.visited) neighbours.add(top);
        if (right != null) if (!right.visited) neighbours.add(right);
        if (bottom != null) if (!bottom.visited) neighbours.add(bottom);
        if (left != null) if (!left.visited) neighbours.add(left);

        return neighbours;
    }

    // no walls between
    public List<Cell> getValidMoveNeighbours(List<Cell> grid) {
        List<Cell> neighbours = new ArrayList<Cell>(4);

        Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
        Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
        Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
        Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

        if (top != null) {
            if (!walls[0]) neighbours.add(top);
        }

        if (right != null) {
            if (!walls[1]) neighbours.add(right);
        }

        if (bottom != null) {
            if (!walls[2]) neighbours.add(bottom);
        }

        if (left != null) {
            if (!walls[3]) neighbours.add(left);
        }

        return neighbours;
    }

    // used for DFS solving, gets a neighbour that could potentially be part of the solution path.
    public Cell getPathNeighbour(List<Cell> grid) {
        List<Cell> neighbours = new ArrayList<Cell>();

        Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
        Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
        Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
        Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

        if (top != null) {
            if (!top.deadEnd) {
                if (!walls[0]) neighbours.add(top);
            }
        }

        if (right != null) {
            if (!right.deadEnd) {
                if (!walls[1]) neighbours.add(right);
            }
        }

        if (bottom != null) {
            if (!bottom.deadEnd) {
                if (!walls[2]) neighbours.add(bottom);
            }
        }

        if (left != null) {
            if (!left.deadEnd) {
                if (!walls[3]) neighbours.add(left);
            }
        }

        if (neighbours.size() == 1) {
            return neighbours.get(0);
        }

        return randomNeighbour(neighbours);
    }

    public List<Cell> getAllNeighbours(List<Cell> grid) {
        List<Cell> neighbours = new ArrayList<Cell>();

        Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
        Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
        Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
        Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));

        if (top != null) neighbours.add(top);
        if (right != null) neighbours.add(right);
        if (bottom != null) neighbours.add(bottom);
        if (left != null) neighbours.add(left);

        return neighbours;
    }

    public Cell getTopNeighbour(List<Cell> grid) {
        return checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
    }

    public Cell getRightNeighbour(List<Cell> grid) {
        return checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
    }

    public Cell getBottomNeighbour(List<Cell> grid) {
        return checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
    }

    public Cell getLeftNeighbour(List<Cell> grid) {
        return checkNeighbourInGridBounds(grid, new Cell(x - 1, y));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}