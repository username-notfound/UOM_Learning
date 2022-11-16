import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
      List<String> LineList = new ArrayList<String>();

      // Read the lines into Linelist
      String currentLine;
      while ((currentLine = input.readLine()) != null)
      {
        LineList.add(currentLine);
      } // while

      // odd number
      for(int odd = 0; odd < LineList.size(); odd = odd + 2 )
      {
        output.println(LineList.get(odd));
      } // for

      // even number
      int getListEven;
      if (LineList.size() % 2 == 0)
        getListEven = LineList.size() - 1;
      else
        getListEven = LineList.size() - 2;

      for(int even = getListEven; even > 0; even = even - 2 )
      {
        output.println(LineList.get(even));
      } // for
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
       }
     } // if
   } // finally
  } // main
} // class StreetOrder
