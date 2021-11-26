package cs5004.easyanimator.view;


import java.util.ArrayList;

import cs5004.easyanimator.ShapeAnimationModel;
import cs5004.easyanimator.ShapeSort;
import cs5004.easyanimator.Transformation;
import cs5004.easyanimator.TransformationSort;

public class TextView {

//public String createView(model);
//

  public String createView(ShapeAnimationModel model) {
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

}
