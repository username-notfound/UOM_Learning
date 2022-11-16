import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
      Set<String> voterIdentifications = new HashSet<String>();

      int numOfVotes = 0;
      String currentLine;

      while ((currentLine = input.readLine()) != null)
      {
        if(!VoterIdentifications.add(currentLine))
        {
          output.println(currentLine);
          numOfVotes++;
        } // if
        currentLine =input.readLine();
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
