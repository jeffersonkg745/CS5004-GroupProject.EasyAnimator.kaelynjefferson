package cs5004.easyanimator;

/**
 * Shape Oval extends AbstractShape and specifically implements methods that have unique elements
 * for the case of Ovals.
 */

public class Oval extends AbstractShape {
  private final String type;

  /**
   * Constructs an instance of an Oval.
   *
   * @param name            the name of the Oval as a String
   * @param currentLocation the location as per center of the Oval
   * @param color           the color of the Oval (RGB)
   * @param appearTime      Appear time of the Oval
   * @param disappearTime   Disappear time of the Oval
   * @param xRadius         the x-axis radius of the Oval, used to calculate width
   * @param yRadius         the y-axis radius of the Oval, used to calculate height
   */
  public Oval(String name, Point2D currentLocation, Color color, int appearTime,
              int disappearTime, double xRadius, double yRadius) {
    super(name, currentLocation, color, appearTime, disappearTime, xRadius, yRadius);

    this.type = "Oval";
  }

  /**
   * Returns the width of the Shape as a double.
   *
   * @return the width of the Shape
   */
  @Override
  public double getWidth() {
    //actual width of an Oval is radius (inputted width) * 2
    return this.width * 2;
  }

  /**
   * Returns the height of the Shape as a double.
   *
   * @return the height of the Shape
   */
  @Override
  public double getHeight() {
    //actual height of an Oval is radius (inputted height) * 2
    return this.height * 2;
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

  @Override
  public String toString() {
    String str = "";
    str = "Name: " + this.getName() + "\n"
            + "Type: " + this.getType() + "\n"
            + "Center: " + this.getLocation() + ", X radius: " + this.getWidth() / 2
            + ", Y radius: " + this.getHeight() / 2 + ", Color: " + this.getColor() + "\n"
            + "Appears at t=" + this.getAppearTime() + "\n"
            + "Disappears at t=" + this.getDisappearTime();

    return str;
  }
}
