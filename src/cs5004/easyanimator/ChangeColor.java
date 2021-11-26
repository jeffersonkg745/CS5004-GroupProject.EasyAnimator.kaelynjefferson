package cs5004.easyanimator;

/**
 * This class represents the color change transformation on a shape - the shape transforms by
 * changing color to a new RGB value.
 */

public class ChangeColor extends AbstractTransformation {
  private Color color;

  /**
   * Constructs the color change transformation with the parameters name, fromTime, and toTime.
   *
   * @param name     the name of the transformation
   * @param fromTime the start time of the transformation
   * @param toTime   the to time of the transformation
   */
  public ChangeColor(String name, int fromTime, int toTime, Color color) {
    super(name, fromTime, toTime);

    this.color = color;
  }

  /**
   * Returns the color of the shape.
   *
   * @return the color as a Color object.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Returns a string representation of the transformation on the shape.
   *
   * @return a string representation of the transformation on the shape.
   */
  @Override
  public String toString() {
    String str;

    if (this.transformedShapes.isEmpty()) {
      throw new IllegalArgumentException("This transformation has not yet occurred.");
    } else {
      str = this.transformedShapes.get(0).getName() + " changes color from "
              + this.transformedShapes.get(0).getColor().toString() + " to "
              + this.color.toString() + " from t=" + this.getFromTime() + " to t="
              + this.getToTime() + "\n";
    }
    return str;
  }
}
