package code;

public class CoastGuard extends SearchProblem{
    // StateObject[][] grid;
    static int numOfDeaths=0;
    static int numOfRetrived=0;
    static String[] actions = {"Pick-up","Drop","Retrieve","Up","Down","Left","Right"};

    public  CoastGuard(Node intialNodeState) {
        super(actions,intialNodeState);

        int x =0;
        x++;


    }


    public static String GenGrid() {
        int xDimension = (int) (Math.random() * 50);
        int yDimension = (int) (Math.random() * 50);
        int numOfShips = ((int) (Math.random() * xDimension * yDimension / 2)) + 1;
        int numOfStations = ((int)( Math.random() * xDimension * yDimension / 2)) + 1;
        int shipCapacity = ((int) (Math.random() * 99)) + 1;
        int coastGuardCapacity = ((int) (Math.random() * 99)) + 1;
        StateObject[][] grid = new StateObject[xDimension][yDimension];

        int[] coastGuarddIntialCoordinate = generateRandomCoordinate(xDimension, yDimension);
        CoastGuardBoat cgb = new CoastGuardBoat(coastGuardCapacity, xDimension, yDimension);

        String retString = yDimension + "," + xDimension + ";" + cgb.x + "," + cgb.y + ";";
        while (numOfStations > 0) {
            int[] coorddinates = generateRandomCoordinate(xDimension, yDimension);
            if (grid[coorddinates[0]][coorddinates[1]] == null) {
                grid[coorddinates[0]][coorddinates[1]] = new Station(coorddinates[0], coorddinates[1]);
                retString +=  + coorddinates[0] + "," + coorddinates[1]+",";
                numOfStations--;
            }

        }

        retString=retString.substring(0,retString.length()-1);
        retString += ";";
        while (numOfShips > 0) {
            int[] coorddinates = generateRandomCoordinate(xDimension, yDimension);
            if (grid[coorddinates[0]][coorddinates[1]] == null) {
                grid[coorddinates[0]][coorddinates[1]] = new Ship(shipCapacity, coorddinates[0], coorddinates[1]);
                retString +=  coorddinates[0] + "," + coorddinates[1] + "," + shipCapacity+"," ;
                numOfShips--;
            }
        }
        retString=retString.substring(0,retString.length()-1);
        retString += ";";

        return retString;
    }


    private static int[] generateRandomCoordinate(int xDimension, int yDimension) {
        int x = (int) (Math.random() * xDimension);
        int y = (int) (Math.random() * yDimension);


        return new int[]{x, y};
    }

    public static String solve(String grid, String strategy, boolean visualize) {
        StateObject[][] intialGridArray = genGridFromStringGrid(grid);
        CoastGuardBoat coastGuardBoat= getCoastGuardBoatFromStringGrid(grid);
        State intialState = new State(intialGridArray,coastGuardBoat);
        Node intialNodeState = new Node(intialState,new Node[]{},null);
        CoastGuard coastGuardProblem = new CoastGuard(intialNodeState);


        return SearchProcedure.search(strategy,coastGuardProblem);
    }

    //creates coast guard boat object from the string represntation of the grid and returns it
    private static CoastGuardBoat getCoastGuardBoatFromStringGrid(String grid){
        int[] coastGuardCoordinates = getCoastGuardCoardinatesFromStringGrid(grid);
        int coastGuardCapacity= getCoastGuardCapacityFromStingGrid(grid);
        CoastGuardBoat coastGuardBoat= new CoastGuardBoat(coastGuardCapacity,coastGuardCoordinates[0],coastGuardCoordinates[1]);
        return coastGuardBoat;

    }
    private static int getCoastGuardCapacityFromStingGrid(String grid)
    {
        String[] gridInfo=grid.split(";");
        String[] cgPos = gridInfo[1].split(",");
        return Integer.parseInt(cgPos[0]);
    }



   //gets the coordinates of the coast guard boat from the string represntaion of the  grid
    private static int[] getCoastGuardCoardinatesFromStringGrid(String grid) {
        String[] gridInfo=grid.split(";");
        String[] cgPos = gridInfo[2].split(",");
        int cgX = Integer.parseInt(cgPos[0]);
        int cgY = Integer.parseInt(cgPos[1]);
        return new int[] {cgX,cgY};
    }

