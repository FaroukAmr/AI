public  class Node {
    State state;
    Node[] children;
    Node parent;
    String actionPerformedOn;



    public Node(Node parent) {
        this.parent = parent;
    }

    public Node(State state, Node[] children, Node parent) {
        this.state = state;
        this.children = children;
        this.parent = parent;
    }

    public Node(State state, Node[] children, Node parent, String actionPerformedOn) {
        this.state = state;
        this.children = children;
        this.parent = parent;
        this.actionPerformedOn = actionPerformedOn;
    }

    //A method that returns a deep clone of the node
    public Node cloneNode(){
      Node retNode= new Node(parent);
      retNode.children= new Node[children.length];
      cloneChildrenNodes(this,retNode);
      retNode.state=this.state.clone();

      return retNode;
    }

    //This is a method that creates a deepClone of the state of the node in the other node
//    private void cloneNodeState(Node node, Node cloneNode) {
//        for (int i=0;i< node.state.length;i++) {
//
//            cloneNode.state[i]= node.state[i].clone();
//        }
//    }

    //A method that puts the same children nodes in the original node in another Node
    public static void cloneChildrenNodes(Node originalNode,Node cloneNode)
    {
        for (int i=0;i< originalNode.children.length;i++) {

            cloneNode.children[i]=originalNode.children[i];
        }
    }

    public String getAcitonPath() {
        String actionPath="";
        Node currParent=parent;
        while(currParent!=null)
        {
            actionPath=currParent.actionPerformedOn+";"+actionPath;
            currParent=parent;
        }
        return actionPath;
    }
}
