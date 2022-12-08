package code;

// A class represinting the state of a coast guard problem
public class State implements Cloneable{
    StateObject[][] grid ;

    CoastGuardBoat coastGuardBoat;
    int blackBoxesRetrived;
    int numOfUndamagedBlackBoxes;
    int numOfDamagedBlackBoxes=0;
    int numOfDeadPassengers;
    int numOfUnrescuedPassengers;

    public State(StateObject[][] grid,CoastGuardBoat coastGuardBoat, int blackBoxesRetrived, int numOfDeadPassengers) {
        this.grid = grid;

        this.coastGuardBoat = coastGuardBoat;
        this.blackBoxesRetrived = blackBoxesRetrived;
        this.numOfDeadPassengers = numOfDeadPassengers;
        calculateNumOfUndamagedBlackBoxes();
        calculateNumOfUnrescuedPassengers();
    }



    public State(StateObject[][] grid, CoastGuardBoat coastGuardBoat) {
        this.grid = grid;
        this.coastGuardBoat = coastGuardBoat;
        numOfDeadPassengers=0;
        blackBoxesRetrived=0;
        calculateNumOfUndamagedBlackBoxes();
        calculateNumOfUnrescuedPassengers();

    }

    public State(StateObject[][] grid,CoastGuardBoat coastGuardBoat, int blackBoxesRetrived) {
        this.grid = grid;
        this.coastGuardBoat = coastGuardBoat;
        this.blackBoxesRetrived = blackBoxesRetrived;
        calculateNumOfUndamagedBlackBoxes();
        calculateNumOfUnrescuedPassengers();

    }


    //a method that returns a deep clone of the state
    public State clone()
    {
        StateObject[][] retGrid = new StateObject[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]==null)
                    retGrid[i][j] = null;
                else
                    retGrid[i][j] = grid[i][j].clone();
            }
        }
        State retState = new State(retGrid ,(CoastGuardBoat) coastGuardBoat.clone(),blackBoxesRetrived,numOfDeadPassengers);

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
        this.numOfUnrescuedPassengers+=coastGuardBoat.passengersOnBoat;
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

                StateObject stateObject= grid[i][j];
                if(stateObject instanceof  Ship)
                {
                    Ship ship = (Ship) stateObject;
                    if(ship.damage<20 && !ship.blackBoxTaken)
                        numOfUndamagedBlackBoxes++;
                }
            }
        }

    }


    void calculateNumOfdamagedBlackBoxes() {
        numOfDamagedBlackBoxes = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                StateObject stateObject= grid[i][j];
                if(stateObject instanceof  Ship)
                {
                    Ship ship = (Ship) stateObject;
                    if(ship.damage>20 && !ship.blackBoxTaken)
                        numOfDamagedBlackBoxes++;
                }
            }
        }

    }

}
