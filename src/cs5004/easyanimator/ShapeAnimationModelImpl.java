package cs5004.easyanimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the ShapeAnimationModel interface.
 */

public class ShapeAnimationModelImpl implements ShapeAnimationModel {
  private List<Shape> shapeList;
  private List<Transformation> transformationList;

  /**
   * Constructs a ShapeAnimationModelImpl instance.
   */
  public ShapeAnimationModelImpl() {
    this.shapeList = new ArrayList<>();
    this.transformationList = new ArrayList<>();
  }

  /**
   * Creates a Shape in the animation - the shape will later be transformed.
   *
   * @param shape the Shape object that will be added to the model
   */
  @Override
  public void makeShape(Shape shape) {
    //used a list here instead of a dictionary; can switch to use a dictionary instead
    for (int i = 0; i < this.getShape().size(); i++) {
      if (shape.getName().equalsIgnoreCase(this.getShape().get(i).getName())) {
        throw new IllegalArgumentException("Cannot add shape with name that already exists.");
      }
    }
    this.shapeList.add(shape);
  }

  /**
   * Removes a shape from the animation model.
   *
   * @param shape the shape for removal
   */
  @Override
  public void removeShape(Shape shape) {
    //check to make sure that the shape even exists in the animation in the first place
    if (shapeList.contains(shape)) {
      this.shapeList.remove(shape);
    } else {
      throw new IllegalArgumentException("No such shape in animation.");
      //test comment
    }

  }

  /**
   * Creates a Transformation in the model.
   *
   * @param transformation a transformation - move, scale, change color - to be added to a Shape
   */
  @Override
  public void createTransformation(Transformation transformation) {
    this.transformationList.add(transformation);
    for (Shape currentShape : this.getShape()) {
      if (transformation.getName().equals(currentShape.getName())) {
        transformation.transform(currentShape);
      }
      //throw new IllegalArgumentException("Cannot find shape to apply transformation.");
    }
  }

  /**
   * Creates a description of the animation model that first outputs the shapes that are utilized in
   * the model, then outputs the creation/disappearance times of the shapes, then outputs the
   * transformations that the shapes will undergo.
   *
   * @return a String representation of the animation model of the Shapes
   */
  @Override
  public String animationReadback() {
    String str = "";

    if (this.shapeList.isEmpty()) {
      throw new IllegalArgumentException("There are no shapes in this animation.");
    } else {
      str += "Shapes: \n";

      //loop through the shapesList and first add every single operation from all shapes into
      //a separate list for printing at the end, by order of occurrence
      ArrayList<Transformation> transformationSummary = new ArrayList<>();
      this.shapeList.sort(new ShapeSort());

      for (int i = 0; i < this.shapeList.size(); i++) {
        transformationSummary.addAll(shapeList.get(i).getTransformation());
        str += shapeList.get(i).toString() + "\n";
      }
      if (!transformationSummary.isEmpty()) {
        str += "\nAnimations: \n";
        for (int j = 0; j < transformationSummary.size(); j++) {
          transformationSummary.sort(new TransformationSort());
          str += transformationSummary.get(j).toString();
        }
      }
      return str;
    }
  }


  /**
   * Outputs a List of the Shapes in the Animation.
   *
   * @return an ArrayList of the Shapes in the Animation
   */
  @Override
  public ArrayList<Shape> getShape() {
    return new ArrayList<>(shapeList);
  }
}