package cs5004.easyanimator;

/**
 * This class represents a 2D point. This point is denoted in Cartesian coordinates as (x,y).
 */

public class Point2D {
  private double x;
  private double y;

  /**
   * Constructs a 2D point with the given coordinates.
   *
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }


  /**
   * Return the x-coordinates of this point.
   *
   * @return x-coordinate of the point
   */
  public double getX() {
    return x;
  }

  /**
   * Return the y-coordinates of this point.
   *
   * @return y coordinate of the point
   */
  public double getY() {
    return y;
  }

  /**
   * Returns a String representation of the cartesian coordinate.
   *
   * @return a string representation of the coordinate
   */
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }

  /**
   * Sets the x-coordinate of the position to an inputted value.
   *
   * @param x a given x coordinate
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Sets the y-coordinate of the position to an inputted value.
   *
   * @param y a given y coordinate
   */
  public void setY(double y) {
    this.y = y;
  }
}
