public class Station extends StateObject {
    int x;
    int y;

    public Station(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public StateObject clone() {
        return new Station(x,y);
    }
}
