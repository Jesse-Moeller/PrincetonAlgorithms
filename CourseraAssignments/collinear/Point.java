import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point  implements Comparable<Point> {
    private int x;
    private int y;
    // constructs the point (x, y)
    public Point(int inX, int inY) {
        x = inX;
        y = inY;
    }
    // draws this point
    public   void draw() {
        StdDraw.setXscale(-1, 1);
        StdDraw.setYscale(-1, 1);
        StdDraw.setPenRadius(0.1);
        StdDraw.point(x, y);
    }
    // draws the line segment from this point to that point
    public   void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }         
    // string representation
    public String toString() {
        String touple = String.format("(%d, %d)", x, y);
        return touple;
    }
    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y!=that.y) {
            if (this.y < that.y) {
                return -1;
            }
            else {
                return 1;
            }
        }
        else {
            if (this.x != that.x) {
                if (this.x < that.x) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            else {
                return 0;
            }
        }
    }
    // the slope between this point and that point
    public double slopeTo(Point that) {
        double num = y - that.y;
        double denom = x - that.x;
        if (this.compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        else if (num == 0) {
            return 0.0;
        }
        else if (denom == 0) {
            return Double.POSITIVE_INFINITY;
        }
        else {
            return num / denom;
        }
    }

    public Comparator<Point> slopeOrder() {
        return new SlopeOrderComparator();
    }

    private class SlopeOrderComparator implements Comparator<Point> {
        private Point comparatorPoint;
        public SlopeOrderComparator() {
            comparatorPoint = new Point(x, y);
        }
        public int compare(Point v, Point w) {
            Double slope1 = comparatorPoint.slopeTo(v);
            Double slope2 = comparatorPoint.slopeTo(w);
            return slope1.compareTo(slope2);
        }
    }

    public static void main(String[] args) {
        Point pointA = new Point(1,1);
        Point pointB = new Point(3,1);
        Point pointC = new Point(2,4);
        Point pointD = new Point(5,2);
        Point pointE = new Point(1,4);
        Comparator<Point> slopeOrderA = pointA.slopeOrder();
        StdOut.println(slopeOrderA.compare(pointB, pointC)); // -1 (0 < 3)
        StdOut.println(slopeOrderA.compare(pointD, pointB)); // +1 (1/4 > 0)
        StdOut.println(slopeOrderA.compare(pointE, pointC)); // +1 (+infinity > 3)
        Comparator<Point> slopeOrderC = pointC.slopeOrder();
        StdOut.println(slopeOrderC.compare(pointC, pointB)); // -1 (-inf < -3)
        pointA.draw();
    }
 }
