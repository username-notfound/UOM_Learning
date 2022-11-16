import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class StreetOrder
{
  // Program to read lines of a file, and rewrite them in other order
  // Input file is the first argument, output file is the second.
  public static void main(String[] args)
  {
    BufferedReader input = null;
    PrintWriter output = null;
    try
    {
      // if (args.length != 2)
      //   throw IllegalArgumentException
      //           ("There are must be exactly two argument");

      input = new BufferedReader(new FileReader(args[0]));
      output = new PrintWriter(new FileWriter(args[1]));
      // The List for sorting the lines
      TreeSet<DeliveryHouseDetails> lineList =
                   new TreeSet<DeliveryHouseDetails>();

      // Read the lines into Linelist
      String currentLine;
      int homeNum = 1;
      while ((currentLine = input.readLine()) != null)
      {
        DeliveryHouseDetails details
               = new DeliveryHouseDetails(homeNum,currentLine);
        lineList.add(details);
        homeNum++;
      } // while

      Iterator<DeliveryHouseDetails> iterator = lineList.iterator();
      while(iterator.hasNext())
        output.println(iterator.next().getPersonName());
    } // try
    catch (ArrayIndexOutOfBoundsException e)
    { throw new IllegalArgumentException("Array must be non-empty", e);}
    catch (NullPointerException e)
    { throw new IllegalArgumentException("Array must exist", e);}
    catch(Exception exception)
    {
      System.err.println(exception);
    } // catch
    finally
    {
    try { if (input != null) input.close(); }
    catch (IOException exception)
      { System.err.println("Could not close the input" + exception); }
    if (output != null)
    {
      output.close();
      if (output.checkError())
      {
        System.err.println("Something went wrong with the output writer.");
      } // of
    } // if
   } // finally

  } // main

} // class StreetOrder
