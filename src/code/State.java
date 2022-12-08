package code;

// A class representing the state of a coast guard problem
public class State implements Cloneable{
    StateObject[][] grid ;
//UnRescued
    CoastGuardBoat coastGuardBoat;
    int blackBoxesRetrieved;
    int numOfUndamagedBlackBoxes;
    int numOfDamagedBlackBoxes=0;
    int numOfDeadPassengers;
    int numOfNotRescuedPassengers;

    public State(StateObject[][] grid,CoastGuardBoat coastGuardBoat, int blackBoxesRetrieved, int numOfDeadPassengers) {
        this.grid = grid;

        this.coastGuardBoat = coastGuardBoat;
        this.blackBoxesRetrieved = blackBoxesRetrieved;
        this.numOfDeadPassengers = numOfDeadPassengers;
        calculateNumOfUndamagedBlackBoxes();
        calculateNumOfUnRescuedPassengers();
    }



    public State(StateObject[][] grid, CoastGuardBoat coastGuardBoat) {
        this.grid = grid;
        this.coastGuardBoat = coastGuardBoat;
        numOfDeadPassengers=0;
        blackBoxesRetrieved =0;
        calculateNumOfUndamagedBlackBoxes();
        calculateNumOfUnRescuedPassengers();

    }

    public State(StateObject[][] grid,CoastGuardBoat coastGuardBoat, int blackBoxesRetrieved) {
        this.grid = grid;
        this.coastGuardBoat = coastGuardBoat;
        this.blackBoxesRetrieved = blackBoxesRetrieved;
        calculateNumOfUndamagedBlackBoxes();
        calculateNumOfUnRescuedPassengers();

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
        State retState = new State(retGrid ,(CoastGuardBoat) coastGuardBoat.clone(), blackBoxesRetrieved,numOfDeadPassengers);

        return retState;
    }

    //calculates the number of un-rescued passengers
    public void calculateNumOfUnRescuedPassengers(){
        int numOfUnRescuedPassengers=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]!=null && grid[i][j] instanceof Ship) {
                    Ship ship =(Ship) grid[i][j];
                    numOfUnRescuedPassengers+=ship.numOfPassengers;
                }
            }
        }
        this.numOfNotRescuedPassengers =numOfUnRescuedPassengers;
        this.numOfNotRescuedPassengers +=coastGuardBoat.passengersOnBoat;
    }



    public int getNumOfNotRescuedPassengers() {
        return numOfNotRescuedPassengers;
    }

    public void decreaseNumOfUnRescuedPassengers(int numberToDecrease) {
        this.numOfNotRescuedPassengers -= numberToDecrease;
    }

    //calculates the number of unSunk ships
    public int getNumOfUnSunkShips(){
        int numOfUnSunkShips=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]!=null && grid[i][j] instanceof Ship) {
                    numOfUnSunkShips++;
                }
            }
        }
        return numOfUnSunkShips;
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


}
