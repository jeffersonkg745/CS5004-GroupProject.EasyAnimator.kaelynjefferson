package cs5004.easyanimator;

/**
 * This class represents the move transformation on a shape - the shape transforms by changing
 * location to a new cartesian coordinate.
 */

public class Move extends AbstractTransformation {
  private Point2D newLocation;

  /**
   * Constructs the move transformation.
   *
   * @param name        the name of the specific move transformation
   * @param fromTime    the start time of the move transformation
   * @param toTime      the end time of the move transformation
   * @param newLocation the end location of the move transformation, in cartesian coordinates
   */
  public Move(String name, int fromTime, int toTime, Point2D newLocation) {
    super(name, fromTime, toTime);

    this.newLocation = newLocation;
  }

  /**
   * Returns the new location that the move transformation will move the shape to.
   *
   * @return a cartesian coordinate of the new location
   */
  public Point2D getNewLocation() {
    return this.newLocation;
  }


  /**
   * Updates the current location of the shape (as identified by the getLocation method).
   */
  public void updateLocation() {
    this.transformedShapes.get(0).getLocation().setX(newLocation.getX());
    this.transformedShapes.get(0).getLocation().setY(newLocation.getY());
  }


  /**
   * Attaches the transformation to the given shape.
   *
   * @param shape the shape of interest to be transformed
   * @throws IllegalArgumentException if transformation occurs when shape is not present or when
   *                                  shape is undergoing a different transformation at the same
   *                                  time.
   */
  @Override
  public void transform(Shape shape) throws IllegalArgumentException {
    //check to see if shape is undergoing a different transformation at the same time
    for (int i = 0; i < shape.getTransformation().size(); i++) {
      if (this.fromTime > shape.getTransformation().get(i).getFromTime()
              && this.fromTime < shape.getTransformation().get(i).getToTime()) {
        throw new IllegalArgumentException("Another transformation is in progress!");
      }
    }
    //check to see if the shape has not appeared yet
    if (this.fromTime < shape.getAppearTime()) {
      throw new IllegalArgumentException("The shape cannot be transformed before appearance.");
    } else if (this.fromTime > shape.getDisappearTime()) {
      //check to see if the shape has already disappeared
      throw new IllegalArgumentException("The shape cannot be transformed after disappearance.");
    } else {
      //adds transformations to the shape's list of transformations
      //adds shape to the transformation's list of transformed shapes - not sure if we need this tbh

      if (shape.getLocation().getX() == newLocation.getX()
              && shape.getLocation().getY() == newLocation.getY()) {
        throw new IllegalArgumentException("Cannot move to the current location.");
      }
      shape.getTransformation().add(this);
      this.transformedShapes.add(shape);
    }
  }

  /**
   * Returns a string representation of the transformation on the shape.
   *
   * @return a string representation of the transformation on the shape.
   */
  @Override
  public String toString() {
    if (this.transformedShapes.isEmpty()) {
      throw new IllegalArgumentException("This transformation has not occurred.");
    }
    String str = "";

    str = this.transformedShapes.get(0).getName() + " moves from ("
            + this.transformedShapes.get(0).getLocation().getX() + ","
            + this.transformedShapes.get(0).getLocation().getY() + ") "
            + "to (" + this.getNewLocation().getX() + ","
            + this.getNewLocation().getY() + ") from time t="
            + this.getFromTime() + " to t=" + this.getToTime() + "\n";

    this.updateLocation();
    return str;
  }

}
