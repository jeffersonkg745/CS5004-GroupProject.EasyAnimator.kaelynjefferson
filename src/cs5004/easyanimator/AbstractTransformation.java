package cs5004.easyanimator;

import java.util.ArrayList;

/**
 * Represents the abstraction of Transformation methods that can be applied to Shape object.
 */

public abstract class AbstractTransformation implements Transformation {
  protected String name;
  protected int fromTime;
  protected int toTime;
  protected ArrayList<Shape> transformedShapes;

  /**
   * Constructs the abstract transformation with the parameters name, fromTime, and toTime; includes
   * an array list of the transformed shapes.
   *
   * @param name     the name of the transformation
   * @param fromTime the start time of the transformation
   * @param toTime   the to time of the transformation
   */
  protected AbstractTransformation(String name, int fromTime, int toTime) {
    //account for impossible scenarios (fromTime or toTime being negative, toTime < fromTime
    if ((fromTime <= 0 || toTime <= 0)) {
      throw new IllegalArgumentException("Times cannot be negative.");

    } else if (toTime <= fromTime) {
      throw new IllegalArgumentException("End time cannot be before Start time");

    } else {
      this.name = name;
      this.fromTime = fromTime;
      this.toTime = toTime;
      this.transformedShapes = new ArrayList<>();
    }
  }

  /**
   * Returns the new location that the move transformation will move the shape to.
   *
   * @return a cartesian coordinate of the new location
   */
  public Point2D getNewLocation() {
    return null;
  }


  /**
   * Updates the current location of the shape (as identified by the getLocation method).
   */
  public void updateLocation() {
    //used in the move class, added to the Transformation interface as well as here in order to test
  }


  /**
   * Attaches the transformation to the given shape.
   *
   * @param shape the shape of interest to be transformed
   * @throws IllegalArgumentException if transformation occurs when shape is not present or when
   *                                  shape is undergoing a different transformation at the same
   *                                  time.
   */
  public void transform(Shape shape) throws IllegalArgumentException {
    //check to see if shape is undergoing a different transformation at the same time
    for (int i = 0; i < shape.getTransformation().size(); i++) {
      if (this.fromTime >= shape.getTransformation().get(i).getFromTime()
              && this.fromTime <= shape.getTransformation().get(i).getToTime()) {
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
      shape.getTransformation().add(this);
      this.transformedShapes.add(shape);

    }
  }

  /**
   * Returns the name of the transformation.
   *
   * @return the name of the transformation
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns the start time of the transformation.
   *
   * @return the start time of the transformation
   */
  @Override
  public int getFromTime() {
    return this.fromTime;
  }

  /**
   * Returns the end time of the transformation.
   *
   * @return the end time of the transformation
   */
  @Override
  public int getToTime() {
    return this.toTime;
  }

  /**
   * Returns a string representation of the transformation on the shape.
   *
   * @return a string representation of the transformation on the shape.
   */
  @Override
  public String toString() {
    return null;
  }
}
