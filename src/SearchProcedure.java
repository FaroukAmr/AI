public abstract class SearchProcedure {
    String searchStrategy;
    SearchProblem searchProblem;

    public int evaluationFunction(Node startNode,Node endNode){
        return 0;
    }
    public abstract int heuristicFunction(Node node);
}
