public abstract class Node {
    Object[] state;
    Node[] children;
    Node parent;


    public abstract boolean isGoalState();
}
