import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Program to copy standard input from a file to standard ouput
// but with the lines in reverse order.
public class ReverseLines
{
  public static void main(String[] args)
  {
    BufferedReader input = null;
    PrintWriter output = null;
    try
    {
      input = new BufferedReader(new FileReader(args[0]));
      output = new PrintWriter(new FileWriter(args[1]));
      // An list to store the input
      List<String> inputOfFile = new ArrayList<String>();
      // Read the lines into array
      String currentLine;

      while ((currentLine = input.readLine()) != null)
      {
        inputOfFile.add(currentLine);
      } // while

      for(int count = inputOfFile.size() - 1; count >= 0; count--)
      {
        output.println(inputOfFile.get(count));
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
       } // of
     } // if
   } // finally

  } // main method
}
