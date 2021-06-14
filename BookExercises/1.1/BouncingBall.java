import edu.princeton.cs.algs4.StdDraw;

public class BouncingBall {
   public static void main(String[] args) {
        StdDraw.setXscale(-1,1);
        StdDraw.setYscale(-1,1);
        StdDraw.enableDoubleBuffering();
        double x = Math.random();
        double y = Math.random();
        double xvelocity = Math.random();
        double yvelocity = Math.random();
        double norm = Math.sqrt(Math.pow(xvelocity,2)+Math.pow(yvelocity,2));
        xvelocity/=20*norm;
        yvelocity/=20*norm;
        while(true){
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(0, 0, 1);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(x, y, 0.05);
            StdDraw.show();
            StdDraw.pause(20);
            if (Math.abs(x + xvelocity) > 1.0 - 0.05) xvelocity *= -1;
            if (Math.abs(y + yvelocity) > 1.0 - 0.05) yvelocity *= -1;
            x+=xvelocity;
            y+=yvelocity;
       }
   } 
}
