package code;

import java.util.*;

public  class SearchProcedure {
    String searchStrategy;
    SearchProblem searchProblem;
    //A hashamp that saves the states so we don't have duplicate states
    static HashMap<String,Integer> hashMap = new HashMap<String, Integer>();


    public static void putInHashMap(State state){

        String str =""+state.coastGuardBoat.x+""+state.coastGuardBoat.y+""+state.numOfDeath+""+state.numRetrived+""+state.numOfUndamagedBlackBoxes+""+state.numOfDamagedBlackBoxes+""+state.numOfDeadPassengers+""+state.numOfUnrescuedPassengers;
        hashMap.put(str,1);

    }

    public static boolean checkHashMap(State state){

        String str =""+state.coastGuardBoat.x+""+state.coastGuardBoat.y+""+state.numOfDeath+""+state.numRetrived+""+state.numOfUndamagedBlackBoxes+""+state.numOfDamagedBlackBoxes+""+state.numOfDeadPassengers+""+state.numOfUnrescuedPassengers;
        return hashMap.get(str)==null;

    }

    public int evaluationFunction(Node startNode,Node endNode){

        return 0;
    }
    public  int heuristicFunctionOne(Node node){

        return 100-node.state.getNumOfUnrescuedPassengers();
    }
    public  int heuristicFunctionTwo(Node node){

        return 100-node.state.getNumOfUnsunkShips();
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
               res = DFmaxDepthSearch(searchProblem,44);
               break;
           case ("GR1"):
               res=greedySearch(searchProblem,1);
               break;
           case ("GR2"):
               res=greedySearch(searchProblem,2);
               break;
           case ("AS1"):
               res=greedySearch(searchProblem,3);
               break;
           case ("AS2"):
               res=greedySearch(searchProblem,4);
               break;
           case ("UC"):
               res=greedySearch(searchProblem,5);
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
        Node intialStateNode = searchProblem.initialStateNode;
        stack.push(intialStateNode);
        int numOfnodesExpanded = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            numOfnodesExpanded++;
            if (searchProblem.isGoalState(node)) {
                plan = node.getActionPath();
                plan = plan.substring(1, plan.length());
                deaths = "" + node.state.numOfDeadPassengers;
                retrieved = "" + node.state.blackBoxesRetrived;

                break;
            }
            ArrayList<Node> children = searchProblem.getChildrenNodes(node);
            for (Node n :
                    children) {
                if (n != null && checkHashMap(n.state)) {
                    stack.push(n);
                    putInHashMap(n.state);
                    CoastGuardBoat cgb = n.state.coastGuardBoat;
//                    if(cgb.x ==1 && cgb.y==1)
//                        System.out.println("Heree");
//                    if(node.actionPerformedOn=="Pick")
//                        System.out.println("skfsgfg");
                }
//                else if(!checkHashMap(n.state))
//                {
////                    System.out.println("dsgfdsf");
//                    ;
//                }

            }

        }
        return plan+";"+deaths+";"+retrieved+";"+numOfnodesExpanded;

    }

    private static String BFsearch(SearchProblem searchProblem) {
        String resString="";
        String plan="";
        String deaths="";
        String retrieved="";
        Queue<Node> queue = new LinkedList<>();
        Node intialStateNode =searchProblem.initialStateNode;
        queue.add(intialStateNode);
        int numOfnodesExpanded=0;
        while(!queue.isEmpty())
        {
            Node node =queue.remove();
            numOfnodesExpanded++;
            if(searchProblem.isGoalState(node))
            {
                plan = node.getActionPath();
                plan=plan.substring(1,plan.length());
                deaths = ""+node.state.numOfDeadPassengers;
                retrieved=""+node.state.blackBoxesRetrived;

                break;
            }
            ArrayList<Node> children = searchProblem.getChildrenNodes(node);
            for (Node n :
                    children) {
                if(n!=null && checkHashMap(n.state)) {
                    queue.add(n);
                    putInHashMap(n.state);
                    CoastGuardBoat cgb = n.state.coastGuardBoat;
//                    if(cgb.x ==1 && cgb.y==1)
//                        System.out.println("Heree");
//                    if(node.actionPerformedOn=="Pick")
//                        System.out.println("skfsgfg");
                }
//                else if(!checkHashMap(n.state))
//                {
////                    System.out.println("dsgfdsf");
//                    ;
//                }

            }

        }

        System.out.println();
        return plan+";"+deaths+";"+retrieved+";"+numOfnodesExpanded;

    }
    private static String IDsearch(SearchProblem searchProblem){
        int stoppingDepth=1000;
        int d=0;
        Node intialStateNode = searchProblem.initialStateNode.cloneNode();

        //String res=DFmaxDepthSearch(searchProblem,100);

        while (d<stoppingDepth){
            CoastGuard p =new CoastGuard(intialStateNode);
            String temp =DFmaxDepthSearch(p,100);
            if(temp!=null)
                return temp;
            d++;
        }
        return null;
    }

    private static String greedySearch(SearchProblem searchProblem,int heuristicNumber){
        String resString="";
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

        Node intialStateNode =searchProblem.initialStateNode;
        queue.add(intialStateNode);
        int numOfnodesExpanded=0;
        while(!queue.isEmpty())
        {
            Node node =queue.remove();
            numOfnodesExpanded++;
            if(searchProblem.isGoalState(node))
            {
                plan = node.getActionPath();
                plan=plan.substring(1,plan.length());
                deaths = ""+node.state.numOfDeadPassengers;
                retrieved=""+node.state.blackBoxesRetrived;

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
        return plan+";"+deaths+";"+retrieved+";"+numOfnodesExpanded;
    }

    private static String DFmaxDepthSearch(SearchProblem searchProblem,int d){
        String resString = "";
        String plan = "";
        String deaths = "";
        String retrieved = "";
        Stack<Node> stack = new Stack<>();
        Node intialStateNode = searchProblem.initialStateNode;
        stack.push(intialStateNode);
        int numOfnodesExpanded = 0;
        int currentDepth=1;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            numOfnodesExpanded++;
            if (searchProblem.isGoalState(node)) {
                plan = node.getActionPath();
                plan = plan.substring(1, plan.length());
                deaths = "" + node.state.numOfDeadPassengers;
                retrieved = "" + node.state.blackBoxesRetrived;

                break;
            }
            ArrayList<Node> children = searchProblem.getChildrenNodes(node);
            if(children.get(0).getDepth()<d){
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
        if(plan==""){
            return null;
        }
        return plan+";"+deaths+";"+retrieved+";"+numOfnodesExpanded;
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
