package com.star.path.dataobject;

/**
 * 
 * Class representation of a Tile or Terrain
 * Grid is a collection of cells
 * 
 * @author Rashid.O
 *
 */
public class Cell implements Comparable<Cell>{

    /** Heuristic cost of the cell(Distance from current Cell to Destination Cell)
     *  calculated using Manhattan Distance formula **/
    private int heuristicCost = 0; 

    /** final cost of cell = heuristicCost + cost of the movement + cost of Terrain **/
    private int finalCost = 0;

    /** row and column number of the cell **/
    private int row, col;

    /** content of the cell **/
    private Terrain terrain;

    /** To trace out the shortest path **/
    private Cell parent;

    /** constructor of a cell **/
    public Cell(final int row, final int column){
	this.setRow(row);
	this.setCol(column); 
    }

    /** Method to set the content of a cell 
     * 
     * @param ch - character to set 
     **/
    public void setText(final char ch){
	this.setTerrain(Terrain.getTerrileByChar(ch));
    }

    /**
     *  method to get the content of a cell
     *  @return character of the cell
     */
    public char getText(){
	return this.getTerrain().getKey();
    }

    /**
     * Method to print the content of a cell
     */
    public void printText(){
	System.out.print(this.getTerrain().getKey());
    }

    /**
     * @return the heuristicCost
     */
    public int getHeuristicCost() {
	return heuristicCost;
    }

    /**
     * @param heuristicCost the heuristicCost to set
     */
    public void setHeuristicCost(final int heuristicCost) {
	this.heuristicCost = heuristicCost;
    }

    /**
     * @return the finalCost
     */
    public int getFinalCost() {
	return finalCost;
    }

    /**
     * @param finalCost the finalCost to set
     */
    public void setFinalCost(final int finalCost) {
	this.finalCost = finalCost;
    }

    /**
     * @return the row
     */
    public int getRow() {
	return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(final int row) {
	this.row = row;
    }

    /**
     * @return the col
     */
    public int getCol() {
	return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(final int col) {
	this.col = col;
    }

    /**
     * @return the Terrain
     */
    public Terrain getTerrain() {
	return terrain;
    }

    /**
     * @param Terrain the Terrain to set
     */
    public void setTerrain(Terrain Terrain) {
	this.terrain = Terrain;
    }

    /**
     * @return the parent
     */
    public Cell getParent() {
	return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(final Cell parent) {
	this.parent = parent;
    }

    /**
     * Method to sort cell with final cost
     */
    @Override
    public int compareTo(final Cell cell) {
	if(this.getFinalCost() == cell.getFinalCost()){
	    return 0;
	}else{
	    return this.getFinalCost()>cell.getFinalCost()?1:-1;
	}
    }

    /**
     * Method to print a cell details
     */
    @Override
    public String toString(){
	return "["+this.getRow()+", "+this.getCol()+"]";
    }
}

