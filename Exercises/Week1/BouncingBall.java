import edu.princeton.cs.algs4.StdDraw;

public class BouncingBall {
   public static void main(String[] args) {
        StdDraw.setXscale(-1.05,1.05);
        StdDraw.setYscale(-1.05,1.05);
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
            StdDraw.filledSquare(0, 0, 1.05);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(x, y, 0.05);
            StdDraw.show();
            StdDraw.pause(20);
            x+=xvelocity;
            y+=yvelocity;
            if(Math.abs(x)>1){
                xvelocity*=-1;
                if(x>0) x = 2 - x; else x = -2-x;
            }
            if(Math.abs(y)>1){
                yvelocity*=-1;
                if(y>0) y = 2 - y; else y = -2-y;
            }
       }
   } 
}
