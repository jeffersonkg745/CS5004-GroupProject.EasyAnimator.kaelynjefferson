package cs5004.easyanimator;

import java.util.Comparator;

/**
 * A helper class that sorts Shapes by appearTime.
 */

public class ShapeSort implements Comparator<Shape> {

  /**
   * Compares one shape to another by time the shape appears on the animation model.
   *
   * @param s1 the first Shape
   * @param s2 the second Shape
   * @return the comparison value of the two Shapes
   */
  @Override
  public int compare(Shape s1, Shape s2) {
    return s1.getAppearTime() - s2.getAppearTime();
  }
}
