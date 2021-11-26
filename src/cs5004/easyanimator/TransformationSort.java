package cs5004.easyanimator;

import java.util.Comparator;

/**
 * A helper class that sorts Transformations by fromTime.
 */

public class TransformationSort implements Comparator<Transformation> {

  /**
   * Compares one transformation to another by time the transformation appears on the animation
   * model.
   *
   * @param t1 the first Transformation
   * @param t2 the second Transformation
   * @return the comparison value of the two Transformations
   */
  @Override
  public int compare(Transformation t1, Transformation t2) {
    return t1.getFromTime() - t2.getFromTime();
  }
}
