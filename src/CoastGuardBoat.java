public class CoastGuardBoat extends StateObject {
    int coastGuardCapacity;
    int x;
    int y;
    int passengersOnBoat;
    int remainingSpaceOnBoat;

    public CoastGuardBoat(int coastGuardCapacity, int x, int y) {
        this.coastGuardCapacity = coastGuardCapacity;
        this.x = x;
        this.y = y;
        this.passengersOnBoat=0;
        remainingSpaceOnBoat=coastGuardCapacity-passengersOnBoat;
    }

    public CoastGuardBoat(int coastGuardCapacity, int x, int y, int passengersOnBoat) {
        this.coastGuardCapacity = coastGuardCapacity;
        this.x = x;
        this.y = y;
        this.passengersOnBoat=passengersOnBoat;
        remainingSpaceOnBoat=coastGuardCapacity-passengersOnBoat;

    }

    @Override
    public StateObject clone() {
        return new CoastGuardBoat(coastGuardCapacity, x, y,passengersOnBoat);
    }

    public boolean isFull() {
        if(passengersOnBoat==coastGuardCapacity)
            return true;
        else
            return false;
    }

    public void pickPassengersFromShip(Ship ship) {
        if(ship.numOfPassengers<=remainingSpaceOnBoat)
        {
            passengersOnBoat+= ship.numOfPassengers;
            ship.numOfPassengers=0;
        }
        else if(ship.numOfPassengers>remainingSpaceOnBoat)
        {
            passengersOnBoat+=remainingSpaceOnBoat;
            ship.numOfPassengers-=remainingSpaceOnBoat;
        }
        updateRemainingSpaceOnBoat();
    }

    private void updateRemainingSpaceOnBoat() {
        remainingSpaceOnBoat=coastGuardCapacity-passengersOnBoat;
    }

    public int dropPassengers(Station station) {
        int numOfRescued=passengersOnBoat;
        station.peopleInStation+=passengersOnBoat;
        passengersOnBoat=0;
        updateRemainingSpaceOnBoat();

        return numOfRescued;
    }
}
