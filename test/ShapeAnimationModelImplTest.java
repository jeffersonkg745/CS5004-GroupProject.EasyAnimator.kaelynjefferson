import org.junit.Before;
import org.junit.Test;

import cs5004.easyanimator.ChangeColor;
import cs5004.easyanimator.Color;
import cs5004.easyanimator.Move;
import cs5004.easyanimator.Oval;
import cs5004.easyanimator.Point2D;
import cs5004.easyanimator.Rectangle;
import cs5004.easyanimator.Scale;
import cs5004.easyanimator.Shape;
import cs5004.easyanimator.ShapeAnimationModel;
import cs5004.easyanimator.ShapeAnimationModelImpl;
import cs5004.easyanimator.Transformation;

import static org.junit.Assert.assertEquals;

/**
 * A test class for the Animation model; tests all relevant methods.
 */
public class ShapeAnimationModelImplTest {
  private ShapeAnimationModel animation;
  private Shape rectangle;
  private Shape oval;

  private Color red;
  private Color blue;

  @Before
  public void setUp() {
    animation = new ShapeAnimationModelImpl();
    red = new Color(255, 0, 0);
    blue = new Color(0, 0, 255);

    rectangle = new Rectangle("R", new Point2D(0, 0), red, 1,
            60, 25, 30);

    oval = new Oval("O", new Point2D(5, 5), blue, 1,
            100, 60, 60);

  }

  //Ideal case: making a shape for the rectangle shape, verifying that it's correct.
  @Test
  public void makeShapeRectangle() {
    animation.makeShape(rectangle);
    assertEquals("Shapes: \n"
            + "Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60\n", animation.animationReadback());

  }

  //test makeShape with one shape: oval > verify
  @Test
  public void makeShapeOval() {
    animation.makeShape(oval);
    assertEquals("Shapes: \n"
            + "Name: O\n"
            + "Type: Oval\n"
            + "Center: (5.0, 5.0), X radius: 60.0, Y radius: 60.0, Color: (0.00, 0.00, 1.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n", animation.animationReadback());
  }

  //test makeShape with a list of more than one shape
  @Test
  public void makeManyShapes() {
    animation.makeShape(rectangle);
    animation.makeShape(oval);
    assertEquals("Shapes: \n"
            + "Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60\n"
            + "Name: O\n"
            + "Type: Oval\n"
            + "Center: (5.0, 5.0), X radius: 60.0, Y radius: 60.0, Color: (0.00, 0.00, 1.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n", animation.animationReadback());
  }

  //test makeShape with list of more than one shape include two of same type
  @Test
  public void makeManyShapesSameType() {
    animation.makeShape(rectangle);
    Shape secondRectangle = new Rectangle("R2", new Point2D(0, 10), red, 1,
            50, 25, 30);
    animation.makeShape(secondRectangle);
    assertEquals("Shapes: \n"
            + "Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60\n"
            + "Name: R2\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 10.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=50\n", animation.animationReadback());
  }

  //test error case with trying to add a shape to the animation with already existing name
  @Test(expected = IllegalArgumentException.class)
  public void illegalMakeShapeSameName() {
    animation.makeShape(rectangle);
    Shape secondRectangle = new Rectangle("R", new Point2D(0, 10), red, 0,
            50, 25, 30);
    animation.makeShape(secondRectangle);
  }

  //add two shapes, show that shapes are added, remove rectangle, show that shape is removed
  @Test
  public void removeShapeRectangle() {
    animation.makeShape(rectangle);
    animation.makeShape(oval);
    assertEquals("Shapes: \n"
            + "Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60\n"
            + "Name: O\n"
            + "Type: Oval\n"
            + "Center: (5.0, 5.0), X radius: 60.0, Y radius: 60.0, Color: (0.00, 0.00, 1.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n", animation.animationReadback());

    //remove rectangle
    animation.removeShape(rectangle);
    assertEquals("Shapes: \n"
            + "Name: O\n"
            + "Type: Oval\n"
            + "Center: (5.0, 5.0), X radius: 60.0, Y radius: 60.0, Color: (0.00, 0.00, 1.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n", animation.animationReadback());

  }