    private static StateObject[][] genGridFromStringGrid(String grid) {
        String[] gridInfo=grid.split(";");

        //M,N index: 0
        //3,4;97;1,2;0,1;3,2,65
        String[] coordinates = gridInfo[0].split(",");
        int n = Integer.parseInt(coordinates[0]);
        int m = Integer.parseInt(coordinates[1]);

        /*//C index : 1
        int c = Integer.parseInt(gridInfo[1]);

        //cgX,cgY index: 2
        String[] cgPos = gridInfo[2].split(",");
        int cgX = Integer.parseInt(cgPos[0]);
        int cgY = Integer.parseInt(cgPos[1]);*/

        // I1X, I1Y, I2X, I2Y, ...IiX, IiY index:3
        String[] stations = gridInfo[3].split(",");

        //S1X, S1Y, S1P assengers, S2X, S2Y, S2P assengers, ...SjX, SjY, SjP assengers index : 4
        String[] ships = gridInfo[4].split(",");

        StateObject[][] resGrid= new StateObject[m][n];

        //add stations to grid
        for (int i = 0; i < stations.length; i+=2) {
            int x= Integer.parseInt(stations[i]);
            int y= Integer.parseInt(stations[i+1]);
            Station s = new Station(x,y);
            resGrid[x][y]=s;
        }

        //add ships to grid
        for (int i = 0; i < ships.length; i+=3) {
            int x= Integer.parseInt(ships[i]);
            int y= Integer.parseInt(ships[i+1]);
            int num= Integer.parseInt(ships[i+2]);
            Ship ship = new Ship(num,x,y);
            resGrid[x][y]=ship;
        }
        return resGrid;
    }

    public static void main(String[] args)
    {
        solve("3,4;97;1,2;0,1;3,2,65;","BF",false);
    }

    @Override
    public int costFunction(Node endNode) {

        State state = endNode.state;

        return (state.numOfDamagedBlackBoxes + state.numOfDeadPassengers*1000);
    }



    @Override
    //checks for the goal state if there is no more blackboxes or passengers to be rescued(if they are on the boat they are not rescued yet).
    public boolean isGoalState(Node node) {
        if(node.state.numOfUndamagedBlackBoxes==0 && node.state.getNumOfUnrescuedPassengers()==0 && node.state.coastGuardBoat.passengersOnBoat==0)
            return true;
        else
            return false;
    }

    @Override
    //Takes the action as a string and the node and applies the action on the node and returns the child node after the action is applied
    //returns null if there is a problem with the action or the action is not applicable
    protected Node applyAction(String action, Node node) {
        //The chile node  which will be returned
        Node retNode = node.createChildNode();
        retNode.parent=node;
        State state =retNode.state;
        CoastGuardBoat coastGuardBoat= state.coastGuardBoat;
        int x = coastGuardBoat.x;
        int y = coastGuardBoat.y;
        StateObject objectAtCoastGuard = state.grid[x][y];

        switch (action) {
            case ("Pick-up"):
               return applyPickAction(retNode,state,coastGuardBoat,objectAtCoastGuard);
            case ("Drop"):
                return applyDropAction(retNode,state,coastGuardBoat,objectAtCoastGuard);
            case ("Retrieve"):
                return applyRetrieveAction(retNode,state,coastGuardBoat,objectAtCoastGuard);

            case ("Left"):
                return applyLeftAction(retNode,state,coastGuardBoat,x,y);

            case ("Right"):
                return applyRightAction(retNode,state,coastGuardBoat,x,y);

            case ("Up"):
                return applyUpAction(retNode,state,coastGuardBoat,x,y);

            case ("Down"):
                return applyDownAction(retNode,state,coastGuardBoat,x,y);
            default:
                return null;
        }

    }

    //returns null if the board is at the end of the grid
    private Node applyDownAction(Node retNode, State state, CoastGuardBoat coastGuardBoat, int x,int y) {
        if(x==state.grid.length-1)
            return null;
        else
        {
            coastGuardBoat.x+=1;
            retNode.actionPerformedOn="down";
            applyTimeStep(state);
            return retNode;
        }
    }

