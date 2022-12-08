package code;

import java.util.*;

public  class SearchProcedure {
    String searchStrategy;
    SearchProblem searchProblem;
    //A hashmap that saves the states, so we don't have duplicate states
    static HashMap<String,Integer> hashMap = new HashMap<String, Integer>();


    public static void putInHashMap(State state){

        String str =""+state.coastGuardBoat.x+""+state.coastGuardBoat.y+""+state.numOfDeadPassengers+""+state.blackBoxesRetrieved +""+state.numOfUndamagedBlackBoxes+""+state.numOfDamagedBlackBoxes+""+state.numOfDeadPassengers+""+state.numOfNotRescuedPassengers +""+state.coastGuardBoat.passengersOnBoat;
        hashMap.put(str,1);

    }

    public static boolean checkHashMap(State state){

        String str =""+state.coastGuardBoat.x+""+state.coastGuardBoat.y+""+state.numOfDeadPassengers+""+state.blackBoxesRetrieved +""+state.numOfUndamagedBlackBoxes+""+state.numOfDamagedBlackBoxes+""+state.numOfDeadPassengers+""+state.numOfNotRescuedPassengers +""+state.coastGuardBoat.passengersOnBoat;
        return hashMap.get(str)==null;

    }

    public int evaluationFunction(Node startNode,Node endNode){

        return 0;
    }
    public  int heuristicFunctionOne(Node node){

        return 100-node.state.getNumOfNotRescuedPassengers();
    }
    public  int heuristicFunctionTwo(Node node){

        return 100-node.state.getNumOfUnSunkShips();
    }