  // add two shapes, remove oval, show that shape is removed
  @Test
  public void removeShapeOval() {
    animation.makeShape(rectangle);
    animation.makeShape(oval);
    animation.removeShape(oval);
    assertEquals("Shapes: \n"
            + "Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60\n", animation.animationReadback());

  }

  //try to remove shapes from an empty animation model ("No such shape in animation")
  @Test(expected = IllegalArgumentException.class)
  public void removeShapeEmptyShapeList() {
    animation.removeShape(oval);
  }

  //apply move to a shape (match names)
  @Test
  public void createTransformationMove() {
    animation.makeShape(rectangle);
    Transformation moveRectangle = new Move("R", 10, 16,
            new Point2D(5, 6));
    animation.createTransformation(moveRectangle);
    assertEquals("Shapes: \n"
                    + "Name: R\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, "
                    + "Color: (1.00, 0.00, 0.00)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=60\n"
                    + "\n"
                    + "Animations: \n"
                    + "R moves from (0.0,0.0) to (5.0,6.0) from time t=10 to t=16\n",
            animation.animationReadback());
  }

  //apply scale to a shape (match names)
  @Test
  public void createTransformationScale() {
    animation.makeShape(rectangle);
    Transformation scaleRectangle = new Scale("R", 5, 10,
            30, 35);
    animation.createTransformation(scaleRectangle);
    assertEquals("Shapes: \n"
            + "Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60\n"
            + "\n"
            + "Animations: \n"
            + "R scales from width 25.0, Height: 30.0 to Width: 30.0 Height: 35.0 "
            + "from time t=5 to t=10\n", animation.animationReadback());

  }

  //apply color change to a shape (match names)
  @Test
  public void createTransformationColorChange() {
    animation.makeShape(rectangle);
    Transformation colorRectangle = new ChangeColor("R", 20, 30, blue);
    animation.createTransformation(colorRectangle);
    assertEquals("Shapes: \n"
                    + "Name: R\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, "
                    + "Color: (1.00, 0.00, 0.00)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=60\n"
                    + "\n"
                    + "Animations: \n"
                    + "R changes color from (1.00, 0.00, 0.00) to (0.00, 0.00, 1.00) "
                    + "from t=20 to t=30\n",
            animation.animationReadback());
  }

  //illegal case with no matching name - transformation doesn't apply
  //didn't throw IllegalArgumentException because that would prevent application of
  //any other transformations in the workflow to other shapes
  @Test
  public void illegalCreateTransformation() {
    animation.makeShape(rectangle);
    Transformation colorRectangle = new ChangeColor("Illegal case", 20,
            30, blue);
    animation.createTransformation(colorRectangle);
    assertEquals("Shapes: \n"
            + "Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60\n", animation.animationReadback());
  }

  //combo of all three transformations
  @Test
  public void createSeriesOfTransformations() {
    animation.makeShape(rectangle);
    Transformation colorRectangle = new ChangeColor("R", 20, 30, blue);
    animation.createTransformation(colorRectangle);
    Transformation scaleRectangle = new Scale("R", 5, 10,
            30, 35);
    animation.createTransformation(scaleRectangle);
    Transformation moveRectangle = new Move("R", 10, 16,
            new Point2D(5, 6));
    animation.createTransformation(moveRectangle);
    assertEquals("Shapes: \n"
                    + "Name: R\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, "
                    + "Color: (1.00, 0.00, 0.00)\n" + "Appears at t=1\n"
                    + "Disappears at t=60\n"
                    + "\n"
                    + "Animations: \n"
                    + "R scales from width 25.0, Height: 30.0 to Width: 30.0 "
                    + "Height: 35.0 from time t=5 to t=10\n"
                    + "R moves from (0.0,0.0) to (5.0,6.0) from time t=10 to t=16\n"
                    + "R changes color from (1.00, 0.00, 0.00) to (0.00, 0.00, 1.00) "
                    + "from t=20 to t=30\n",
            animation.animationReadback());
  }

  //case1: just print out shapes (no transformations)
  @Test
  public void animationReadbackShapesNoTransformations() {
    animation.makeShape(rectangle);
    assertEquals("Shapes: \n"
            + "Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60\n", animation.animationReadback());
  }

