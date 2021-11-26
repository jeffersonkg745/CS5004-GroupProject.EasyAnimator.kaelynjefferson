package cs5004.easyanimator;

/**
 * The Transformation interface represents all methods available to the animation model in terms of
 * transformations. The classes for each operation implement the interface through extension of the
 * abstract class AbstractTransformation.
 */

public interface Transformation {

  /**
   * Attaches the transformation to the given shape.
   *
   * @param shape the shape of interest to be transformed
   * @throws IllegalArgumentException if transformation occurs when shape is not present or when
   *                                  shape is undergoing a different transformation at the same
   *                                  time.
   */
  void transform(Shape shape) throws IllegalArgumentException;

  /**
   * Returns the name of the transformation.
   *
   * @return the name of the transformation
   */
  String getName();

  /**
   * Returns the start time of the transformation.
   *
   * @return the start time of the transformation
   */
  int getFromTime();

  /**
   * Returns the end time of the transformation.
   *
   * @return the end time of the transformation
   */
  int getToTime();

  /**
   * Returns the new location that the move transformation will move the shape to.
   *
   * @return a cartesian coordinate of the new location
   */
  Point2D getNewLocation();

  /**
   * Updates the current location of the shape (as identified by the getLocation method).
   */
  void updateLocation();

  /**
   * Returns a string representation of the transformation on the shape.
   *
   * @return a string representation of the transformation on the shape.
   */
  String toString();

}
