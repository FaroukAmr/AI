public class CoastGuard extends SearchProblem{
    static StateObject[][] grid;
    static int numOfDeaths=0;
    static int numOfRetrived=0;
    static String[] actions = {"Pick","Drop","Retrieve","Up","Down","Left","Right"};

    public  CoastGuard(Node intialNodeState) {
        super(actions,intialNodeState);


    }
    public static String GenGrid() {
        int xDimension = (int) (Math.random() * 50);
        int yDimension = (int) (Math.random() * 50);
        int numOfShips = ((int) (Math.random() * xDimension * yDimension / 2)) + 1;
        int numOfStations = ((int)( Math.random() * xDimension * yDimension / 2)) + 1;
        int shipCapacity = ((int) (Math.random() * 99)) + 1;
        int coastGuardCapacity = ((int) (Math.random() * 99)) + 1;
        grid = new StateObject[xDimension][yDimension];

        int[] coastGuarddIntialCoordinate = generateRandomCoordinate(xDimension, yDimension);
        grid[coastGuarddIntialCoordinate[0]][coastGuarddIntialCoordinate[1]] = new CoastGuardBoat(coastGuardCapacity, xDimension, yDimension);

        String retString = xDimension + "," + yDimension + ";" + coastGuarddIntialCoordinate[0] + "," + coastGuarddIntialCoordinate[1] + ";";
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

    public static String Solve(String grid, String strategy, boolean visualize) {
        StateObject[][] intialGridArray = genGridFromStringGrid(grid);
        int[] coastGuardCoordinates = getCoastGuardCoardinatesFromStringGrid(grid);
        int numOfUndamagedBlackBoxes = getNumOfUndamagedBlackBoxesFromGrid(intialGridArray);
        State intialState = new State(intialGridArray,coastGuardCoordinates[0],coastGuardCoordinates[1],numOfUndamagedBlackBoxes);
        Node intialNodeState = new Node(intialState,new Node[]{},null);
        CoastGuard coastGuardProblem = new CoastGuard(intialNodeState);


        return SearchProcedure.search(strategy,coastGuardProblem);
    }

    private static int getNumOfUndamagedBlackBoxesFromGrid(StateObject[][] intialGridArray) {
        int res=0;
        for (int i = 0; i < intialGridArray.length; i++) {
            for (int j = 0; j < intialGridArray[i].length; j++) {
                if(intialGridArray[i][j] instanceof  Ship && (((Ship) intialGridArray[i][j]).damage<20)) {
                    res++;
                }
            }
        }
        return res;
    }

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
        String[] coordinates = gridInfo[0].split(",");
        int m = Integer.parseInt(coordinates[0]);
        int n = Integer.parseInt(coordinates[1]);

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
            resGrid[x][x]=ship;
        }
        return resGrid;
    }

    public static void main(String[] args)
    {
        System.out.println(GenGrid());
    }

    @Override
    public int costFunction(Node startNode, Node endNode) {
        return 0;
    }

    @Override
    public boolean isGoalState(Node node) {
        StateObject[][] intialGridArray=node.state.grid;
        for (int i = 0; i < intialGridArray.length; i++) {
            for (int j = 0; j < intialGridArray[i].length; j++) {
                if(intialGridArray[i][j] instanceof  Ship && ((((Ship) intialGridArray[i][j]).damage<20)) || (((Ship) intialGridArray[i][j]).numOfPassengers>0)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected Node applyAction(String action, Node node) {
        return null;
    }
}
