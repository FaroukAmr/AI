import java.util.ArrayList;
import java.util.Stack;

public  class SearchProcedure {
    String searchStrategy;
    SearchProblem searchProblem;

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
       switch (searchStrategy) {
           case ("BFS") :
               res = BFsearch(searchProblem);
               break;
           case(""):
               break;


       }
        return "";
    }

    private static String BFsearch(SearchProblem searchProblem) {
        String resString="";
        String plan="";
        String deaths="";
        Stack<Node> stack = new Stack<>();
        Node intialStateNode =searchProblem.initialStateNode;
        stack.add(intialStateNode);
        int numOfnodesExpanded=0;
        while(!stack.isEmpty())
        {
            Node node =stack.pop();
            numOfnodesExpanded++;
            if(searchProblem.isGoalState(node))
            {
                plan = node.getAcitonPath();
                //death = searchProblem.
                //blacknoxes
                break;
            }
            ArrayList<Node> children = searchProblem.getChildrenNodes(node);
            stack.addAll(children);
        }

        return plan+";"+numOfnodesExpanded;

    }
}
