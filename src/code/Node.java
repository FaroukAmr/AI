package code;

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


    //creates node with null constructor
    public Node() {
        this.parent=null;
    }

    //A method that returns a deep clone of the node
    public Node cloneNode(){
        Node retNode=null;
      if(parent!=null)
          retNode = new Node(parent);
      else
          retNode=new Node();
      retNode.children= new Node[children.length];
      cloneChildrenNodes(this,retNode);
      retNode.state=this.state.clone();

      return retNode;
    }

    public Node createChildNode(){

        Node retNode = new Node(this);
//        retNode.children= new Node[children.length];
        //cloneChildrenNodes(this,retNode);
        retNode.state=this.state.clone();

        return retNode;
    }

    //This is a method that creates a deepClone of the state of the node in the other node
//    private void cloneNodeState(code.Node node, code.Node cloneNode) {
//        for (int i=0;i< node.state.length;i++) {
//
//            cloneNode.state[i]= node.state[i].clone();
//        }
//    }

    //A method that puts the same children nodes in the original node in another code.Node
    public static void cloneChildrenNodes(Node originalNode,Node cloneNode)
    {
        for (int i=0;i< originalNode.children.length;i++) {

            cloneNode.children[i]=originalNode.children[i];
        }
    }

    public String getActionPath() {
        if(actionPerformedOn==null)
            return "";
//        if(parent==null)
//            return "";
        String actionPath="";
        Node currParent=parent;
        actionPath=parent.getActionPath()+","+this.actionPerformedOn;
        return actionPath;
    }
    public int getDepth(){
        if(parent==null){
            return 1;
        }
        return parent.getDepth()+1;

    }

}
