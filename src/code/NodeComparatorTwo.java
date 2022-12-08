package code;

import java.util.Comparator;

public class NodeComparatorTwo implements Comparator<Node> {
    @Override
    public int compare(Node a, Node b) {
        int heuristicA=SearchProcedure.heuristicFunction(a);
        int heuristicB=SearchProcedure.heuristicFunction(b);
        if(heuristicA<heuristicB){
            return -1;
        }
        else if(heuristicA>heuristicB)
            return 1;
        return 0;

    }
}
