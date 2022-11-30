
// A class represinting the state of a coast guard problem
public class State implements Cloneable{
    StateObject[][] grid ;
    int numOfDeath;
    int numRetrived;
    int coastGuardXCoordinate;
    int coastGuardYCoordinate;
    int blackBoxesRetrived;
    int numOfUndamagedBlackBoxes;



    public State(StateObject[][] grid, int coastGuardXCoordinate, int coastGuardYCoordinate, int numOfUndamagedBlackBoxes) {
        this.grid = grid;
        this.coastGuardXCoordinate = coastGuardXCoordinate;
        this.coastGuardYCoordinate = coastGuardYCoordinate;
        this.numOfUndamagedBlackBoxes = numOfUndamagedBlackBoxes;
        numOfDeath=0;
        numRetrived=0;
        blackBoxesRetrived=0;
    }

    public State(StateObject[][] grid, int numOfDeath, int numRetrived, int coastGuardXCoordinate, int coastGuardYCoordinate, int blackBoxesRetrived, int numOfUndamagedBlackBoxes) {
        this.grid = grid;
        this.numOfDeath = numOfDeath;
        this.numRetrived = numRetrived;
        this.coastGuardXCoordinate = coastGuardXCoordinate;
        this.coastGuardYCoordinate = coastGuardYCoordinate;
        this.blackBoxesRetrived = blackBoxesRetrived;
        this.numOfUndamagedBlackBoxes = numOfUndamagedBlackBoxes;
    }


    //a method that returns a deep clone of the state
    public State clone()
    {
        StateObject[][] retGrid = new StateObject[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                retGrid[i][j] = grid[i][j].clone();
            }
        }
        State retState = new State(retGrid,numOfDeath,numRetrived,coastGuardXCoordinate,coastGuardYCoordinate,blackBoxesRetrived,numOfUndamagedBlackBoxes);

        return retState;
    }

}
