package code;

import java.util.Comparator;

public class NodeComparatorOne implements Comparator<Node> {

    @Override
    public int compare(Node a, Node b) {
        int heuristicA=a.state.getNumUnRescuedPassengers();
        int heuristicB=b.state.getNumUnRescuedPassengers();

        if(heuristicA<heuristicB){
            return -1;
        }
        else if(heuristicA>heuristicB)
            return 1;
        return 0;

    }


}
