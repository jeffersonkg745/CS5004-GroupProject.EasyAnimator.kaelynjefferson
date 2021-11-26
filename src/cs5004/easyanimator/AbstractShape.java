package cs5004.easyanimator;

import java.util.ArrayList;

/**
 * Represents the abstraction of methods that can be implemented by a shape object.
 */

public abstract class AbstractShape implements Shape {

  protected String name;
  protected Point2D currentLocation;
  protected Color color;
  protected int appearTime;
  protected int disappearTime;
  protected ArrayList<Transformation> transformationsList;
  protected double width;
  protected double height;

  //package private protections? should we add everything to a package?
  protected AbstractShape(String name, Point2D currentLocation, Color color, int appearTime,
                int disappearTime, double width, double height) {

    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width/height cannot be a negative or zero value.");
    }

    if (currentLocation.getX() < 0 || currentLocation.getY() < 0) {
      throw new IllegalArgumentException("You cannot put a shape at negative position.");
    }

    //check if appears and disappears is a valid time frame
    if (appearTime <= 0 || disappearTime <= 0 || disappearTime <= appearTime) {
      throw new IllegalArgumentException("You need a valid time frame "
              + "(not negative and it appears before it disappears).");
    }

    this.name = name;
    this.currentLocation = currentLocation;
    this.color = color;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
    this.transformationsList = new ArrayList<>();
    this.width = width;
    this.height = height;
  }

  /**
   * Returns the name of the Shape object.
   *
   * @return the name of the Shape object
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns the type of the Shape object (i.e. Rectangle, Oval, etc.)
   *
   * @return a String of the type of the shape
   */
  @Override
  public String getType() {
    return null;
  }

  /**
   * Returns the current location of the Shape as a Point2D object.
   *
   * @return the current location of the Shape
   */
  @Override
  public Point2D getLocation() {
    return this.currentLocation;
  }

  /**
   * Returns the color of the shape object (RGB) as a Color object.
   *
   * @return the color of the shape
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Returns the time the shape first appears in the animation, in the form of an integer.
   *
   * @return the time that the shape first appears
   */
  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  /**
   * Returns the time that the shape last appears in the animation, in the form of an integer.
   *
   * @return the time that the shape last appears
   */
  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  /**
   * Returns a list of the transformations performed on the Shape.
   *
   * @return a list of the transformations performed on the Shape
   */
  @Override
  public ArrayList<Transformation> getTransformation() {
    return this.transformationsList;
  }

  /**
   * Returns the width of the Shape as a double.
   *
   * @return the width of the Shape
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the Shape as a double.
   *
   * @return the height of Shape
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Returns a String representation of the Shape in a given format, used in printing the
   * ShapeAnimationModel.
   *
   * @return a String representation of the shape in the required format
   */
  public String toString() {
    return null;
  }


}
