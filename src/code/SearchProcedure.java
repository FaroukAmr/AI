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
    public  int heuristicFunction(Node node){
        return 0;
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
                plan = node.getAcitonPath();
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
                plan = node.getAcitonPath();
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
}
