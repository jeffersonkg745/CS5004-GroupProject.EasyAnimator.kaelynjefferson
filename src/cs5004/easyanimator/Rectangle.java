package cs5004.easyanimator;

/**
 * Shape Rectangle extends AbstractShape and specifically implements methods that have unique
 * elements for the case of Rectangle.
 */

public class Rectangle extends AbstractShape {
  private final String type;

  /**
   * Constructs an instance of a Rectangle.
   *
   * @param name            the name of the Rectangle as a String
   * @param currentLocation the location as per the bottom left corner of the Oval
   * @param color           the color of the Rectangle (RGB)
   * @param appearTime      Appear time of the Rectangle
   * @param disappearTime   Disappear time of the Rectangle
   * @param width           the width of the Rectangle
   * @param height          the height radius of the Rectangle
   */
  public Rectangle(String name, Point2D currentLocation, Color color, int appearTime,
                   int disappearTime, double width, double height) {

    super(name, currentLocation, color, appearTime, disappearTime, width, height);

    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width/Height value cannot be negative or zero.");
    }

    this.type = "Rectangle";
  }

  /**
   * Returns the type of the Shape object (i.e. Rectangle, Oval, etc.)
   *
   * @return a String of the type of the shape
   */
  @Override
  public String getType() {
    return this.type;
  }

  /**
   * Returns a String representation of the Shape in a given format, used in printing the
   * ShapeAnimationModel.
   *
   * @return a String representation of the shape in the required format
   */
  @Override
  public String toString() {
    //use StringBuilder?
    return "Name: " + this.getName() + "\n"
            + "Type: " + this.getType() + "\n"
            + "Min corner: " + this.getLocation() + ", Width: " + this.getWidth() + ", Height: "
            + this.getHeight() + ", Color: " + this.getColor() + "\n"
            + "Appears at t=" + this.getAppearTime() + "\n"
            + "Disappears at t=" + this.getDisappearTime();

  }
}
