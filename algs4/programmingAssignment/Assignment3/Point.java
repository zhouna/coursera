import java.util.Comparator;

public class Point implements Comparable<Point> {
    
    public final Comparator<Point> SLOPE_ORDER = new BySlope();
    private final int x; // x coordinate
    private final int y; // y coordinate
  
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }
    
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    public int compareTo(Point that) {
      /* YOUR CODE HERE */
      if (this.x == that.x && this.y == that.y) {
        return 0;
      } else if ((this.y < that.y) || ((this.y == that.y) && (this.x < that.y))) {
        return -1;
      } else {
        return 1;
      }
    }
    
    public double slopeTo(Point that) {
      /* YOUR CODE HERE */
      if (that.x == this.x) {
        if ((that.x - this.y) > 0) {
          return Integer.MAX_VALUE;
        } else {
          return Integer.MIN_VALUE;
        }
      }
      return (double)(that.y - this.y) / (that.x - this.x);
    }
    
    private class BySlope implements Comparator<Point> {
      public int compare(Point point1, Point point2) {
        double d = slopeTo(point1) - slopeTo(point2);
        if (d < 0) {
          return -1;
        } else if (d == 0) {
          return 0;
        } else {
          return 1;
        }
      }
    }
}
