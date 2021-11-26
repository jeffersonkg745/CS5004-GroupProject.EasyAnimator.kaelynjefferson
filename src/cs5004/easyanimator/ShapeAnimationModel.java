package cs5004.easyanimator;

import java.util.ArrayList;

/**
 * The ShapeAnimationModel interface represents all methods available to the animation model, which
 * describes shapes and their transformations.
 */

public interface ShapeAnimationModel {

  /**
   * Creates a Shape in the animation - the shape will later be transformed.
   *
   * @param shape the Shape object that will be added to the model
   */
  void makeShape(Shape shape);

  /**
   * Removes a shape from the animation model.
   *
   * @param shape the shape for removal
   */
  void removeShape(Shape shape);

  /**
   * Creates a Transformation in the model.
   *
   * @param transformation a transformation - move, scale, change color - to be added to a Shape
   */
  void createTransformation(Transformation transformation);

  /**
   * Creates a description of the animation model that first outputs the shapes that are utilized in
   * the model, then outputs the creation/disappearance times of the shapes, then outputs the
   * transformations that the shapes will undergo.
   *
   * @return a String representation of the animation model of the Shapes
   */
  String animationReadback();

  /**
   * Outputs a List of the Shapes in the Animation.
   *
   * @return an ArrayList of the Shapes in the Animation
   */
  ArrayList<Shape> getShape();
}