    //returns null if the board is at the end of the grid
    private Node applyUpAction(Node retNode, State state, CoastGuardBoat coastGuardBoat,  int x,int y) {
        if(x==0)
            return null;
        else
        {
            coastGuardBoat.x-=1;
            retNode.actionPerformedOn="up";
            applyTimeStep(state);
            return retNode;
        }
    }

    //returns null if the board is at the end of the grid
    private Node applyRightAction(Node retNode, State state, CoastGuardBoat coastGuardBoat,  int x,int y) {
        if(y==state.grid[0].length-1)
            return null;
        else
        {
            coastGuardBoat.y+=1;
            retNode.actionPerformedOn="right";
            applyTimeStep(state);
            return retNode;
        }
    }

    //returns null if the board is at the end of the grid
    private Node applyLeftAction(Node retNode, State state, CoastGuardBoat coastGuardBoat,  int x,int y) {
        if(y==0)
            return null;
        else
        {
            coastGuardBoat.y-=1;
            retNode.actionPerformedOn="left";
            applyTimeStep(state);
            return retNode;
        }
    }

    //returns null if the drop action is not applicable(the coast guard boat is not at a station or the boat doesn't have passengers).
    private Node applyDropAction(Node retNode, State state, CoastGuardBoat coastGuardBoat, StateObject objectAtCoastGuard) {

        if(coastGuardBoat.passengersOnBoat==0)
            return null;

        if(objectAtCoastGuard!=null){
            if(objectAtCoastGuard instanceof Station)
            {
                Station station= (Station) objectAtCoastGuard;
                int numRescued= coastGuardBoat.dropPassengers(station);
                state.decreaseNumOfUnrescuedPassengers(numRescued);
                applyTimeStep(state);
                retNode.actionPerformedOn="drop";
                return retNode;

            }
        }
        return null;
    }

    //returns null if the retrive action is not applicable(the ship is not a wreck or the coast guard is not at wreck or the blackbox is retrived or damaged).
    private Node applyRetrieveAction(Node retNode, State state, CoastGuardBoat coastGuardBoat, StateObject objectAtCoastGuard) {

        if(objectAtCoastGuard!=null)
        {
            if(objectAtCoastGuard instanceof Ship)
            {
                Ship ship = (Ship) objectAtCoastGuard;
                if(ship.wrecked) {
                    if(!ship.blackBoxTaken && ship.damage<20)
                    {
                        ship.blackBoxTaken=true;
                        state.blackBoxesRetrived++;
                        state.numOfUndamagedBlackBoxes-=1;
                        applyTimeStep(state);
                        retNode.actionPerformedOn="retrieve";
                        return retNode;

                    }
                }
            }
            else
                return null;

        }

        return null;
    }

    //returns null if the Pick action is not applicable(the ship is  a wreck or the coast guard is not at a ship or the coast guard boat is full).
    private Node applyPickAction(Node retNode,State state,CoastGuardBoat coastGuardBoat,StateObject objectAtCoastGuard) {

        if(coastGuardBoat.isFull())
            return null;

        if(objectAtCoastGuard!=null)
        {
            if(objectAtCoastGuard instanceof Ship)
            {
                Ship ship = (Ship) objectAtCoastGuard;
                if(ship.wrecked)
                {
                    return null;
                }else if(ship.numOfPassengers>0){
                    coastGuardBoat.pickPassengersFromShip(ship);
                    applyTimeStep(state);
                    retNode.actionPerformedOn="pickup";
                    return retNode;
                }
                else
                {
                    return null;
                }
            }
            else
                return null;

        }

            return null;

    }



    private void applyTimeStep(State state) {
        StateObject[][] grid = state.grid;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                StateObject sObject = grid[i][j];
                if(sObject instanceof Ship)
                {
                    Ship ship= (Ship) sObject;

                    if(ship.wrecked)
                    {
                        ship.damage++;
                        if(ship.damage==20 && !ship.blackBoxTaken)
                        {
                            state.numOfDamagedBlackBoxes++;
                            state.numOfUndamagedBlackBoxes--;
                        }
                    }
                    else {
                        ship.numOfPassengers--;
                        state.numOfUnrescuedPassengers--;
                        state.numOfDeadPassengers++;
                        if(ship.numOfPassengers<=0)
                            ship.wrecked=true;
                    }

                }
            }
        }
    }


}
