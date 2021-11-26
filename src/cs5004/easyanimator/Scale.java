package cs5004.easyanimator;

/**
 * This class represents the scale transformation on a shape - the shape transforms by changing
 * width and height.
 */

public class Scale extends AbstractTransformation {
  private double toWidth;
  private double toHeight;

  /**
   * Constructs the scale transformation with the parameters name, fromTime, and toTime.
   *
   * @param name     the name of the transformation
   * @param fromTime the start time of the transformation
   * @param toTime   the to time of the transformation
   * @param toWidth  the post-scaling width of the shape
   * @param toHeight the post-scaling height of the shape
   */
  public Scale(String name, int fromTime, int toTime, double toWidth, double toHeight) {

    super(name, fromTime, toTime);

    this.toWidth = toWidth;
    this.toHeight = toHeight;
  }


  /**
   * Returns a string representation of the transformation on the shape.
   *
   * @return a string representation of the transformation on the shape.
   */
  @Override
  public String toString() {
    String str = "";

    str = this.transformedShapes.get(0).getName() + " scales from width "
            + this.transformedShapes.get(0).getWidth() + ", Height: "
            + this.transformedShapes.get(0).getHeight() + " to Width: "
            + this.toWidth + " Height: " + this.toHeight
            + " from time t=" + this.getFromTime() + " to t="
            + this.getToTime() + "\n";
    return str;
  }
}
