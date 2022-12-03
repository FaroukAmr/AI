package code;

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
        this.damage = 0;
        blackBoxTaken=false;
        wrecked=false;
    }

    public Ship(int numOfPassengers, int x, int y, boolean blackBoxTaken, int damage, boolean wrecked) {
        this.numOfPassengers = numOfPassengers;
        this.x = x;
        this.y = y;
        this.blackBoxTaken = blackBoxTaken;
        this.damage = damage;
        this.wrecked = wrecked;
    }

    @Override
    public StateObject clone() {
        return new Ship(numOfPassengers,x,y,blackBoxTaken,damage,wrecked);
    }
}
