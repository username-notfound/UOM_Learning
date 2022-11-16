import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Uuencode
{
  private static FileInputStream input;
  private static void WriteByteAsChar(int thisByte)
  {
    System.out.print((char) (thisByte == 0 ? 96 : thisByte + 32));
  } // WriteByteAsChar

  public static void main(String[] args)
  {
    String filename = null;
    try
    {
      if (args.length != 1)
        throw new IllegalArgumentException();
      else
      {
        filename = args[0];
        input = new FileInputStream(filename);
      } // else
      // write the header
      System.out.println("begin 600 " + filename);
      // create an array - partially full
      int[] lineBytes = new int[45];
      // read next byte
      int nextByte = input.read();
      // while next byte is not equal to-1
      while (nextByte != -1)
      {
        // while the next byte is not equal to -1
        // and the array is not full
        int index = 0;
        while (nextByte != -1 && index < 45)
        {
          // put next byte in the array
          lineBytes[index] = nextByte;
          index++;
          // read next byte
          nextByte = input.read();
        } // while

        // output the number of bytes on this lineBytes
        WriteByteAsChar(index);

        // llop over the line array in groups of 3 bytes
        for (int byteGroupIndex = 0; byteGroupIndex < 45;
             byteGroupIndex = byteGroupIndex + 3)
        {
          // calculate
          int byte1 = lineBytes[byteGroupIndex] >> 2;
          int byte2 = (lineBytes[byteGroupIndex] & 0x3) << 4
                      | (lineBytes[byteGroupIndex + 1] >> 4 );
          int byte3 = (lineBytes[byteGroupIndex + 1] & 0xf) << 2
                      | (lineBytes[byteGroupIndex + 2] >> 6 );
          int byte4 = lineBytes[byteGroupIndex + 2] & 0x3f;
          // output
          WriteByteAsChar(byte1);
          WriteByteAsChar(byte2);
          WriteByteAsChar(byte3);
          WriteByteAsChar(byte4);
        } // for
        System.out.println("");
      } // while

    WriteByteAsChar(0);
    System.out.println("end");
    } // try
    catch (IllegalArgumentException exception)
    {
      System.err.println("Please only enter one file to encode");
    } // catch
    catch (FileNotFoundException exception)
    {
      System.err.println("Cannot open file " + filename);
    } // catch
    catch (IOException exception)
    {
      System.err.println("Problem reading file: " + exception.getMessage());
    } // catch
    finally
    {
      try { if (input != null) input.close(); }
      catch (IOException exception)
        { System.err.println("Could not close the file " + exception);}
    } // finally
  } // main
} // Uuencode
