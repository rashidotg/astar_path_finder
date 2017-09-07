package com.astar.path.finder;

import java.io.IOException;
import java.util.List;

import com.astar.path.service.TextMap;
import com.astar.path.service.impl.LargeTextMap;
import com.astar.path.utilities.FileUtility;

/**
 * 
 * Read map from a file and trace out the shortest path from source to destination using A star algorithm
 * 
 * @author Rashid.O
 *
 */
public class AStar {

    /** To hold the map content read from file **/
    private TextMap largeTextMap;

    /** constructor **/
    public AStar() {
	largeTextMap = new LargeTextMap(0, 0);
    }

    /**
     * Method to create the map from file and trace out the shortest path
     * 
     * @param filePath
     *            - location of text map file
     * @throws IOException
     */
    public void findShortestPath(String filePath) {

	// Total number of rows in the map
	int rowMax = 0;
	// Total number of columns in the map
	int colMax = 0;
	// current row index
	int row = 0;
	// current column index
	int col = 0;

	// each row of text map file
	List<String> lines = null;

	try {
	    // reading whole content of the input textMap file
	    lines = FileUtility.readMapFile(filePath);

	    if (null != lines && !lines.isEmpty()) {
		// Number of rows required in the map
		rowMax = lines.size();
	    }

	    if (rowMax > 0) {

		// Number of columns required
		colMax = lines.get(0).length();

		// initialize map
		largeTextMap.setSize(rowMax, colMax);

		for (String line : lines) {
		    for (char ch : line.toCharArray()) {
			// Set content of the cell
			largeTextMap.setTerrain(row, col, ch);
			col++;
		    }
		    col = 0;
		    row++;
		}

		// Calculating the heuristic cost of all cells
		largeTextMap.calculateDistanceToDestination();

		// trace out path
		largeTextMap.traceAndPrintAStarPath();

	    } else {
		System.out.println("Given Invalid Text map file Format ...!");
	    }
	} catch (IOException ioException) {
	    System.out.println("Invalid text map file location ....!");
	}
    }
}