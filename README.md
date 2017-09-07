# astar_path_finder
java project to trace out the shortest path using a* algorithm

A*
==============
The A* (A-Star) search algorithm is a path-finding algorithm with many uses, including Artificial Intelligence for games. The search builds the route from tile to tile until it reaches the goal. 
To help choose the best possible tile the algorithm will use an equation that takes into account the distance from the tile to the goal and the cost of moving to that tile.

Terrain Map Details
=============================

Non-walkable:
----------------
N/A = Water (~) - Blocked Terrain, so not able to walk

Walkable
----------------
User start(@)	- Movement cost for this terrain is 1                           
Goal tile(X)	- Movement cost for this terrain is 1                                        

Flatlands (.) 	- Movement cost for this terrain is 1                         
Forest (*) 		- Movement cost for this terrain is 2                          
Mountain(^)		- Movement cost for this terrain is 3                                    

Step to run the program:
===========================

1. Checkout code from github repository
2. Run the main method available in com.star.path.start.AstarApp class
3. Select option 1 to trace out the shortest path
4. Give the path of large_map.txt file
5. It will print the shortest path in the console as well as create an output file named AstarPathOutput.txt
6. You can test another file by entering 1 or terminate the program by entering 0
