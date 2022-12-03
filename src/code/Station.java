package code;

public class Station extends StateObject {
    int x;
    int y;
    int peopleInStation;

    public Station(int x, int y) {
        this.x = x;
        this.y = y;
        peopleInStation =0;
    }

    public Station(int x, int y, int numOfRescued) {
        this.x = x;
        this.y = y;
        this.peopleInStation = numOfRescued;
    }

    @Override
    public StateObject clone() {
        return new Station(x,y,peopleInStation);
    }
}
