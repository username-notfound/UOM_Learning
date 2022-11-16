public class MeanMinMaxMinusMean
{
  public static void main(String[] args) throws RuntimeException
  {
    int[] array = new int[args.length];
    for (int index = 0; index < args.length; index++)
    {
      array[index] = Integer.parseInt(args[index]);
    } // for
    Triple stats = IntArrayStats.getStats(array);
    int max = ((Integer)stats.getFirst()).intValue();
    int min = ((Integer)stats.getSecond()).intValue();
    double mean = ((Double)stats.getThird()).doubleValue();

    System.out.println((max + min) / 2.0 - mean);
  } // main
} // MeanMinMaxMinusMean
