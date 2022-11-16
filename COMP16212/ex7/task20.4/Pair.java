public class Pair<FirstType, SecondType>
{
  private final FirstType first;
  private final SecondType second;

  // Constructor
  public Pair(FirstType requiedFirst, SecondType requiedSecond)
  {
    first = requiedFirst;
    second = requiedSecond;
  } // Constructor

  // Return the first object
  public FirstType getFirst()
  {
    return first;
  } // getFirst

  // Return the second object
  public SecondType getSecond()
  {
    return second;
  } // getSecond

} // Pair
