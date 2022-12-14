package code;

import java.util.ArrayList;

public abstract class SearchProblem {
    String[] actions;
    Node initialStateNode;

    public SearchProblem(String[] actions, Node initialStateNode) {
        this.actions = actions;
        this.initialStateNode = initialStateNode;
    }

    public abstract int costFunction(Node endNode);
    public abstract boolean isGoalState(Node node);

    //returns the children of the node after applying actions on it (if the action is applicable) with the actions preformed
    //returns an array of two elements the first is an arraylist of the children
    public ArrayList<Node> getChildrenNodes(Node node)
    {
        ArrayList<Node> children = new ArrayList<>();
        for (int i = 0; i <actions.length; i++) {
            String action =actions[i];
            Node child = applyAction(action,node);
            if(child!=null) {
                children.add(child);
            }
        }

        return  children;
    }

    protected abstract Node applyAction(String action, Node node);

}
