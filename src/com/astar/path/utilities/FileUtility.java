package com.astar.path.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.astar.path.dataobject.Cell;
import com.astar.path.dataobject.Terrain;

/**
 * 
 * Class to handle text map file operations
 * 
 * @author Rashid.O
 *
 */
public class FileUtility {

    /** location of A* output file **/
    private static String outputFilePath = "\\AstarPathOutput.txt";

    /**
     * Method to read text map file
     * 
     * @param filePath
     *            - input file path
     * @return List<String>
     * @throws IOException
     */
    public static List<String> readMapFile(final String filePath) throws IOException {

	// Extracting the folder name
	File file = new File(filePath);
	outputFilePath = file.getParent() + outputFilePath;

	// reading text map file
	return Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
    }

    /**
     * Method to create A* output file
     * 
     * @param grid
     *            - Two dimensional array of cells
     * @param row
     *            - total number of rows in the map
     * @param col
     *            - total number of columns in the map
     */
    public static void createAstarpathFile(final Cell[][] grid, final int row, final int col) {

	FileWriter fileWriter = null;
	BufferedWriter bufferedWriter = null;

	try {

	    fileWriter = new FileWriter(outputFilePath);
	    bufferedWriter = new BufferedWriter(fileWriter);

	    //iterating each cells from the Grid and writing to output file
	    for (int rowIndex = 0; rowIndex < row; rowIndex++) {
		for (int colIndex = 0; colIndex < col; colIndex++) {
		    if (null != grid[rowIndex][colIndex]) {
			bufferedWriter.write(String.valueOf(grid[rowIndex][colIndex].getText()), 0, 1);
		    } else {
			bufferedWriter.write(String.valueOf(Terrain.WATER.getKey()), 0, 1);
		    }
		    bufferedWriter.flush();
		}

		bufferedWriter.newLine();
	    }
	    System.out.println("Output File with shortest path created in " + outputFilePath);
	} catch (IOException e) {
	    System.out.println("Exception occured while writing Astarpath.txt file");
	    e.printStackTrace();
	} finally {
	    try {
		if (null != bufferedWriter) {
		    bufferedWriter.close();
		}

		if (null != fileWriter) {
		    fileWriter.close();
		}
	    } catch (IOException ioException) {
		ioException.printStackTrace();
	    }
	}
    }

}
