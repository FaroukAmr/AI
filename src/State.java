
// A class represinting the state of a coast guard problem
public class State implements Cloneable{
    StateObject[][] grid ;
    int numOfDeath;
    int numRetrived;
    CoastGuardBoat coastGuardBoat;
    int blackBoxesRetrived;
    int numOfUndamagedBlackBoxes;
    int numOfDeadPassengers;
    int numOfUnrescuedPassengers;

    public State(StateObject[][] grid, int numOfDeath, int numRetrived, CoastGuardBoat coastGuardBoat, int blackBoxesRetrived, int numOfDeadPassengers) {
        this.grid = grid;
        this.numOfDeath = numOfDeath;
        this.numRetrived = numRetrived;
        this.coastGuardBoat = coastGuardBoat;
        this.blackBoxesRetrived = blackBoxesRetrived;
        this.numOfDeadPassengers = numOfDeadPassengers;
        calculateNumOfUndamagedBlackBoxes();
    }



    public State(StateObject[][] grid, CoastGuardBoat coastGuardBoat) {
        this.grid = grid;
        this.coastGuardBoat = coastGuardBoat;
        numOfDeath=0;
        numRetrived=0;
        blackBoxesRetrived=0;
        calculateNumOfUndamagedBlackBoxes();
    }

    public State(StateObject[][] grid, int numOfDeath, int numRetrived,CoastGuardBoat coastGuardBoat, int blackBoxesRetrived) {
        this.grid = grid;
        this.numOfDeath = numOfDeath;
        this.numRetrived = numRetrived;
        this.coastGuardBoat = coastGuardBoat;
        this.blackBoxesRetrived = blackBoxesRetrived;
        calculateNumOfUndamagedBlackBoxes();
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
        State retState = new State(retGrid,numOfDeath,numRetrived, (CoastGuardBoat) coastGuardBoat.clone(),blackBoxesRetrived,numOfDeadPassengers);

        return retState;
    }

    //calculates the number of unrescued passengers
    public int calculateNumOfUnrescuedPassengers(){
        int numOfUnrescuedPassengers=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]!=null && grid[i][j] instanceof Ship) {
                    Ship ship =(Ship) grid[i][j];
                    numOfUnrescuedPassengers+=ship.numOfPassengers;
                }
            }
        }
        this.numOfUnrescuedPassengers=numOfUnrescuedPassengers;
        return numOfUnrescuedPassengers;
    }



    public int getNumOfUnrescuedPassengers() {
        return numOfUnrescuedPassengers;
    }

    public void decreaseNumOfUnrescuedPassengers(int numberToDecrease) {
        this.numOfUnrescuedPassengers -= numberToDecrease;
    }

    //calculates the number of unsunk ships
    public int getNumOfUnsunkShips(){
        int numOfUnsunkShips=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]!=null && grid[i][j] instanceof Ship) {
                    numOfUnsunkShips++;
                }
            }
        }
        return numOfUnsunkShips;
    }


    //calculates number of undamaged black boxes in the current state
      void calculateNumOfUndamagedBlackBoxes() {
         numOfUndamagedBlackBoxes=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] instanceof  Ship && (((Ship) grid[i][j]).damage<20)) {
                    numOfUndamagedBlackBoxes++;
                }
            }
        }

    }

}
