import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point  implements Comparable<Point> {
    private int x;
    private int y;
    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // draws this point
    public   void draw() {
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
        if (this.compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        else if (this.y == that.y) {
            return 0.0;
        }
        else {
            return (this.y - that.y)/(this.x - that.x);
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
 }
