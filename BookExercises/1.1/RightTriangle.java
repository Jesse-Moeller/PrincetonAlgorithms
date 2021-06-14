import edu.princeton.cs.algs4.*;

public class RightTriangle {
    public static void main(String[] args) {
    double x0 = Math.random();
    double y0 = Math.random();
    double x1 = Math.random();
    double y1 = Math.random();
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.line(x0, y0, x1, y1);
    StdDraw.line(x0, y0, x0, y1);
    StdDraw.line(x0, y1, x1, y1);
    double xcenter = x0 + (x1-x0)/2;
    double ycenter = y0 + (y1-y0)/2;
    double radius = Math.sqrt(Math.pow((x0-xcenter),2)+Math.pow((y0-ycenter),2));
    StdDraw.circle(xcenter, ycenter, radius);
    }   
}
