public class Brute {
  public static void main(String[] args) {
    StdDraw.setXscale(0, 10);  
    StdDraw.setYscale(0, 10); 
    
    Point[] points = new Point[4];
    for (int i = 1; i <= points.length; i++) {
      points[i] = new Point(i, 2*i);
      points[i].draw();
      if (i != 1) {
        points[i].drawTo(points[i-1]);
      }
    }
  }
}