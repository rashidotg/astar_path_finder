package com.astar.path.service.impl;

import java.util.PriorityQueue;

import com.astar.path.service.TextMap;
import com.star.path.dataobject.Cell;
import com.star.path.dataobject.Terrain;
import com.star.path.utilities.FileUtility;

/**
 * Implementation class for TextMap interface This class represents the textMap read from file as a 2D Array of Cells
 * It's implementation of the path finder related methods and specific handling for the types of units and terrain.
 * 
 * @author Rashid.O
 */
public class LargeTextMap implements TextMap {

    /** cost to move diagonally **/
    private static final int DIAGONAL_COST = 14;

    /** cost to move horizontally **/
    private static final int V_H_COST = 10;

    /** 2D array to store the map. Blocked cells are just null Cell values in grid **/
    private Cell[][] grid;

    /** to store evaluating cells **/
    private PriorityQueue<Cell> open = new PriorityQueue<>();

    /** to store evaluated cells **/
    private boolean closed[][];

    /** row and column number of the cell **/
    private int rowMax, columnMax;

    /** source cell **/
    private int startRow, startCol;

    /** end cell **/
    private int endRow, endCol;

    /** constructor **/
    public LargeTextMap(final int row, final int col) {

	// initialize grid with row and column size
	grid = new Cell[row][col];
	this.rowMax = row;
	this.columnMax = col;

	// initializing each cell's inside a Map
	initGrid(row, col);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGrid(final int row, final int col) {

	// fill grid with Cells
	for (int rowIndex = 0; rowIndex < row; rowIndex++) { // row iterator
	    for (int colIndex = 0; colIndex < col; colIndex++) { // column iterator
		grid[rowIndex][colIndex] = new Cell(rowIndex, colIndex);
	    }
	}

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printGrid() {
	// prints every cells

	// looping through row
	for (int rowIndex = 0; rowIndex < rowMax; rowIndex++) {
	    // looping through columns
	    for (int colIndex = 0; colIndex < columnMax; colIndex++) {

		if (grid[rowIndex][colIndex] != null) {
		    grid[rowIndex][colIndex].printText();
		} else {
		    System.out.print(Terrain.WATER.getKey());
		}
	    } // end column loop
	    System.out.println();
	}
    } // end printGrid

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getCell(final int row, final int col) {

	// Cell to return
	Cell cell = null;
	if (row >= 0 && row <= rowMax && col >= 0 && col <= columnMax) {
	    cell = grid[row][col];
	}
	return cell;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSize(final int row, final int col) {

	// setting maximum number of rows
	this.rowMax = row;
	// setting maximum number of column
	this.columnMax = col;
	// initializing Grid with maximum number of rows and column
	grid = new Cell[row][col];
	closed = new boolean[row][col];
	// initialize all cells
	initGrid(row, col);
	// Setting the final cost of source as zero
	grid[startRow][startCol].setFinalCost(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTerrain(final int row, final int col, final char ch) {

	if (null == Terrain.getTerrileByChar(ch)) {
	    System.out.println(
		    "Invalid character present in text map file at row=" + (row + 1) + " and column=" + (col + 1));
	    System.out.println("Exiting from program ......!!");
	    System.exit(0);
	}
	if (Terrain.WATER.getKey() == ch) {
	    // makes a cell as non-walkable
	    markcellAsNonWalkable(row, col);

	} else if (Terrain.SOURCE.getKey() == ch) {
	    // setting as the source cell
	    setStartCell(row, col);
	    grid[row][col].setText(ch);

	} else if (Terrain.DESTINATION.getKey() == ch) {
	    // setting as the destination
	    setEndCell(row, col);
	    grid[row][col].setText(ch);
	} else {
	    // setting character of the cell
	    grid[row][col].setText(ch);
	}

    } // end

    /**
     * {@inheritDoc}
     */
    @Override
    public void markcellAsNonWalkable(final int row, final int col) {
	if (row >= 0 && row <= rowMax && col >= 0 && col <= columnMax) {
	    grid[row][col] = null;
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStartCell(final int row, final int col) {
	startRow = row;
	startCol = col;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEndCell(final int row, final int col) {
	endRow = row;
	endCol = col;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void calculateDistanceToDestination() {

	// Finding distance from each source to destination using Manhattan Distance formula
	for (int rowIndex = 0; rowIndex < rowMax; ++rowIndex) {
	    for (int colIndex = 0; colIndex < columnMax; ++colIndex) {
		// only calculating distance of the walkable cells
		if (grid[rowIndex][colIndex] != null) {
		    grid[rowIndex][colIndex]
			    .setHeuristicCost(Math.abs(rowIndex - endRow) + Math.abs(colIndex - endCol));
		}
	    }
	}

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkAndUpdateCost(final Cell current, final Cell adjacent, final int cost) {
	// Validating whether the Cell is Non-walkable or already evaluated
	if (adjacent == null || closed[adjacent.getRow()][adjacent.getCol()]) {
	    return;
	}
	// calculating total cost for the movement
	int costOfMovement = adjacent.getHeuristicCost() + cost + adjacent.getTerrain().getValue();

	boolean inOpen = open.contains(adjacent);
	// calculating lowest cost and finding adjacent Cell
	if (!inOpen || costOfMovement < adjacent.getFinalCost()) {
	    adjacent.setFinalCost(costOfMovement);
	    adjacent.setParent(current);
	    if (!inOpen) {
		// Adding to open
		open.add(adjacent);
	    }
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void traceAStarPath() {

	// add the start location to open list.
	open.add(grid[startRow][startCol]);
	
	//current cell to evaluate
	Cell currentCell = null;
	// Adjacent walkable cell to evaluate
	Cell adjacentCell;

	while (true) {

	    // retrieve the cell to process from the priority Queue
	    currentCell = open.poll();

	    if (currentCell == null) {
		break; // Exiting from loop if cell is non-walkable
	    }
	    // marking cell as evaluated
	    closed[currentCell.getRow()][currentCell.getCol()] = true;

	    // Destination reached
	    if (currentCell.equals(grid[endRow][endCol])) {
		// replacing with #
		currentCell.setText(Terrain.SHORT_PATH.getKey());
		return;
	    }

	    // Checking whether adjacent cell is a valid cell in the map.
	    // if valid then calculating the cost of this movement
	    if (currentCell.getRow() - 1 >= 0) {
		adjacentCell = grid[currentCell.getRow() - 1][currentCell.getCol()];
		checkAndUpdateCost(currentCell, adjacentCell, currentCell.getFinalCost() + V_H_COST);

		if (currentCell.getCol() - 1 >= 0) {
		    adjacentCell = grid[currentCell.getRow() - 1][currentCell.getCol() - 1];
		    checkAndUpdateCost(currentCell, adjacentCell, currentCell.getFinalCost() + DIAGONAL_COST);
		}

		if (currentCell.getCol() + 1 < grid[0].length) {
		    adjacentCell = grid[currentCell.getRow() - 1][currentCell.getCol() + 1];
		    checkAndUpdateCost(currentCell, adjacentCell, currentCell.getFinalCost() + DIAGONAL_COST);
		}
	    }

	    if (currentCell.getCol() - 1 >= 0) {
		adjacentCell = grid[currentCell.getRow()][currentCell.getCol() - 1];
		checkAndUpdateCost(currentCell, adjacentCell, currentCell.getFinalCost() + V_H_COST);
	    }

	    if (currentCell.getCol() + 1 < grid[0].length) {
		adjacentCell = grid[currentCell.getRow()][currentCell.getCol() + 1];
		checkAndUpdateCost(currentCell, adjacentCell, currentCell.getFinalCost() + V_H_COST);
	    }

	    if (currentCell.getRow() + 1 < grid.length) {
		adjacentCell = grid[currentCell.getRow() + 1][currentCell.getCol()];
		checkAndUpdateCost(currentCell, adjacentCell, currentCell.getFinalCost() + V_H_COST);

		if (currentCell.getCol() - 1 >= 0) {
		    adjacentCell = grid[currentCell.getRow() + 1][currentCell.getCol() - 1];
		    checkAndUpdateCost(currentCell, adjacentCell, currentCell.getFinalCost() + DIAGONAL_COST);
		}

		if (currentCell.getCol() + 1 < grid[0].length) {
		    adjacentCell = grid[currentCell.getRow() + 1][currentCell.getCol() + 1];
		    checkAndUpdateCost(currentCell, adjacentCell, currentCell.getFinalCost() + DIAGONAL_COST);
		}
	    }

	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void traceAndPrintAStarPath() {

	// trace out the path
	traceAStarPath();

	// display final cost of each cell
	// displayFinalCost();

	// print path
	printShortPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printShortPath() {

	if (closed[endRow][endCol]) {
	    // Trace back the path
	    System.out.println("Path ------->>>>: ");

	    Cell current = grid[endRow][endCol];

	    while (current.getParent() != null) {

		current = current.getParent();
		current.setText(Terrain.SHORT_PATH.getKey()); // replace short path with '#'
	    }
	    // print A* path in console
	    printGrid();

	    // write A* path to output file
	    FileUtility.createAstarpathFile(grid, rowMax, columnMax);
	} else
	    System.out.println("No possible path");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayFinalCost() {

	System.out.println("\nScores for cells: ");

	// thru row
	for (int rowIndex = 0; rowIndex < rowMax; rowIndex++) {
	    // loop thru columns
	    for (int colIndex = 0; colIndex < columnMax; colIndex++) {

		if (grid[rowIndex][colIndex] != null) {
		    System.out.printf("%-3d ", grid[rowIndex][colIndex].getFinalCost());
		} else {
		    System.out.print("BL  ");
		}
	    }
	    System.out.println();
	} // end row it loop

	System.out.println();
    }

}
