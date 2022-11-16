import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteField
{

  public static void main(String[] args)
  {
    BufferedReader input = null;
    PrintWriter output = null;
    try
    {
      // Check for too many arguments before opening files
      // in case wrong names
      int fieldsToDelete = Integer.parseInt(args[0]);
      if (args.length > 3)
        throw new DeleteFieldException("Too many arguments ");

      if (args.length == 1)
        throw new DeleteFieldException("Please provide the input file");

      if (args.length < 1)
        throw new DeleteFieldException("Please enter a file to delete");

      if (args.length < 2 || args[1].equals("-"))
        input = new BufferedReader(new InputStreamReader(System.in));
      else
        input = new BufferedReader(new FileReader(args[1]));

      if(args.length < 3 || args[2].equals("-"))
        output = new PrintWriter(System.out, true);
      else
      {
        if (new File(args[2]).exists())
          throw new DeleteFieldException("Output file " + args[2]
                           + " already exists");
        output = new PrintWriter(new FileWriter(args[2]));
      } //else

      // Now delete fields.
      String inputLine = "";
      String currentLine;
      while((currentLine = input.readLine()) != null)
      {

        // Divide the line into fields using a tab as a delimiter
        String[] fields = currentLine.split("\t");
        String editedLine = "";
        if (fields.length < fieldsToDelete)
          {
            editedLine = currentLine;
            System.out.println(editedLine);
          }
        else
        {
          // We build the new line in parts
          // Add the fields before the one to be deleted.
          for(int index = 0; index < fieldsToDelete - 1; index++)
          {
            if (editedLine.equals(""))
              editedLine = fields[index];
            else
              editedLine += "\t" + fields[index];
          } // for
          // Add the fields after the one to be deleted;
          for(int index = fieldsToDelete; index < fields.length; index++)
          {
            if (editedLine.equals(""))
              editedLine = fields[index];
            else
              editedLine += "\t" + fields[index];
          } // for
          System.out.println(editedLine);
        } // else

      output.println(editedLine);
      }// while
    } // try
    catch(DeleteFieldException excepetion)
    {
      System.err.println(excepetion.getMessage());
    } // catch
    catch(IOException excepetion)
    {
      System.err.println(excepetion);
    } // catch
    finally
    {
      try { if (input != null) input.close(); }
      catch (IOException excepetion)
        {System.err.println("Could not close input " + excepetion);}
      if (output != null)
      {
        output.close();
        if (output.checkError())
          System.err.println("Something wrong with the output");
      } // if
    } // finally

  } // main
} // DeleteField
