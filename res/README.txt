CS5004 - Object Oriented Design - Section 2
Team 59833 - Fatima Choudhury & Kaelyn Jefferson
Assignment 6 - Easy Animator Model README

**************

OVERVIEW: 
The easy animation model that we created uses the general approach of first addressing Shapes and Transformations as separate entities (separate interfaces, abstract classes, and then classes that extend the abstract class) before relating them together using lists in the ShapeAnimationModelImpl class (which implements the ShapeAnimationModel interface). The ShapeAnimationModelImpl class is meant to be user-facing, and has createShape, removeShape, and createTransformation methods that apply a created transformation to a shape if the names of both match. 

In order to fully produce the text description output of the animation, using the animationReadback method, the model first sorts the list of shapes by appearance time(shapeList, added to in the makeShape method) before looping through the list of shapes and calls the toString method that is unique to each Shape (listing Name, Type, Min/Center, Width/X radius, Height/Y radius, Color, Appears, and Disappears) while simultaneously copying over all of the transformations associated with each Shape (kept in a list in AbstractShape) into a master List of Transformations, that are then sorted (by fromTime) before the toString method is called for each transformation.

The overall result calling the animationReadback method in ShapeAnimationModelImpl is the desired end result of a text output of first the Shapes that appear in the animation (sorted by appearance time), followed by an output of all of the transformations in the model, sorted by the time each starts.

In deciding on which data structures to use in the easy animation model, we explored the use of ArrayLists as well as HashMaps store both the Shapes and Transformations that were used in the model. The benefit of using HashMaps was the ability to use the Shape's name as the key and the Shape object itself as a value, and then reference the name against the Transformation that is to be applied to the Shape. We ultimately chose to use ArrayLists in order to be able to sort the Shape and Transformation lists by appearance time and start time, respectively, using the Comparator interface. 

**************

INTERFACES & CLASSES IN MODEL:

INTERFACE - ShapeAnimationModel:
The ShapeAnimationModel interface details the methods available to the animation model. Creation of Shapes, Transformations, and application of Transformations to Shapes.

CLASS - ShapeAnimationModelImpl:
Implements ShapeAnimationModel, combines Shapes and related Transformations, presents a text summary of the animation.


INTERFACE - Shape:
Details all of the operations that are performed by the Shapes - methods return information related to the Shape.
*Note: appearTime begins at t=1.*

ABSTRACT CLASS - AbstractShape:
Implements the methods in the Shape interface - abstracts common methods from Oval and Rectangle classes.

CLASS - Oval:
Extends methods from AbstractShape class and defines methods specific to the Oval shape. 

CLASS - Rectangle:
Extends methods from AbstractShape class and defines methods specific to the Rectangle shape. 

INTERFACE - Transformation:
Details all of the operations that are available to Transformations, specifically returns information related to the transformation and applies transformations to a shape via the transform method.
*Note: fromTime beings at t=1.*

ABSTRACT CLASS - AbstractTransformation:
Implements the methods in the Transformation interface - abstracts common methods from the Move, ChangeColor, Scale classes. 

CLASS - Move:
Extends methods from the AbstractTransformation class and defines methods specific to the Move class (transform, getNewLocation, updateLocation, toString).

CLASS - ChangeColor:
Extends methods from the AbstractTransformation class and defines methods specific to the ChangeColor class (getColor, toString).


CLASS - Scale:
Extends methods from the AbstractTransformation class and defines methods specific to the Scale class (toString).


HELPER CLASS - Point2D:
A helper class for the easy animation model, represents X and Y points as a Cartesian coordinate, used to set location of the Shapes, and in the Move class to set the destination of the transformation.

HELPER CLASS - Color:
A helper class for the easy animation model, represents the color as an RGB tuple in the format (R, G, B) (printed out on a scale of 0-1; inputted in traditional RGB values of 0-255).

HELPER CLASS - ShapeSort:
A helper class for the easy animation model, utilizes the Comparator interface to sort the list of Shapes by order of appearTime (least > greatest). 

HELPER CLASS - TransformationSort:
A helper class for the easy animation model, utilizes the Comparator interface to sort the list of Transformations by order of fromTime (least > greatest). 
