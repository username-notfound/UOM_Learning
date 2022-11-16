public class MinMaxArray
{
  // Generic method with one type parameter that is comparable with itself
  public static <ArrayType extends Comparable<ArrayType>>
                        Pair<ArrayType, ArrayType> getMinAndMax(ArrayType[] anArray)
                        throws IllegalArgumentException
  {
    try
    {

      ArrayType min = anArray[0];
      ArrayType max = anArray[0];

      for (int index = 1; index < anArray.length; index++)
      {
        if (min.compareTo(anArray[index]) < 0)
        min = anArray[index];
      } // for

      for (int index = 1; index < anArray.length; index++)
      {
        if (max.compareTo(anArray[index]) > 0)
          max = anArray[index];
      }
      return new Pair<ArrayType, ArrayType>(min, max);
    } // try
    // catch exception
    catch (ArrayIndexOutOfBoundsException e)
    { throw new IllegalArgumentException("Array must be non-empty", e);}
    catch (NullPointerException e)
    { throw new IllegalArgumentException("Array must exist", e);}
  } // class method
} // MinMaxArray
