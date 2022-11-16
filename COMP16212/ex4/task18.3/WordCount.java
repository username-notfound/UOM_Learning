import java.io.InputStreamReader;
import java.io.IOException;

// Program to count how many words that contains from standard input
// and reports the number on its standard input.
public class WordCount
{
  public static void main(String[] agrs)
 {

    // We will read the input as words.
    InputStreamReader input = new InputStreamReader(System.in);
    // The total number of words found so far.
    int allWordsCountSoFar = 0;

    try
    {
      int currentWord;
      int storedWord = 0;
      while((currentWord = input.read()) != -1)
      {
        if (storedWord != 0 && !Character.isWhitespace(storedWord)
              && Character.isWhitespace(currentWord))
        {
          allWordsCountSoFar++;
        }
        storedWord = currentWord;
      } // while
    } // try
    catch(Exception exception)
    {
      System.err.println(exception);
    } // catch
    finally
    {
      try {input.close();}
      catch(IOException exception)
        {System.err.println("Could not close input " + exception);} // catch
    } // finally

    // Reports results.
    System.out.println("The number of words read was " + allWordsCountSoFar);

  } // main
} // WordCount
