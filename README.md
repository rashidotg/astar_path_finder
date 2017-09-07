# astart_path_finder
java project to trace out shortest path using a* algorithm

A*
==============
The A* (A-Star) search algorithm is a path-finding algorithm with many uses, including Artificial Intelligence for games. The search builds the route from tile to tile until it reaches the goal. 
To help choose the best possible tile the algorithm will use an equation that takes into account the distance from the tile to the goal and the cost of moving to that tile.

Terrain Map Details
=============================
  Non-walkable:
    N/A = Water (~)

  Walkable:

    1 = Flatlands (. or @ or X)
    2 = Forest (*)
    3 = Mountain (^)

Step to run the program:
===========================

1. Run the main method available in com.star.path.start.AstarApp class
2. Select option 1 to trace out the shortest path
3. Give the path of large_map.txt
4. It will print the shortest path in the console as well as create an output file named AstarPathOutput.txt
5. You can test another file by entering 1 or terminate the program by entering 0
