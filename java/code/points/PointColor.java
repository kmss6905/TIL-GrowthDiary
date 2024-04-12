package points;

class Point {
    int x, y;           // coordinates
    Point next;         // next point with this color
    static int nPoints;
}
class PointColor {
    Point first;        // first point with this color
    PointColor(int color) { this.color = color; }
    private int color;  // color components

    int getColor() {
        return color;
    }
}