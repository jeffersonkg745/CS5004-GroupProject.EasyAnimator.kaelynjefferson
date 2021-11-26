package cs5004.easyanimator;

/**
 * This class represents a color in our animation model using an RGB color model.
 */

public class Color {
  private double red;
  private double green;
  private double blue;

  /**
   * Constructs a Color object with the inputted Red, Green, and Blue values in the traditional RGB
   * model range of 0-255.
   *
   * @param red   the value for the color red in the RGB model
   * @param green the value for the color green in the RGB model
   * @param blue  the value for the color blue in the RGB model
   * @throws IllegalArgumentException if the color value isn't within a valid range.
   */
  public Color(double red, double green, double blue) throws IllegalArgumentException {
    if (red < 0 || red > 255) {
      throw new IllegalArgumentException("Color values are to range between 0-255.");
    } else {
      this.red = red;
    }

    if (green < 0 || green > 255) {
      throw new IllegalArgumentException("Color values are to range between 0-255.");
    } else {
      this.green = green;
    }

    if (blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Color values are to range between 0-255.");
    } else {
      this.blue = blue;
    }
  }

  /**
   * Returns the Color's red value.
   *
   * @return the red value in the RGB model
   */
  public double getRed() {
    return this.red;
  }

  /**
   * Returns the Color's green value.
   *
   * @return the green value in the RGB model
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * Returns the Color's blue value.
   *
   * @return the blue value in the RGB model
   */
  public double getBlue() {
    return this.blue;
  }


  /**
   * Returns a String representation of the Color in (R, G, B) format. The value returned for each
   * color is between the values of 0-1.
   *
   * @return a String representation of the RGB color model
   */
  public String toString() {
    double strRed = this.getRed() / 255;
    double strGreen = this.getGreen() / 255;
    double strBlue = this.getBlue() / 255;

    String str = "(" + String.format("%.2f", strRed) + ", " + String.format("%.2f", strGreen)
            + ", " + String.format("%.2f", strBlue) + ")";

    return str;
  }

}
