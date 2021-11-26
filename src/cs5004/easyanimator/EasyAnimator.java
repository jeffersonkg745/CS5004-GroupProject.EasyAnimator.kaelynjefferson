package cs5004.easyanimator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import cs5004.easyanimator.view.ShapeViewInterface;

public class EasyAnimator {
  private ShapeAnimationModel model;
  private ShapeViewInterface view;
  private Appendable appendable;
  private String input;
  private int speed;
  private String typeOfView;

  public static void main(String[] args) {
//    String str = new String(args);
//    Scanner scanner = new Scanner(Arrays.toString(args));

//    String[] args = {"-in", "name-of-animation-file", "-view", "type-of-view", "-out",
//            "where-output-show-go", "-speed", "integer-ticks-per-second"};


    if (args.length > 0) {
      for (int i = 0; i < args.length; i ++) {
        if (args[i].contains("-in")) {
          if ((args[i + 1].endsWith(".txt")) || (args[i + 1].endsWith(".svg"))) {
            String input = args[i + 1];
          } else {
            throw new IllegalArgumentException("Not a valid file type"); //change to pop-up message
          }
        }

        if (args[i].contains("-view")) {
          if ((args[i + 1].equalsIgnoreCase("svg") || args[i + 1].contains("visual")
                  || args[i + 1].contains("text"))) {
            String typeOfView = args[i + 1];
          } else {
            throw new IllegalArgumentException("Not a valid view type"); //change to pop-up message
          }
        }

        if (args[i].contains("-out")) {
          appendable = System.out;
        } else {
          try {
            appendable = new FileWriter(args[i+1]);
          } catch (IOException e) {
            throw new IllegalArgumentException("Cannot create output file."); //change to pop-up message
          }
        }

        if (args[i].contains("speed")) {
          speed = Integer.parseInt(args[i+1]);
          if (speed < 1) {
            throw new IllegalArgumentException("Tick rate must be above 0");
          }
        }

      }
    }

// start with creating empty instance of AnimationFileReader
// need an empty model impl

    //parse the file for input
    //-in "name-of-animation-file" -view "type-of-view"
    // -out "where-output-show-go" -speed "integer-ticks-per-second"

    //use Scanner
    //****OPTION 1
//    int firstArg;
//    if (args.length > 0) {
//      try {
//        firstArg = Integer.parseInt(args[0]);
//      } catch (NumberFormatException e) {
//        System.err.println("Argument" + args[0] + " must be an integer.");
//        System.exit(1);
//      }
//    }

    //****OPTION 2
//    public class Echo {
//      public static void main (String[] args) {
//        for (String s: args) {
//          System.out.println(s);
//        }
//      }
//    }

    //while length of argument > 0
    // case: "in" do _____
    //

    //use builder file to make the animation

    //based on what was inputted on -view, use the specific view class to create output

  }

}