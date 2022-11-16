// Program to compute the volume of a fish tank.
// Dimensions are three command line arguments: width depth height.
public class FishTankVolume
{
  public static void main(String [] args)
  {
    try
    {
      int width = Integer.parseInt(args[0]);
      int depth = Integer.parseInt(args[1]);
      int height = Integer.parseInt(args[2]);


      if (args.length != 3)
        throw new ArrayIndexOutOfBoundsException
                     ("You have suppiled " + args.length + " arguments!");

      if (width <= 0)
        throw new NumberFormatException
                     ("Width of the fish tank " + width + " is negative.");

      if (depth <= 0)
        throw new NumberFormatException
                     ("Depth of the fish tank " + depth + " is negative.");

      if (height <= 0)
        throw new NumberFormatException
                     ("Heigh of the fish tank " + height + " is negative.");

      int volume = width * depth * height;
      System.out.println("The volume of a tank with dimensions "
                            + "(" + width + "," + depth + "," + height + ") "
                            + "is " + volume);
    } // try

     catch (ArrayIndexOutOfBoundsException exception)
     {
       System.out.println("Please supply the width, depth and height of the "
                         + "fish tank, and nothing else.");
       System.out.println("Exception message was '" +
                          exception.getMessage() + "'");
       System.err.println(exception);
     } // catch

     catch (NumberFormatException exception)
     {
       System.out.println("Width, depth and height must be"
                           + " positive whole number.");
       System.out.println("Exception message was '" +
                          exception.getMessage() + "'");
       System.err.println(exception);
     } // catch

     catch (Exception exception)
     {
       System.out.println("Something unforeseen has happened :-(");
       System.out.println("Exception message was '" +
                          exception.getMessage() + "'");
       System.err.println(exception);
     } // catch
  } // main

} // class FishTankVolume
