public class Triple<FirstType, SecondType, ThirdType>
{
  private final FirstType first;
  private final SecondType second;
  private final ThirdType third;

  // Constructor
  public Triple(FirstType requiredFirst, SecondType requiredSecond,
               ThirdType requiredThird)
  {
    first = requiredFirst;
    second = requiredSecond;
    third = requiredThird;
  }

  public FirstType getFirst()
  {
    return first;
  }

  public SecondType getSecond()
  {
    return second;
  }

  public ThirdType getThird()
  {
    return third;
  }
} // Triple
