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
        StateObject[] intialGridArray = genGridFromStringGrid(grid);
        int[] coastGuardCoordinates = getCoastGuardCoardinatesFromStringGrid(grid);
        int numOfUndamagedBlackBoxes = getNumOfUndamagedBlackBoxesFromGrid(intialGridArray);
        State intialState = new State(intialGridArray,coastGuardCoordinates[0],coastGuardCoordinates[1],numOfUndamagedBlackBoxes);
        Node intialNodeState = new Node(intialState,new Node[]{},null);
        CoastGuard coastGuardProblem = new CoastGuard(intialNodeState);


        return SearchProcedure.search(strategy,coastGuardProblem);
    }

    private static int getNumOfUndamagedBlackBoxesFromGrid(StateObject[] intialGridArray) {

        return -1;
    }

    private static int[] getCoastGuardCoardinatesFromStringGrid(String grid) {

        return null;
    }

    private static StateObject[] genGridFromStringGrid(String grid) {

        return null;
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

        return false;
    }

    @Override
    protected Node applyAction(String action, Node node) {
        return null;
    }
}
