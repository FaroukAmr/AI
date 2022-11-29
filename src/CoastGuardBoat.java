public class CoastGuardBoat extends StateObject {
    int coastGuardCapacity;
    int x;
    int y;
    int passengersOnBoat;

    public CoastGuardBoat(int coastGuardCapacity, int xDimension, int yDimension) {
        this.coastGuardCapacity = coastGuardCapacity;
        this.x = xDimension;
        this.y = yDimension;
        passengersOnBoat=0;
    }

    @Override
    public StateObject clone() {
        return new CoastGuardBoat(coastGuardCapacity, x, y);
    }
}
