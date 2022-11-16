public class MeanMinMaxMinusMean
{
  public static void main(String[] args) throws RuntimeException
  {
    int[] array = new int[args.length];
    for (int index = 0; index < args.length; index++)
    {
      array[index] = Integer.parseInt(args[index]);
    } // for
    Triple<Integer, Integer, Double> stats = IntArrayStats.getStats(array);
    int max = stats.getFirst();
    int min = stats.getSecond();
    double mean = stats.getThird();

    System.out.println((max + min) / 2.0 - mean);
  } // main
} // MeanMinMaxMinusMean
