public class Ship  extends StateObject{
    int numOfPassengers;
    int x;
    int y;
    boolean blackBoxTaken;
    int damage;
    boolean wrecked;

    public Ship(int numOfPassengers, int x, int y) {
        this.numOfPassengers = numOfPassengers;
        this.x = x;
        this.y = y;
        this.damage = damage;
        blackBoxTaken=false;
        wrecked=false;
    }


    @Override
    public StateObject clone() {
        return null;
    }
}
