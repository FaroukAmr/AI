package code;

import java.util.Comparator;

public class NodeComparatorOne implements Comparator<Node> {

    @Override
    public int compare(Node a, Node b) {
        int heuristicA=a.state.getNumOfNotRescuedPassengers();
        int heuristicB=b.state.getNumOfNotRescuedPassengers();

        if(heuristicA<heuristicB){
            return -1;
        }
        else if(heuristicA>heuristicB)
            return 1;
        return 0;

    }


}