  //case2: apply transformations and print
  @Test
  public void animationReadbackShapesWithTransformations() {
    animation.makeShape(rectangle);
    Transformation colorRectangle = new ChangeColor("R", 20, 30, blue);
    animation.createTransformation(colorRectangle);
    assertEquals("Shapes: \n"
                    + "Name: R\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, "
                    + "Color: (1.00, 0.00, 0.00)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=60\n"
                    + "\n"
                    + "Animations: \n"
                    + "R changes color from (1.00, 0.00, 0.00) to "
                    + "(0.00, 0.00, 1.00) from t=20 to t=30\n",
            animation.animationReadback());

  }

  //case3: print with no shapes
  @Test(expected = IllegalArgumentException.class)
  public void animationReadbackNoShapes() {
    animation.animationReadback();
  }

  //test that all shapes are accounted for (add shapes, print)
  @Test
  public void getShapeVerifyShapes() {
    animation.makeShape(rectangle);
    animation.makeShape(oval);
    assertEquals("[Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60, Name: O\n"
            + "Type: Oval\n"
            + "Center: (5.0, 5.0), X radius: 60.0, Y radius: 60.0, Color: (0.00, 0.00, 1.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100]", animation.getShape().toString());

  }

  //empty shapelist (shapeList empty message)
  @Test
  public void getShapeEmptyShapeList() {
    assertEquals("[]",
            animation.getShape().toString());
  }

  //****cs5004.EasyAnimator.AbstractShape***
  //>>Test Constructor
  //Testing successful Rectangle constructor
  @Test
  public void testShapeConstructorRectangle() {
    Shape rectangle2 = new Rectangle("R2", new Point2D(0, 0), red, 1,
            50, 29, 40);

    assertEquals("Name: R2\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 29.0, Height: 40.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=50", rectangle2.toString());

  }

  //Testing successful Oval constructor
  @Test
  public void testShapeConstructorOval() {
    Shape oval2 = new Oval("O2", new Point2D(0, 0), red, 1,
            50, 29, 40);

    assertEquals("Name: O2\n"
            + "Type: Oval\n"
            + "Center: (0.0, 0.0), X radius: 29.0, Y radius: 40.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=50", oval2.toString());

  }

