# Artificial Intelligence Project 1

### Team Members
```md
46-2595 Alfarouk Amr Ali Sabry T14
46-16589 Ahmed Mohamed Ramadan T07
46-17914 Ahmed Samir Elsayed Elghobashy T07
```
### Brief discussion of the problem
```md
Given a certain grid that contains :
1. A coast guard ship at a random location
2. Several ships scattered at random locations, each has random initial passenger numbers from 1 to 100
3. Several stations at random locations

Actions such as pick-up, drop, retrieve, and movement in any of the 4 directions.
We are required to create an agent that acts as a coast guard boat captain using several search procedures.
```

### Discussion of the search-tree node ADT implementation
```md
Each node contains the following information:
1. State
    1.1 The current grid as an array of arrays
    1.2 Coast guard boat instance
        1.2.1 Capacity of the coast guard
        1.2.2 X position of the coast guard
        1.2.3 Y position of the coast guard
        1.2.4 Y position of the coast guard
        1.2.5 Number of passengers on the coast guard
        1.2.6 Number of remaining space on the coast guard
    1.3 Number of black boxes retrieved
    1.4 Number of undamaged black boxes
    1.5 Number of damaged black boxes initially set to 0
    1.6 Number of dead passengers
    1.7 Number of rescued passengers
2. Array of children nodes
3. Parent node
4. Action performed to reach this node as a string, null on initial node
```

### Discussion of the search problem ADT implementation
```md
Each search problem contains the following information:

1. Actions performed as an array of strings
2. initial state node
    2.1 State
        2.1.1 The current grid as an array of arrays
        2.1.2 Coast guard boat instance
            2.1.2.1 Capacity of the coast guard
            2.1.2.2 X position of the coast guard
            2.1.2.3 Y position of the coast guard
            2.1.2.4 Y position of the coast guard
            2.1.2.5 Number of passengers on the coast guard
            2.1.2.6 Number of remaining space on the coast guard
        2.1.3 Number of black boxes retrieved
        2.1.4 Number of undamaged black boxes
        2.1.5 Number of damaged black boxes initially set to 0
        2.1.6 Number of dead passengers
        2.1.7 Number of rescued passengers
    2.2. Array of children nodes
    2.3. Parent node
    2.4. Action performed to reach this node as a string, null on initial node
```

### Discussion of the coast guard problem
```md
The coast guard boat class extends ClassObject, and contains the following information:

1. Capacity of the coast guard
2. X position of the coast guard
3. Y position of the coast guard
4. Y position of the coast guard
5. Number of passengers on the coast guard
6. Number of remaining space on the coast guard
```

### Discussion of the main functions
#### 1. CoastGuard class
```md
1. solve : a static method that takes the starting grid, the strategy to use,
   and a boolean visualize, the method returns the search result from SearchProcedure.search() method
   
2.genGridFromStringGrid: a static method that takes the initial grid in string format,
   the method returns a StateObject array of arrays where each cell represents the 
   positions of an object in the grid
   
3.getCoastGuardBoatFromStringGrid: a static method that takes the starting grid as an input,
   and returns a CoastGuardBoat object
```
#### 2. SearchProcedure class
```md

```

### Discussion of the various search algorithms
```md

```

### Discussion of the heuristic functions
```md

```

### Performance comparisons
#### Tests used in all comparisons :
```md
1. "10,6;59;1,7;0,0,2,2,3,0,5,3;1,3,69,3,4,80,4,7,94,4,9,14,5,2,39;"
2. "7,5;100;3,4;2,6,3,5;0,0,4,0,1,8,1,4,77,1,5,1,3,2,94,4,3,46;"
3. "6,6;74;1,1;0,3,1,0,2,0,2,4,4,0,4,2,5,0;0,0,78,3,3,5,4,3,40;"
```

| CPU | Memory | Time     | Expanded Nodes | Algorithm |
|-----|--------|----------|----------------|-----------|
| 30% | 5GB    | 18726 ms |                | BFS       |
| 13% | 1.8GB  | 46 ms    |                | DFS       |
| 15% | 3GB    | 72492 ms |                | ID        |
| 16% | 2.9GB  | 17123 ms |                | DFMD      |
| 10% | 1.8GB  | 53 ms    |                | GR1       |
| 20% | 2.3GB  | 1185 ms  |                | GR2       |
| 14% | 1.8GB  | 404 ms   |                | AS1       |
| 14% | 1.9GB  | 575 ms   |                | AS2       |
| 19% | 2.1GB  | 391 ms   |                | UC        |

```md
CPU: CPU utilization's percentage
Memory: RAM usage in gigabytes
Time: Time taken to complete all tasks in milliseconds
Algorithm: The type of algorithm used
```
### Citations
```md

```
