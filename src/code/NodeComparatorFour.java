package code;

import java.util.Comparator;

public class NodeComparatorFour implements Comparator<Node> {


    @Override
    public int compare(Node a, Node b) {
        CoastGuard A = new CoastGuard(a);
        CoastGuard B = new CoastGuard(b);
        int heuristicA=a.state.getNumOfUnsunkShips()+A.costFunction(a);
        int heuristicB=b.state.getNumOfUnsunkShips()+B.costFunction(b);
        if(heuristicA<heuristicB){
            return -1;
        }
        else if(heuristicA>heuristicB)
            return 1;
        return 0;

    }
}