  //Testing illegal Shape constructor instance where width is OOB (<0)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeConstructorWidthOOB() {
    Shape oval2 = new Oval("O2", new Point2D(0, 0), red, 1,
            50, -5, 40);
  }

  //Testing illegal Shape constructor instance where height is OOB (<0)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeConstructorHeightOOB() {
    Shape rectangle2 = new Rectangle("R2", new Point2D(0, 0), red, 1,
            50, 29, -100);
  }

  //Testing illegal Shape constructor instance where X pos. of shape is OOB (<0)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeConstructorXPosOOB() {
    Shape rectangle2 = new Rectangle("R2", new Point2D(-5, 0), red, 1,
            50, 29, 40);
  }

  //Testing illegal Shape constructor instance where Y pos. of shape is OOB (<0)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeConstructorYPosOOB() {
    Shape rectangle2 = new Rectangle("R2", new Point2D(5, -2), red, 1,
            50, 29, 40);
  }

  //Testing illegal Shape constructor instance where appearTime of shape is OOB (=0)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeConstructorAppearTimeOOB() {
    Shape oval2 = new Oval("O2", new Point2D(0, 0), red, 0,
            50, 29, 40);
  }

  //Testing illegal Shape constructor instance where appearTime of shape is OOB (<0)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeConstructorAppearTimeOOB2() {
    Shape oval2 = new Oval("O2", new Point2D(0, 0), red, -5,
            50, 29, 40);
  }

  //Testing illegal Shape constructor instance where disappearTime of shape is OOB (<=0 or
  //appearTime)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeConstructorDisappearTimeOOB() {
    Shape oval2 = new Oval("O2", new Point2D(0, 0), red, 10,
            0, 29, 40);
  }

  //test that the getName function of the Rectangle shape works
  @Test
  public void testRectangleGetName() {
    Shape rectangle2 = new Rectangle("R2", new Point2D(5, 2), red, 1,
            50, 29, 40);
    assertEquals("R2", rectangle2.getName());
  }

  //test that the getName function of the Oval shape works
  @Test
  public void testOvalGetName() {
    Shape oval2 = new Oval("O2", new Point2D(5, 2), red, 1,
            50, 29, 40);
    assertEquals("O2", oval2.getName());
  }

  //test that the getType function of the Rectangle shape works
  @Test
  public void testRectangleGetType() {
    Shape rectangle2 = new Rectangle("R2", new Point2D(5, 2), red, 1,
            50, 29, 40);
    assertEquals("Rectangle", rectangle2.getType());
  }

  //test that the getType function of the Oval shape works
  @Test
  public void testOvalGetType() {
    Shape oval2 = new Oval("O2", new Point2D(5, 2), red, 1,
            50, 29, 40);
    assertEquals("Oval", oval2.getType());
  }

  //test that the getLocation function of the Rectangle shape works
  @Test
  public void testRectangleGetLocation() {
    assertEquals("(0.0, 0.0)", rectangle.getLocation().toString());
  }

  //test that the getLocation function of the Oval shape works
  @Test
  public void testOvalGetLocation() {
    assertEquals("(5.0, 5.0)",
            oval.getLocation().toString());
  }

  //test that the getColor function of the Rectangle shape works
  @Test
  public void testRectangleGetColor() {
    assertEquals("(1.00, 0.00, 0.00)", rectangle.getColor().toString());
  }

  //test that the getColor function of the Oval shape works
  @Test
  public void testOvalGetColor() {
    assertEquals("(0.00, 0.00, 1.00)",
            oval.getColor().toString());
  }

  //test that the getAppearTime function of the Rectangle shape works
  @Test
  public void testRectangleGetAppearTime() {
    assertEquals(1,
            rectangle.getAppearTime());
  }

  //test that the getAppearTime function of the Oval shape works
  @Test
  public void testOvalGetAppearTime() {
    assertEquals(1,
            oval.getAppearTime());
  }

  //test that the getDisappearTime function of the Rectangle shape works
  @Test
  public void testRectangleGetDisappearTime() {
    assertEquals(60,
            rectangle.getDisappearTime());
  }

  //test that the getDisappearTime function of the Oval shape works
  @Test
  public void testOvalGetDisappearTime() {
    assertEquals(100,
            oval.getDisappearTime());
  }

  //test that the getTransformation function of the Rectangle shape works
  @Test
  public void testRectangleGetTransformation() {
    Transformation moveRectangle = new Move("R", 2, 5,
            new Point2D(5, 5));
    moveRectangle.transform(rectangle);
    assertEquals("[R moves from (0.0,0.0) to (5.0,5.0) from time t=2 to t=5\n"
            + "]", rectangle.getTransformation().toString());
  }

  //test that the getTransformation function of the Oval shape works
  @Test
  public void testOvalGetTransformation() {
    Transformation moveOval = new Move("O", 2, 5,
            new Point2D(50, 50));
    moveOval.transform(oval);
    assertEquals("[O moves from (5.0,5.0) to (50.0,50.0) from time t=2 to t=5\n"
            + "]", oval.getTransformation().toString());
  }

  //test that the getWidth function of the Rectangle shape works
  @Test
  public void testRectangleGetWidth() {
    assertEquals(25,
            rectangle.getWidth(), 0.01);
  }

  //test that the getWidth function of the Oval shape works
  @Test
  public void testOvalGetWidth() {
    assertEquals(120,
            oval.getWidth(), 0.01);
  }

  //test that the getHeight function of the Rectangle shape works
  @Test
  public void testRectangleGetHeight() {
    assertEquals(30,
            rectangle.getHeight(), 0.01);
  }

  //test that the getHeight function of the Oval shape works
  @Test
  public void testOvalGetHeight() {
    assertEquals(120,
            oval.getHeight(), 0.01);
  }

  //test that the toString function of the Rectangle shape works
  @Test
  public void testRectangleToString() {
    assertEquals("Name: R\n"
            + "Type: Rectangle\n"
            + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, Color: (1.00, 0.00, 0.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=60", rectangle.toString());
  }

  //test that the toString function of the Oval shape works
  @Test
  public void testOvalToString() {
    assertEquals("Name: O\n"
            + "Type: Oval\n"
            + "Center: (5.0, 5.0), X radius: 60.0, Y radius: 60.0, Color: (0.00, 0.00, 1.00)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100", oval.toString());
  }


  //>>cs5004.EasyAnimator.Rectangle class
  //no unique tests for this class (that haven't already been taken care of above)


  //>>cs5004.EasyAnimator.Oval class
  //no unique tests for this class (that haven't already been taken care of above)

  //****cs5004.EasyAnimator.AbstractTransformation***
  //test the constructor for Move with ideal inputs
  @Test
  public void testMoveConstructor() {
    Transformation moveShape = new Move("R", 5, 10,
            new Point2D(50, 60));
    moveShape.transform(rectangle);
    assertEquals("R moves from (0.0,0.0) to (50.0,60.0) from time t=5 to t=10\n",
            moveShape.toString());
  }

  //test the constructor for Scale with ideal inputs
  @Test
  public void testScaleConstructor() {
    Transformation scaleShape = new Scale("R", 10, 23,
            30, 30);
    scaleShape.transform(rectangle);
    assertEquals("R scales from width 25.0, Height: 30.0 to Width: 30.0 "
            + "Height: 30.0 from time t=10 to t=23\n", scaleShape.toString());
  }

  //test the constructor for ChangeColor with ideal inputs
  @Test
  public void testChangeColorConstructor() {
    Transformation colorShape = new ChangeColor("R", 10, 25,
            new Color(245, 200, 155));
    colorShape.transform(rectangle);
    assertEquals("R changes color from (1.00, 0.00, 0.00) to (0.96, 0.78, 0.61) "
            + "from t=10 to t=25\n", colorShape.toString());
  }

  //test illegal fromTime constructor (<=0)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTransformationConstructorFromTime() {
    Transformation moveShape = new Move("R", -5, 10,
            new Point2D(50, 60));
  }

  //test illegal toTime constructor (<=0, < fromTime)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTransformationConstructorToTime() {
    Transformation moveShape = new Move("R", 5, -5,
            new Point2D(50, 60));
  }

  //test illegal toTime constructor (<=0, = fromTime)
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTransformationConstructorToTime2() {
    Transformation moveShape = new Move("R", 5, 5,
            new Point2D(50, 60));
  }

  //verify that the transform function works - rectangle
  @Test
  public void testTransformFunctionRectangle() {
    Transformation moveShape = new Move("R", 5, 15,
            new Point2D(50, 60));
    moveShape.transform(rectangle);
    assertEquals("R moves from (0.0,0.0) to (50.0,60.0) from time t=5 to t=15\n",
            moveShape.toString());
  }

  //verify that the transform function works - oval
  @Test
  public void testTransformFunctionOval() {
    Transformation moveShape = new Move("O", 5, 15,
            new Point2D(50, 60));
    moveShape.transform(oval);
    assertEquals("O moves from (5.0,5.0) to (50.0,60.0) from time t=5 to t=15\n",
            moveShape.toString());
  }

  //test illegal transformation - shape transformed during another transformation
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTransformation1() {
    Shape oval2 = new Oval("O2", new Point2D(5, 5), blue, 15,
            100, 60, 60);
    Transformation moveShape = new Move("O2", 20, 25,
            new Point2D(50, 60));
    moveShape.transform(oval2);
    Transformation moveShape2 = new Scale("O2", 20, 24,
            40, 40);
    moveShape2.transform(oval2);
  }

  //test illegal transformation - shape transformed before appearance
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTransformation2() {
    Shape oval2 = new Oval("O2", new Point2D(5, 5), blue, 15,
            100, 60, 60);
    Transformation moveShape = new Move("O2", 5, 15,
            new Point2D(50, 60));
    moveShape.transform(oval2);
  }

  //test illegal transformation - shape transformed after disappearance
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTransformation3() {
    Shape oval2 = new Oval("O2", new Point2D(5, 5), blue, 15,
            100, 60, 60);
    Transformation moveShape = new Move("O2", 105, 115,
            new Point2D(50, 60));
    moveShape.transform(oval2);
  }

  //test the getName function of the Move transformation
  @Test
  public void testGetNameMove() {
    Transformation moveShape = new Move("O2", 105, 115,
            new Point2D(50, 60));
    assertEquals("O2", moveShape.getName());
  }

  //test the getName function of the Scale transformation
  @Test
  public void testGetNameScale() {
    Transformation scaleShape = new Scale("O2", 105, 115,
            50, 60);
    assertEquals("O2", scaleShape.getName());
  }

  //test the getName function of the ChangeColor transformation
  @Test
  public void testGetNameChangeColor() {
    Transformation colorShape = new ChangeColor("O2", 20, 30,
            new Color(30, 200, 243));
    assertEquals("O2", colorShape.getName());
  }

  //test the getFromTime function of the Move transformation
  @Test
  public void testGetFromTimeMove() {
    Transformation moveShape = new Move("O2", 105, 115,
            new Point2D(50, 60));
    assertEquals(105, moveShape.getFromTime());
  }

  //test the getFromTime function of the Scale transformation
  @Test
  public void testGetFromTimeScale() {
    Transformation scaleShape = new Scale("O2", 100, 115,
            50, 60);
    assertEquals(100, scaleShape.getFromTime());
  }

  //test the getFromTime function of the ChangeColor transformation
  @Test
  public void testGetFromTimeChangeColor() {
    Transformation colorShape = new ChangeColor("O2", 20, 30,
            new Color(30, 200, 243));
    assertEquals(20, colorShape.getFromTime());
  }

  //test the getToTime function of the Move transformation
  @Test
  public void testGetToTimeMove() {
    Transformation moveShape = new Move("O2", 105, 115,
            new Point2D(50, 60));
    assertEquals(115, moveShape.getToTime());
  }

  //test the getToTime function of the Scale transformation
  @Test
  public void testGetToTimeScale() {
    Transformation scaleShape = new Scale("O2", 100, 115,
            50, 60);
    assertEquals(115, scaleShape.getToTime());
  }

  //test the getToTime function of the ChangeColor transformation
  @Test
  public void testGetToTimeChangeColor() {
    Transformation colorShape = new ChangeColor("O2", 20, 30,
            new Color(30, 200, 243));
    assertEquals(30, colorShape.getToTime());
  }


  //>>cs5004.EasyAnimator.Move class
  //test the getNewLocation function of the Move transformation
  @Test
  public void testMoveGetNewLocation() {
    Transformation moveShape = new Move("O2", 105, 115,
            new Point2D(50, 60));
    assertEquals("(50.0, 60.0)", moveShape.getNewLocation().toString());
  }

  //test the updateLocation function of the Move transformation
  @Test
  public void testMoveUpdateLocation() {
    Transformation moveShape = new Move("O", 10, 20,
            new Point2D(50, 60));
    animation.makeShape(oval);
    animation.createTransformation(moveShape);
    moveShape.updateLocation();
    assertEquals("(50.0, 60.0)", oval.getLocation().toString());
  }

  //test the Move specific transform method's illegal argument - cannot move to current location
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveCurrentLocation() {
    Transformation moveShape = new Move("O", 10, 20,
            new Point2D(5, 5));
    animation.makeShape(oval);
    animation.createTransformation(moveShape);
  }

  //test the Move's toString function
  @Test
  public void testMoveToString() {
    Transformation moveShape = new Move("O", 10, 20,
            new Point2D(5, 50));
    moveShape.transform(oval);
    assertEquals("O moves from (5.0,5.0) to (5.0,50.0) from time t=10 to t=20\n",
            moveShape.toString());
  }

  //>>cs5004.EasyAnimator.ChangeColor
  //illegal red OOB constructor
  @Test(expected = IllegalArgumentException.class)
  public void illegalRedColorChange() {
    Transformation colorChange = new ChangeColor("O", 30, 40,
            new Color(300, 200, 100));
  }

  //illegal green OOB constructor
  @Test(expected = IllegalArgumentException.class)
  public void illegalGreenColorChange() {
    Transformation colorChange = new ChangeColor("O", 30, 40,
            new Color(200, -200, 100));
  }

  //illegal blue OOB constructor
  @Test(expected = IllegalArgumentException.class)
  public void illegalBlueColorChange() {
    Transformation colorChange = new ChangeColor("O", 30, 40,
            new Color(200, 30, 600));
  }

  //test ChangeColor toString
  @Test
  public void changeColorToString() {
    Transformation colorChange = new ChangeColor("O", 30, 40,
            new Color(200, 30, 115));
    colorChange.transform(oval);
    assertEquals("O changes color from (0.00, 0.00, 1.00) to (0.78, 0.12, 0.45) "
            + "from t=30 to t=40\n", colorChange.toString());
  }


  //testing the Color class's getRed method
  @Test
  public void testColorGetRed() {
    Color testColor = new Color(234, 40, 100);
    assertEquals(234, testColor.getRed(), 0.01);
  }

  //testing the Color class's getGreen method
  @Test
  public void testColorGetGreen() {
    Color testColor = new Color(234, 40, 100);
    assertEquals(40, testColor.getGreen(), 0.01);
  }

  //testing the Color class's getBlue method
  @Test
  public void testColorGetBlue() {
    Color testColor = new Color(234, 40, 100);
    assertEquals(100, testColor.getBlue(), 0.01);
  }

  //testing the Color class's toString method
  @Test
  public void testColorToString() {
    Color testColor = new Color(234, 40, 100);
    assertEquals("(0.92, 0.16, 0.39)", testColor.toString());
  }

  //test the toString for the Scale method
  @Test
  public void scaleToString() {
    Transformation scaleShape = new Scale("O", 30, 40,
            50, 60);
    scaleShape.transform(oval);
    assertEquals("O scales from width 120.0, Height: 120.0 to Width: 50.0 "
            + "Height: 60.0 from time t=30 to t=40\n", scaleShape.toString());
  }

  //testing the Point2D constructor
  @Test
  public void testPoint2D() {
    Point2D testCoordinate = new Point2D(2, 6);
    assertEquals("(2.0, 6.0)", testCoordinate.toString());
  }

  //test Point2D getX()
  @Test
  public void testPoint2DGetX() {
    assertEquals(0,
            rectangle.getLocation().getX(), 0.1);
  }

  //test Point2D getY()
  @Test
  public void testPoint2DGetY() {
    assertEquals(0,
            rectangle.getLocation().getY(), 0.1);
  }

  //test Point2D setX()
  @Test
  public void testPoint2DSetX() {
    rectangle.getLocation().setX(12);
    assertEquals(12, rectangle.getLocation().getX(), 0.1);
  }

  //test Point2D setY()
  @Test
  public void testPoint2DSetY() {
    rectangle.getLocation().setY(12);
    assertEquals(12, rectangle.getLocation().getY(), 0.1);
  }

  //test Point2D toString()
  @Test
  public void testPoint2DtoString() {
    assertEquals("(5.0, 5.0)",
            oval.getLocation().toString());
  }

  //test TransformationSort class
  @Test
  public void testTransformationSort() {
    animation.makeShape(oval);
    Transformation moveShape = new Move("O", 10, 20,
            new Point2D(5, 50));
    animation.createTransformation(moveShape);
    animation.makeShape(rectangle);
    Transformation colorShape = new ChangeColor("R", 20, 40, blue);
    animation.createTransformation(colorShape);
    assertEquals("Shapes: \n"
                    + "Name: O\n"
                    + "Type: Oval\n"
                    + "Center: (5.0, 5.0), X radius: 60.0, Y radius: 60.0, "
                    + "Color: (0.00, 0.00, 1.00)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "Name: R\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, "
                    + "Color: (1.00, 0.00, 0.00)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=60\n"
                    + "\n"
                    + "Animations: \n"
                    + "O moves from (5.0,5.0) to (5.0,50.0) from time t=10 to t=20\n"
                    + "R changes color from (1.00, 0.00, 0.00) to (0.00, 0.00, 1.00) "
                    + "from t=20 to t=40\n",
            animation.animationReadback());

  }

  //test TransformationSort class
  @Test
  public void testShapeSort() {
    animation.makeShape(oval);
    animation.makeShape(rectangle);

    assertEquals("Shapes: \n"
                    + "Name: O\n"
                    + "Type: Oval\n"
                    + "Center: (5.0, 5.0), X radius: 60.0, Y radius: 60.0, "
                    + "Color: (0.00, 0.00, 1.00)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "Name: R\n"
                    + "Type: Rectangle\n"
                    + "Min corner: (0.0, 0.0), Width: 25.0, Height: 30.0, "
                    + "Color: (1.00, 0.00, 0.00)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=60\n",
            animation.animationReadback());

  }
}