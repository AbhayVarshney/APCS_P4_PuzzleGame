package Model;

public class CoordinatePoint {
    private int myX;
    private int myY;

    public CoordinatePoint(int x, int y) {
        this.myX = x;
        this.myY = y;
    }

    int getX() {
        return myX;
    }

    int getY() {
        return myY;
    }
}
