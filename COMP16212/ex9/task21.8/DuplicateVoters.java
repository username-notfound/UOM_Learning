import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class DuplicateVoters
{
  public static void main(String[] args)
  {
    BufferedReader input = null;
    PrintWriter output = null;
    try
    {
      input = new BufferedReader(new FileReader(args[0]));
      output = new PrintWriter(new FileWriter(args[1]));
      FirstOccurenceMap firstOcc = new FirstOccurenceMap();

      int numOfVotes = 0;
      String currentLine;

      while ((currentLine = input.readLine()) != null)
      {
        String voterName = currentLine;
        String timeAndLocation = input.readLine();
        boolean isFirstTime =
                    firstOcc.isFirstTime(voterName, timeAndLocation);
        if(!isFirstTime)
        {
          String first = firstOcc.getTimeAndLocation(voterName);
          output.println(currentLine);
          output.println("  Duplicate: " + timeAndLocation);
          output.println("  First Occurence: " + first);
          numOfVotes++;
        } // if
      } // while
      output.println("There are " + numOfVotes + " duplicate votes");
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
} // DuplicateVotersS
