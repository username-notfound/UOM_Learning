import  java.io.IOException;

// Program to calculate some kind of check sum of the file
// and compare it with the number obtained from the original file
public class CheckSum
{
  public static void main(String[] args)
  {
    int checkSum = 0;
    try
    {
      int currentByte;
      while ((currentByte = System.in.read()) != -1)
      {
        if (checkSum % 2 == 0)
        {
          checkSum /= 2;
        } // if
        else
        {
          checkSum /= 2;
          checkSum += 32768;
        } // else
        checkSum += currentByte;
        if (checkSum >= 65536)
        checkSum -= 65536;
      } // while
    } // try
    catch (IOException exception)
    {
      System.err.println(exception);
    } // catch
    finally
    {
      try {System.in.close();}
      catch (IOException exception)
        {System.err.println("Could not close input "+ exception);}
    } // finally

    System.out.println(checkSum);

  } // main
} // CheckSum
