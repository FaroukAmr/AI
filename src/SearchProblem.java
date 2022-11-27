public abstract class SearchProblem {
    String[] actions;
    Node initialState;
    int cost;

    public abstract int costFunction(Node startNode,Node endNode);


}