    public static int heuristicFunction(Node node){
        int xCoastGuard=node.state.coastGuardBoat.x;
        int yCoastGuard=node.state.coastGuardBoat.y;
        StateObject[][] grid = node.state.grid;
        int nearestShipDis=100000;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] instanceof Ship){
                    Ship ship = (Ship) grid[i][j];
                    int distance = manhattanDistance(xCoastGuard,yCoastGuard,ship.x,ship.y);
                    if(distance<nearestShipDis)
                        nearestShipDis=distance;

                }

            }
        }
        return nearestShipDis;
    }


    public static int manhattanDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2)+Math.abs(y1-y2);
    }

    public SearchProcedure(String searchStrategy, SearchProblem searchProblem) {
        this.searchStrategy = searchStrategy;
        this.searchProblem = searchProblem;
    }

    public static String search(String searchStrategy, SearchProblem searchProblem){
        String res ="";
        hashMap = new HashMap<String, Integer>();
        switch (searchStrategy) {
            case ("BF") :
                res = BFsearch(searchProblem);
                break;
            case("DF"):
                res = DFsearch(searchProblem);
                break;
            case("ID"):
                res = IDsearch(searchProblem);
                break;
            case("DFMD"):
                res = DFMaxDepthSearch(searchProblem,44);
                break;
            case ("GR1"):
                res= heuristicSearch(searchProblem,1);
                break;
            case ("GR2"):
                res= heuristicSearch(searchProblem,2);
                break;
            case ("AS1"):
                res= heuristicSearch(searchProblem,3);
                break;
            case ("AS2"):
                res= heuristicSearch(searchProblem,4);
                break;
            case ("UC"):
                res= heuristicSearch(searchProblem,5);
                break;


        }
        return res;
    }

    private static String DFsearch(SearchProblem searchProblem) {
        String resString = "";
        String plan = "";
        String deaths = "";
        String retrieved = "";
        Stack<Node> stack = new Stack<>();
        Node initialStateNode = searchProblem.initialStateNode;
        stack.push(initialStateNode);
        int numOfNodesExpanded = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            numOfNodesExpanded++;
            if (searchProblem.isGoalState(node)) {
                plan = node.getActionPath();
                plan = plan.substring(1, plan.length());
                deaths = "" + node.state.numOfDeadPassengers;
                retrieved = "" + node.state.blackBoxesRetrieved;
                if(CoastGuard.enableVisuals){
                    CoastGuard.visualize(node);
                }
                break;
            }
            ArrayList<Node> children = searchProblem.getChildrenNodes(node);
            for (Node n :
                    children) {
                if (n != null && checkHashMap(n.state)) {
                    stack.push(n);
                    putInHashMap(n.state);
                    CoastGuardBoat cgb = n.state.coastGuardBoat;
                }

            }

        }
        return plan+";"+deaths+";"+retrieved+";"+numOfNodesExpanded;

    }

    private static String BFsearch(SearchProblem searchProblem) {
        String resString="";
        String plan="";
        String deaths="";
        String retrieved="";
        Queue<Node> queue = new LinkedList<>();
        hashMap = new HashMap<>();
        Node initialStateNode =searchProblem.initialStateNode;
        queue.add(initialStateNode);
        int numOfNodesExpanded=0;
        while(!queue.isEmpty())
        {
            Node node =queue.remove();
            numOfNodesExpanded++;
            if(searchProblem.isGoalState(node))
            {
                plan = node.getActionPath();
                plan=plan.substring(1,plan.length());
                deaths = ""+node.state.numOfDeadPassengers;
                retrieved=""+node.state.blackBoxesRetrieved;
                if(CoastGuard.enableVisuals){
                    CoastGuard.visualize(node);
                }

                break;
            }
            ArrayList<Node> children = searchProblem.getChildrenNodes(node);
            for (Node n :
                    children) {
                if(n!=null && checkHashMap(n.state)) {
                    queue.add(n);
                    putInHashMap(n.state);
                    CoastGuardBoat cgb = n.state.coastGuardBoat;
                }
            }

        }

        System.out.println();
        return plan+";"+deaths+";"+retrieved+";"+numOfNodesExpanded;

    }
    //
    private static String IDsearch(SearchProblem searchProblem) {
        String plan="";
        int depth=1;
        while(true)
        {
            hashMap = new HashMap<>();
            plan = DFMaxDepthSearch(searchProblem,depth);
            if(plan!="")
            {

                break;
            }
            depth++;
        }
        return plan;

    }


    private static String heuristicSearch(SearchProblem searchProblem, int heuristicNumber){
        String plan="";
        String deaths="";
        String retrieved="";
        PriorityQueue<Node> queue =null;
        switch (heuristicNumber){
            case 1:
                queue =  new PriorityQueue<Node>(1, new NodeComparatorOne());
                break;
            case 2:
                queue =  new PriorityQueue<Node>(1, new NodeComparatorTwo());
                break;
            case 3:
                queue =  new PriorityQueue<Node>(1, new NodeComparatorThree());
                break;
            case 4:
                queue =  new PriorityQueue<Node>(1, new NodeComparatorFour());
                break;
            case 5:
                queue =  new PriorityQueue<Node>(1, new NodeComparatorFive());
                break;
        }

        Node initialStateNode =searchProblem.initialStateNode;
        queue.add(initialStateNode);
        int numOfNodesExpanded=0;
        while(!queue.isEmpty())
        {
            Node node =queue.remove();
            numOfNodesExpanded++;
            if(searchProblem.isGoalState(node))
            {
                plan = node.getActionPath();
                plan=plan.substring(1);
                deaths = ""+node.state.numOfDeadPassengers;
                retrieved=""+node.state.blackBoxesRetrieved;
                if(CoastGuard.enableVisuals){
                    CoastGuard.visualize(node);
                }
                break;
            }
            ArrayList<Node> children = searchProblem.getChildrenNodes(node);
            for (Node n :
                    children) {
                if(n!=null && checkHashMap(n.state)) {
                    queue.add(n);
                    putInHashMap(n.state);

                }


            }

        }
        return plan+";"+deaths+";"+retrieved+";"+numOfNodesExpanded;
    }
    //optimize DFMaxDepthSearch
    private static String DFMaxDepthSearch2(SearchProblem searchProblem,int maxDepth) {
        String plan = "";
        String deaths = "";
        String retrieved = "";
        Stack<Node> stack = new Stack<>();
        Node initialStateNode = searchProblem.initialStateNode;
        stack.push(initialStateNode);
        int numOfNodesExpanded = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            numOfNodesExpanded++;
            if (searchProblem.isGoalState(node)) {
                plan = node.getActionPath();
                plan = plan.substring(1, plan.length());
                deaths = "" + node.state.numOfDeadPassengers;
                retrieved = "" + node.state.blackBoxesRetrieved;

                break;
            }
            if (node.getDepth() < maxDepth) {
                ArrayList<Node> children = searchProblem.getChildrenNodes(node);
                for (Node n :
                        children) {
                    if (n != null && checkHashMap(n.state)) {
                        stack.push(n);
                        putInHashMap(n.state);
                        CoastGuardBoat cgb = n.state.coastGuardBoat;
                    }
                }
            }

        }
        return plan + ";" + deaths + ";" + retrieved + ";" + numOfNodesExpanded;
    }



    private static String DFMaxDepthSearch(SearchProblem searchProblem, int d){
        String plan = "";
        String deaths = "";
        String retrieved = "";
        Stack<Node> stack = new Stack<>();
        Node initialStateNode = searchProblem.initialStateNode;
        stack.push(initialStateNode);
        int numOfNodesExpanded = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            numOfNodesExpanded++;
            if (searchProblem.isGoalState(node)) {
                plan = node.getActionPath();
                plan = plan.substring(1);
                deaths = "" + node.state.numOfDeadPassengers;
                retrieved = "" + node.state.blackBoxesRetrieved;
                if(CoastGuard.enableVisuals){
                    CoastGuard.visualize(node);
                }
                break;
            }
            ArrayList<Node> children = searchProblem.getChildrenNodes(node);
            if(children.get(0).getDepth()<d){
                for (Node n :
                        children) {
                    if (n != null && checkHashMap(n.state)) {
                        stack.push(n);
                        putInHashMap(n.state);
                    }

                }
            }


        }
        if(plan==""){
            return "";
        }
        return plan+";"+deaths+";"+retrieved+";"+numOfNodesExpanded;
    }
    public static void main(String[] args){
        String grid0 = "5,6;50;0,1;0,4,3,3;1,1,90;";
        String grid8 = "6,6;74;1,1;0,3,1,0,2,0,2,4,4,0,4,2,5,0;0,0,78,3,3,5,4,3,40;";
        String solution1 = CoastGuard.solve(grid0, "BF", false);
        String solution2 = CoastGuard.solve(grid0, "DF", false);
        String solution3 = CoastGuard.solve(grid0, "ID", false);
        String solution4 = CoastGuard.solve(grid0, "GR1", false);
        String solution5 = CoastGuard.solve(grid0, "GR2", false);
        String solution6 = CoastGuard.solve(grid0, "AS1", false);
        String solution7 = CoastGuard.solve(grid0, "AS2", false);
        String solution8 = CoastGuard.solve(grid0, "UC", false);
        System.out.println(solution1);
        System.out.println(solution2);
        System.out.println(solution3);
        System.out.println(solution4);
        System.out.println(solution5);
        System.out.println(solution6);
        System.out.println(solution7);
        System.out.println(solution8);
    }
}
