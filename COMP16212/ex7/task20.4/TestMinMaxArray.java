public class TestMinMaxArray
{
  public static void main(String[] args) throws RuntimeException
  {
    Integer[] input = new Integer[args.length];
    for(int index = 0; index < args.length; index++)
    {
      input[index] = Integer.parseInt(args[index]);
    } // for

    Pair<Integer, Integer> result = MinMaxArray.getMinAndMax(input);

    int min = (result.getSecond()).intValue();
    int max = (result.getFirst()).intValue();
    // Print the result
    System.out.println("The min is " + min);
    System.out.println("The max is " + max);
  } // main
} // TestMinMaxArray
