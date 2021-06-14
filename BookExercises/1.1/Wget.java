import edu.princeton.cs.algs4.*;

public class Wget {
    public static void main(String[] args) {
        String lnk = args[0];
        In webpage = new In(lnk);
        String content = webpage.readAll();

        StdOut.println(content);

    }
}
