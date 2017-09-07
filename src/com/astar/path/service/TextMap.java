package com.astar.path.service;

import com.astar.path.dataobject.Cell;

/**
 * The description for the data for path finding. Data represented as two dimensional array of Cells or Terriles
 * 
 * 
 * @author Rashid.O
 */
public interface TextMap {

    /**
     * Method to initialize the TextMap with specified width and height
     * 
     * @param row
     *            - number of rows in the map
     * @param col
     *            - Number of columns in the map
     */
    void initGrid(final int row, final int col);

    /**
     * Method to loop through the entire map and print each Tile(Cell)
     * 
     */
    void printGrid();

    /**
     * Method to get a Cell from Map
     * 
     * @param row
     *            - row number of the cell
     * @param col
     *            - column number of the cell
     * @return Cell
     */
    Cell getCell(final int row, final int col);

    /**
     * Re-set the size of the map
     * 
     * @param row
     *            - new map row size
     * @param col
     *            - new map column size
     */
    void setSize(final int row, final int col);

    /**
     * Method to set the Terrile property of a tile
     * 
     * @param row
     *            - row number of the cell to set
     * @param col
     *            - column number of the cell to set
     * @param ch
     *            - character read from the map file
     */
    void setTerrain(final int row, final int col, final char ch);

    /**
     * Method to mark a non-walkable tile in the map
     * 
     * @param row
     *            - row number to set non-walkable
     * @param column
     *            - column number to set non-walkable
     */
    void markcellAsNonWalkable(final int row, final int column);

    /**
     * Method to mark a cell as the source. A* algorithm will find shortest path from source cell to destination cell in
     * the map
     * 
     * @param row
     *            - row number of the source cell
     * @param column
     *            - column number of the source
     */
    void setStartCell(final int row, final int column);

    /**
     * Method to mark a cell as the destination. A* algorithm will find shortest path from source cell to destination
     * cell in the map
     * 
     * @param row
     *            - row number of the destination cell
     * @param column
     *            - column number of the destination
     */
    void setEndCell(final int row,final int column);

    /**
     * Method to calculate the distance from a each cell to destination using Manhattan Distance formula this is called the
     * heuristic cost of the cell
     * 
     */
    void calculateDistanceToDestination();

    /**
     * Method to check and update the final cost of each cell
     * 
     * @param current
     *            - current cell being evaluating
     * @param adjacent
     *            - possible adjacent cell of the current cell to move
     * @param cost
     *            - final cost of the current cell
     */
    void checkAndUpdateCost(final Cell current, final Cell adjacent,final int cost);

    /**
     * Method to trace out and print the shortest path in the given map
     * 
     */
    void traceAndPrintAStarPath();

    /**
     * Method to trace out the shortest path from source to destination
     * 
     */
    void traceAStarPath();

    /**
     * Method to print the shortest path from source to destination
     */
    void printShortPath();

    /**
     * Method to display the final cost of each cell used for de-bugging
     */
    void displayFinalCost();

}
