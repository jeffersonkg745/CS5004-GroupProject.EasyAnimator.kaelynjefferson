package cs5004.easyanimator;

import java.util.ArrayList;
/**
 * The Shape interface represents all methods available to the animation model in
 * terms of shapes. The classes for each operation implement the interface through
 * extension of the abstract class AbstractShape.
 */

public interface Shape {

  /**
   * Returns the name of the Shape object.
   *
   * @return the name of the Shape object
   */
  String getName();

  /**
   * Returns the type of the Shape object (i.e. Rectangle, Oval, etc.)
   *
   * @return a String of the type of the shape
   */
  String getType();

  /**
   * Returns the current location of the Shape as a Point2D object.
   *
   * @return the current location of the Shape
   */
  Point2D getLocation();

  /**
   * Returns the color of the shape object (RGB) as a Color object.
   *
   * @return the color of the shape
   */
  Color getColor();

  /**
   * Returns the time the shape first appears in the animation, in the form of an integer.
   *
   * @return the time that the shape first appears
   */
  int getAppearTime();

  /**
   * Returns the time that the shape last appears in the animation, in the form of an integer.
   *
   * @return the time that the shape last appears
   */
  int getDisappearTime();

  /**
   * Returns a list of the transformations performed on the Shape.
   *
   * @return a list of the transformations performed on the Shape
   */
  ArrayList<Transformation> getTransformation();

  /**
   * Returns the width of the Shape as a double.
   *
   * @return the width of the Shape
   */
  double getWidth();

  /**
   * Returns the height of the Shape as a double.
   *
   * @return the height of the Shape
   */
  double getHeight();

  /**
   * Returns a String representation of the Shape in a given format, used in printing the
   * ShapeAnimationModel.
   *
   * @return a String representation of the Shape object
   */
  String toString();
}
