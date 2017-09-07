package com.star.path.start;

import java.util.Scanner;

import com.star.path.finder.AStar;

/**
 * Test class to test the A* algorithm to trace out short path
 * for a given text map file
 * 
 * @author Rashid.O
 *
 */
public class AstarApp {

    static public void main (String[] args){

	// very user error prone !
	boolean quit = false;
	Scanner in = new Scanner(System.in);
	int input;

	while(!quit){
	    input = -1;
	    System.out.print("\n");
	    System.out.println("============================");
	    System.out.println("	A STAR ALGORITH 	");
	    System.out.print("============================"
		    + "\n 0 ---> Quit"
		    + "\n 1 ---> Find path"
		    + "\n============================");
	    input = in.nextInt();

	    if (input == 1){
		System.out.println("Enter text map file location");
		String path = in.next();

		if(null != path){

		    AStar astar = new AStar();
		    astar.findShortestPath(path);
		    //astar.findShortestPath("D:\XRE\MultiChoice\large_map.txt");
		}

	    } else if (input == 0){
		quit = true;

	    }


	}
	System.out.print("EXECUTION COMPLETED");
	in.close();
    }

}
