public class IntArrayStats
{
  private static int max;
  private static int min;
  private static double mean;
  private static int sumOfArray;

  public static Triple<Integer, Integer, Double> getStats(int[] array)
                       throws IllegalArgumentException
  {
    if (array == null| array.length == 0)
      throw new IllegalArgumentException("Array must exist and non-empty");

    max = array[0];
    min = array[0];
    sumOfArray = 0;
    for(int index = 1; index < array.length; index++)
    {
      if(array[index] > max)
        max = array[index];

      if(array[index] < min)
        min = array[index];
    } // for
    for (int index = 0; index < array.length; index++)
    {
      sumOfArray += array[index];
    }

    mean = sumOfArray / (double)array.length;
    return new Triple<Integer, Integer, Double>(min, max, new Double(mean));
  } // getStats
} // IntArrayStats
